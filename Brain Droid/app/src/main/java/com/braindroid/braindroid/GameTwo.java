package com.braindroid.braindroid;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Random;






/**
 * Created by Vanessa on 11/11/2015.
 */
public class GameTwo extends AppCompatActivity {

    private HashMap<Integer, String> vocabMap = new HashMap<Integer, String>();
    private HashMap<Integer, String> definitionMap = new HashMap<Integer, String>();
    private HashMap<Integer, String> sentenceMap = new HashMap<Integer, String>();
    private TextView vocabWord, Hint;
    private RadioGroup definitionGroup;
    private RadioButton def1, def2, def3, def4, definition;
    private Button next, hint;
    private int currentWordPosition, selectedId, randomInt2, radiob=1;
    private Random randomGenerator = new Random();
    private Random randomGen2 = new Random();
    private TextView score;
    private int number_correct=0;
    private double points=0;

    private boolean newBest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gametwo);

        next =(Button) findViewById(R.id.button);
        hint =(Button) findViewById(R.id.button10);
        Hint = (TextView) findViewById(R.id.textView12);
        vocabWord = (TextView) findViewById(R.id.textView9);
        score = (TextView) findViewById(R.id.textView16);
        definitionGroup = (RadioGroup) findViewById(R.id.rg);
        def1 = (RadioButton) findViewById(R.id.rb1);
        def2 = (RadioButton) findViewById(R.id.rb2);
        def3 = (RadioButton) findViewById(R.id.rb3);
        def4 = (RadioButton) findViewById(R.id.rb4);
        next = (Button) findViewById(R.id.button);

        newBest=false;

        final Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        PopulateHash();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus) {

            populate_question();
        }
    }

    private void PopulateHash() {
        try {

            String data;
            AssetManager am = this.getAssets();
            InputStream is = am.open("vocabwords.txt");

            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            int i = 0;
            while ((data = reader.readLine()) != null) {
                vocabMap.put(i, data);
                i++;
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }


        try {

            String data2;
            AssetManager am2 = this.getAssets();
            InputStream is2 = am2.open("definitions.txt");
            BufferedReader reader2 = new BufferedReader(new InputStreamReader(is2));

            int j = 0;
            while ((data2 = reader2.readLine()) != null) {

                definitionMap.put(j, data2);
                j++;
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        try {

            String data3;
            AssetManager am3 = this.getAssets();
            InputStream is3 = am3.open("use_in_sentence.txt");
            BufferedReader reader3 = new BufferedReader(new InputStreamReader(is3));

            int k = 0;
            while ((data3 = reader3.readLine()) != null) {

                sentenceMap.put(k, data3);
                k++;
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }
    }

    public void Hint(View v) {
        hint.setVisibility(View.GONE);
        Hint.setText(sentenceMap.get(currentWordPosition));
        points=points-.5;
        score.setText("Score: " + points);
    }

    public void NextClick(View v) {
        if(definitionGroup.getCheckedRadioButtonId()==-1) {
            Toast.makeText(getApplicationContext(), "You Must Answer", Toast.LENGTH_SHORT).show();
        }
        else {
            Hint.setText("");
            Hint.setHint("Press Hint, Lose Half Point");
            hint.setVisibility(View.VISIBLE);
            check_answers();
            populate_question();
            definitionGroup.clearCheck();
        }
    }

    private void check_answers() {
        selectedId = definitionGroup.getCheckedRadioButtonId();
        definition = (RadioButton) findViewById(selectedId);

        if(definitionMap.get(currentWordPosition)==definition.getText()) {
            User.incRight(User.Game.VOCAB);
            points+=1;
            score.setText("Score: " + points);
            Toast.makeText(getApplicationContext(), "Good Job", Toast.LENGTH_SHORT).show();
        }
        else {
            User.incWrong(User.Game.VOCAB);
            Toast.makeText(getApplicationContext(), vocabMap.get(currentWordPosition) + " - " + definitionMap.get(currentWordPosition), Toast.LENGTH_SHORT).show();
            points-=1;
            score.setText("Score: " + points);
        }

        if(User.checkBest((int)points, User.Game.VOCAB) && !newBest){
            Toast.makeText(this, "New personal best!", Toast.LENGTH_SHORT).show();
            newBest=true;
        }
    }


    private void populate_question() {

        currentWordPosition = randomGenerator.nextInt(99);
        vocabWord.setText(vocabMap.get(currentWordPosition));

        radiob = randomGenerator.nextInt(4);
        switch(radiob){
            case 0:
                def1.setText(definitionMap.get(currentWordPosition));
                break;
            case 1:
                def2.setText(definitionMap.get(currentWordPosition));
                break;
            case 2:
                def3.setText(definitionMap.get(currentWordPosition));
                break;
            case 3:
                def4.setText(definitionMap.get(currentWordPosition));
                break;
        }

        for(int x=0; x<4; x++) {
            do {
                randomInt2 = randomGenerator.nextInt(99);
            }while(currentWordPosition == randomInt2);

            if (radiob != 0 && x==0)
                def1.setText(definitionMap.get(randomGenerator.nextInt(99)));
            else if (radiob != 1 && x==1)
                def2.setText(definitionMap.get(randomGenerator.nextInt(99)));
            else if (radiob != 2 && x==2)
                def3.setText(definitionMap.get(randomGenerator.nextInt(99)));
            else if (radiob != 3 && x==3)
                def4.setText(definitionMap.get(randomGenerator.nextInt(99)));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.action_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_back:
                final Intent back = new Intent(this, MainMenu.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(back);
                return true;

            case R.id.action_forward:
                final Intent scores = new Intent(this, HighScores.class);
                scores.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
                        | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                scores.putExtra("game", "vocab");
                startActivity(scores);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
