package com.braindroid.braindroid;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by Camilo on 11/11/2015.
 */
public class MathGame extends AppCompatActivity{

    private LinearLayout mathLayout, questionLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mathgame);

        mathLayout = (LinearLayout) findViewById(R.id.mathLayout);
        questionLayout = (LinearLayout) findViewById(R.id.questionLayout);

        final Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

    public void input(View v){

    }

    public void submit(View v){

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus) {
            final int o = getResources().getConfiguration().orientation;
            if (o == Configuration.ORIENTATION_LANDSCAPE) {
                mathLayout.setOrientation(LinearLayout.HORIZONTAL);
                questionLayout.setOrientation(LinearLayout.VERTICAL);
            }
            else if (o == Configuration.ORIENTATION_PORTRAIT){
                mathLayout.setOrientation(LinearLayout.VERTICAL);
                questionLayout.setOrientation(LinearLayout.HORIZONTAL);
            }
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
                final Intent back = new Intent(this, MainMenu.class);
                back.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(back);
                return true;

            /*case R.id.action_forward:
                final Intent register = new Intent(this, Register.class);
                startActivity(register);
                return true;
            */
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

}
