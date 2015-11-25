package com.braindroid.braindroid;

import android.content.Intent;
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
public class UserProgress extends AppCompatActivity{

    private View mathT, memoryT, vocabT, reportLay;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_progress);

        mathT = (TextView) findViewById(R.id.mathTab);
        mathT.setBackgroundColor(Color.rgb(197,208,241));
        memoryT = (TextView) findViewById(R.id.memoryTab);
        vocabT = (TextView) findViewById(R.id.vocabTab);
        reportLay = (LinearLayout) findViewById(R.id.reportLay);

        final Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

    public void tab(View v){
        mathT.setBackgroundColor(Color.rgb(33,36,217));
        memoryT.setBackgroundColor(Color.rgb(33,36,217));
        vocabT.setBackgroundColor(Color.rgb(33,36,217));
        switch (v.getId()){
            case R.id.mathTab:
                mathT.setBackgroundColor(Color.rgb(197,208,241));
                break;
            case R.id.memoryTab:
                memoryT.setBackgroundColor(Color.rgb(197,208,241));
                break;
            case R.id.vocabTab:
                vocabT.setBackgroundColor(Color.rgb(197,208,241));
                break;
            default:
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
