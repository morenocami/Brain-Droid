package com.braindroid.braindroid;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
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


        startTimer();    // testtimer
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

//====================================================================
//Using timer
//====================================================================
    private Handler timer = new Handler();
    private int secondsPassed = 0;

    public void startTimer()
    {
        if (secondsPassed == 0)
        {
            timer.removeCallbacks(updateTimeElasped);
            // tell timer to run call back after 1 second
            timer.postDelayed(updateTimeElasped, 1000);
        }
    }

    public void stopTimer()
    {
        // disable call backs
        timer.removeCallbacks(updateTimeElasped);
    }

    // timer call back when timer is ticked
    private Runnable updateTimeElasped = new Runnable()
    {
        public void run()
        {
            long currentMilliseconds = System.currentTimeMillis();
            ++secondsPassed;
            txtTimer.setText(Integer.toString(secondsPassed));

            // add notification
            timer.postAtTime(this, currentMilliseconds);
            // notify to call back after 1 seconds
            // basically to remain in the timer loop
            timer.postDelayed(updateTimeElasped, 1000);
        }
    };

}