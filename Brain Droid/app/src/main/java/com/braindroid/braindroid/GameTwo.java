package com.braindroid.braindroid;

import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

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


    //Need to create a hash map for vocabulary words.
    //to randomly select a word
    //and generate the definitions of the word

    private HashMap<Integer, String> vocabMap = new HashMap<Integer, String>();
    private HashMap<Integer, String> definitionMap = new HashMap<Integer, String>();
    private HashMap<Integer, String> sentenceMap = new HashMap<Integer, String>();
    private TextView vocabWord, Hint;
    private RadioGroup definitionGroup;
    private RadioButton def1, def2, def3, def4, definition;
    private Button next, hint;
    private int randomInt, selectedId, randomInt2;
    private Random randomGenerator = new Random();
    private Random randomGen2 = new Random();
    private TextView question_number;
    private int number_of_questions= 10, question_num=0, number_correct=0;
    private double decimal_correctness;
    android.support.v4.app.Fragment f;
    FragmentManager fm = getSupportFragmentManager();
    private LinearLayout Hintlay, questionLay;

    private boolean newBest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gametwo);

        Hintlay = (LinearLayout) findViewById(R.id.lay2);
        questionLay = (LinearLayout) findViewById(R.id.rel);

        next =(Button) findViewById(R.id.button);
        hint =(Button) findViewById(R.id.button10);
        Hint = (TextView) findViewById(R.id.textView12);
        vocabWord = (TextView) findViewById(R.id.textView9);
        definitionGroup = (RadioGroup) findViewById(R.id.rg);
        def1 = (RadioButton) findViewById(R.id.rb1);
        def2 = (RadioButton) findViewById(R.id.rb2);
        def3 = (RadioButton) findViewById(R.id.rb3);
        def4 = (RadioButton) findViewById(R.id.rb4);
        next = (Button) findViewById(R.id.button);

        question_number= (TextView) findViewById(R.id.textView10);

        newBest=false;

        PopulateHash();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus) {

            populate_question();
        }
    }

    public void PopulateHash() {
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

    public void Hint(View v)
    {
        Hint.setText(sentenceMap.get(randomInt));
    }

    public void NextClick(View v) {
        Hint.setText("");
        if(definitionGroup.getCheckedRadioButtonId()==-1) {

            Toast.makeText(getApplicationContext(), "You Must Answer",
                    Toast.LENGTH_SHORT).show();
        }
        else {

            if (question_num < 10) {
                check_answers();
                populate_question();

                if (question_num == 10) {
                    next.setText("Submit Answers");

                    //should check parse history to see if this new value, below, is greatest
                    //if greatest, then make a toast! and add to high score (variable declaration needed), else do not say anything
                    decimal_correctness = number_correct / number_of_questions;
                    //then save into parse
                }

                definitionGroup.clearCheck();


            }
            else {
                Intent intent = new Intent(GameTwo.this, MainMenu.class);
                startActivity(intent);
            }
        }
    }

    public void check_answers()
    {
        selectedId = definitionGroup.getCheckedRadioButtonId();
        definition = (RadioButton) findViewById(selectedId);

        if(definitionMap.get(randomInt)==definition.getText()) {
            User.incRight(User.Game.VOCAB);
            number_correct++;
            Toast.makeText(getApplicationContext(), "Good Job",
                    Toast.LENGTH_SHORT).show();
        }
        else {
            User.incWrong(User.Game.VOCAB);
            Toast.makeText(getApplicationContext(),"            Correct Answer: \n" + vocabMap.get(randomInt)+ " - " + definitionMap.get(randomInt), Toast.LENGTH_LONG).show();
        }

        if(User.checkBest(number_correct, User.Game.VOCAB) && !newBest){
            Toast.makeText(this, "New personal best!", Toast.LENGTH_SHORT).show();
            newBest=true;
        }
    }

    public void populate_question() {

        randomInt = randomGenerator.nextInt(99);


        vocabWord.setText(vocabMap.get(randomInt));

        def1.setText(definitionMap.get(randomInt));
        def2.setText(definitionMap.get(randomGen2.nextInt(99)));
        def3.setText(definitionMap.get(randomGen2.nextInt(99)));
        def4.setText(definitionMap.get(randomGen2.nextInt(99)));

        question_num++;
        question_number.setText(question_num + " out of " + number_of_questions);
    }



    public void changeHintFrag(View v)
    {
        FragmentTransaction t=fm.beginTransaction();

        switch (v.getId()) {
            case R.id.authors:
                f = new Frag_AboutUs();
                t.replace(R.id.lay, f);
                t.commitAllowingStateLoss();
                break;
            case R.id.app:
                f = new Frag_AboutApp();
                t.replace(R.id.lay, f);
                t.commitAllowingStateLoss();
            default:
        }
    }
}
