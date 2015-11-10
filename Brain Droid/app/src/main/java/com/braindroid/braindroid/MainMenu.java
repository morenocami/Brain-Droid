package com.braindroid.braindroid;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Camilo on 11/2/2015.
 */

public class MainMenu extends AppCompatActivity{
    private TextView logo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        final Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


//====================================================================
//Using external fonts
//====================================================================
        logo = (TextView) findViewById(R.id.logo);
// set font style for timer and mine count to LCD style
 //       Typeface lcdFont = Typeface.createFromAsset(getAssets(),"fonts/LCD2B.TTF");
 //       logo.setTypeface(lcdFont);
//====================================================================
    }

    public void enterGame(View v){
        switch (v.getId()){
            case R.id.G1:
                final Intent g1 = new Intent(this,gameone.class);
                startActivity(g1);
                break;
            default:
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user_action_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_back:
                final Intent back = new Intent(this, LogIn.class);
                back.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(back);
                return true;

            case R.id.account:
                final Intent account = new Intent(this, UserAccount.class);
                account.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(account);
                return true;

            case R.id.progress:
                final Intent progress = new Intent(this, UserProgress.class);
                progress.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(progress);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }


}