package com.braindroid.braindroid;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Camilo on 11/8/2015.
 */
public class ProgressReports extends AppCompatActivity{

    private View mathT, memoryT, vocabT, reportLay;
    private TextView best, right, wrong, ratio;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress_reports);

        mathT = (TextView) findViewById(R.id.mathTab);
        memoryT = (TextView) findViewById(R.id.memoryTab);
        vocabT = (TextView) findViewById(R.id.vocabTab);
        reportLay = (LinearLayout) findViewById(R.id.reportLay);
        best = (TextView) findViewById(R.id.bestNum);
        right = (TextView) findViewById(R.id.rightNum);
        wrong = (TextView) findViewById(R.id.wrongNum);
        ratio = (TextView) findViewById(R.id.ratioNum);


        final Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

    public void tab(View v){
        mathT.setBackgroundColor(Color.rgb(0,120,150));
        memoryT.setBackgroundColor(Color.rgb(50,150,0));
        vocabT.setBackgroundColor(Color.rgb(100, 0, 150));
        switch (v.getId()){
            case R.id.mathTab:
                mathT.setBackgroundColor(Color.rgb(200,225,250));
                reportLay.setBackgroundColor(Color.rgb(200, 225, 250));
                best.setText(Integer.toString(User.getBest(User.Game.MATH)));
                right.setText(Integer.toString(User.getRight(User.Game.MATH)));
                wrong.setText(Integer.toString(User.getWrong(User.Game.MATH)));
                ratio.setText(User.getRatio(User.Game.MATH));
                break;
            case R.id.memoryTab:
                memoryT.setBackgroundColor(Color.rgb(200,250,200));
                reportLay.setBackgroundColor(Color.rgb(200,250,200));
                best.setText(Integer.toString(User.getBest(User.Game.MEMORY)));
                right.setText(Integer.toString(User.getRight(User.Game.MEMORY)));
                wrong.setText(Integer.toString(User.getWrong(User.Game.MEMORY)));
                ratio.setText(User.getRatio(User.Game.MEMORY));
                break;
            case R.id.vocabTab:
                vocabT.setBackgroundColor(Color.rgb(200,120,250));
                reportLay.setBackgroundColor(Color.rgb(200,120,250));
                best.setText(Integer.toString(User.getBest(User.Game.VOCAB)));
                right.setText(Integer.toString(User.getRight(User.Game.VOCAB)));
                wrong.setText(Integer.toString(User.getWrong(User.Game.VOCAB)));
                ratio.setText(User.getRatio(User.Game.VOCAB));
                break;
            default:
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus) {
            tab(mathT);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.action_bar, menu);
        menu.findItem(R.id.action_forward).setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_back:
                final Intent intent = new Intent(this, MainMenu.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
