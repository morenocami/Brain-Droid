package com.braindroid.braindroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.parse.Parse;

/**
 * Created by Camilo on 11/2/2015.
 */
public class Intro extends AppCompatActivity{

    android.support.v4.app.Fragment f;
    Frag_AboutUs f2 = new Frag_AboutUs();
    FragmentManager fm = getSupportFragmentManager();
    boolean Checked=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro);

        final Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

    public void changeFrag(View v)
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.action_bar, menu);
        menu.findItem(R.id.action_back).setVisible(true);
        menu.findItem(R.id.action_forward).setVisible(false);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_back:
                Intent intent = new Intent(Intro.this, LogIn.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
