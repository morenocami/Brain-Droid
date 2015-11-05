package com.braindroid.braindroid;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;



import com.parse.Parse;

/**
 * Created by f on 11/3/2015.
 */
public class gameone extends AppCompatActivity {
    private TextView txtCount;
    private TextView txtTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameone);

//====================================================================
//Using external fonts
//====================================================================
        txtCount = (TextView) findViewById(R.id.Count);
        txtTimer = (TextView) findViewById(R.id.Timer);
// set font style for timer and mine count to LCD style
//       Typeface lcdFont = Typeface.createFromAsset(getAssets(),"LCD2B.TTF");
//        txtCount.setTypeface(lcdFont);
 //       txtTimer.setTypeface(lcdFont);
//====================================================================
    }
}