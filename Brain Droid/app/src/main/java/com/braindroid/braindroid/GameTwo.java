package com.braindroid.braindroid;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
    private TextView vocabWord;
    private RadioGroup definitionGroup;
    private RadioButton def1, def2, def3, def4, definition;
    private Button next;
    private int randomInt, selectedId, randomInt2;
    private Random randomGenerator = new Random();
    private Random randomGen2 = new Random();
    private TextView question_number;
    private int number_of_questions= 10, question_num=0, number_correct=0;
    private double decimal_correctness;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gametwo);

        next =(Button) findViewById(R.id.button);
        vocabWord = (TextView) findViewById(R.id.textView9);
        definitionGroup = (RadioGroup) findViewById(R.id.rg);
        def1 = (RadioButton) findViewById(R.id.rb1);
        def2 = (RadioButton) findViewById(R.id.rb2);
        def3 = (RadioButton) findViewById(R.id.rb3);
        def4 = (RadioButton) findViewById(R.id.rb4);
        next = (Button) findViewById(R.id.button);

        question_number= (TextView) findViewById(R.id.textView10);

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
    }

    public void NextClick(View v)
    {

        if(question_num<10) {

            check_answers();
            populate_question();

            if(question_num==10)
            {
                next.setText("Submit Answers");

                //should check parse history to see if this new value, below, is greatest
                //if greatest, then make a toast! and add to high score (variable declaration needed), else do not say anything
                decimal_correctness= number_correct/number_of_questions;
                //then save into parse
            }

            definitionGroup.clearCheck();


        }
        else
        {
            Intent intent = new Intent(GameTwo.this, MainMenu.class);
            startActivity(intent);
        }


    }

    public void check_answers()
    {
        selectedId = definitionGroup.getCheckedRadioButtonId();
        definition = (RadioButton) findViewById(selectedId);

        if(definitionMap.get(randomInt)==definition.getText()) {

            number_correct++;
            Toast.makeText(getApplicationContext(), "Good Job",
                    Toast.LENGTH_SHORT).show();
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
}
