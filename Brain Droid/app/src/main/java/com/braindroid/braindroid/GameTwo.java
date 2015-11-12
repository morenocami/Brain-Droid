package com.braindroid.braindroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

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
    private TextView vocabWord;
    private RadioGroup definitionGroup;
    private RadioButton definition;
    private Button next;
    private int randomInt, selectedId;
    private Random randomGenerator = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gametwo);

        next =(Button) findViewById(R.id.button);
        vocabWord = (TextView) findViewById(R.id.textView9);
        definitionGroup = (RadioGroup) findViewById(R.id.rg);

        PopulateHash();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus) {
            randomInt = randomGenerator.nextInt(6);
            vocabWord.setText(vocabMap.get(randomInt));
            selectedId = definitionGroup.getCheckedRadioButtonId();
            definition = (RadioButton) findViewById(selectedId);
        }
    }

    public void PopulateHash() {

        vocabMap.put(0,"abhorrent");
        vocabMap.put(1, "abidance");
        vocabMap.put(2, "abjure");
        vocabMap.put(3, "abstinence");
        vocabMap.put(4, "accost");
        vocabMap.put(5, "adjutant");
    }

    public void NextClick(View v)
    {
        randomInt = randomGenerator.nextInt(6);
        vocabWord.setText(vocabMap.get(randomInt));
        selectedId = definitionGroup.getCheckedRadioButtonId();
        definition = (RadioButton) findViewById(selectedId);

    }
}
