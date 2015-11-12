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

    HashMap<String, Integer> VocabMap = new HashMap<String, Integer>();
    TextView VocabWord;
    RadioGroup DefinitionGroup;
    RadioButton Definition;
    Button Next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gametwo);


        Next =(Button) findViewById(R.id.button);
        VocabWord = (TextView) findViewById(R.id.textView9);
        DefinitionGroup = (RadioGroup) findViewById(R.id.rg);

        PopulateHash();
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(6);
        VocabWord.setText(VocabMap.get(randomInt));
        int selectedId = DefinitionGroup.getCheckedRadioButtonId();
        Definition = (RadioButton) findViewById(selectedId);



        }


    public void PopulateHash() {

        VocabMap.put("abhorrent", new Integer(0));
        VocabMap.put("abidance", new Integer(1));
        VocabMap.put("abjure", new Integer(2));
        VocabMap.put("abstinence", new Integer(3));
        VocabMap.put("accost", new Integer(4));
        VocabMap.put("adjutant", new Integer(5));
    }

    public void NextClick(View v)
    {
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(6);
        VocabWord.setText(VocabMap.get(randomInt));
        int selectedId = DefinitionGroup.getCheckedRadioButtonId();
        Definition = (RadioButton) findViewById(selectedId);

    }
}
