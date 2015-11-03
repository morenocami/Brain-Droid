package com.braindroid.braindroid;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.parse.Parse;

/**
 * Created by Camilo on 11/2/2015.
 */
public class Intro extends AppCompatActivity{

    Frag_AboutApp f1 = new Frag_AboutApp();
    Frag_AboutUs f2 = new Frag_AboutUs();
    FragmentManager fm=getSupportFragmentManager();
    FragmentTransaction t=fm.beginTransaction();
    boolean F1Checked=false;
    boolean F2Checked=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro);

        try {
            Parse.enableLocalDatastore(this);
            Parse.initialize(this, "vuVEXjX02ghorFfG7HLFrZRuVBC43xhHFzvoPRUX", "QmPTK0yZCCUZhPQjW8CHaOtUw75MFZa8FDy4OyBO");
        }
        catch (Exception e){
            // this catch should be changed to catch the exception Parse gives when already initialized!
        }
        final Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

    public void changeFrag(View v)
    {
        switch (v.getId()) {
            case R.id.authors:
                if (F1Checked) {
                    t.replace(R.id.lay, f2);
                    t.addToBackStack(null);
                    t.commit();
                }
                else {
                    f2.setArguments(getIntent().getExtras());
                    t.add(R.id.lay, f2);
                    t.commit();
                    F1Checked=true;
                }
                break;
            case R.id.app:
                if(F1Checked){
                    t.replace(R.id.lay, f1);
                    t.addToBackStack(null);
                    t.commit();
                }
                else{
                    f2.setArguments(getIntent().getExtras());
                    t.add(R.id.lay, f1);
                    t.commit();
                    F1Checked=true;
                }
            default:
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.action_bar, menu);
        menu.findItem(R.id.action_back).setVisible(false);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_forward:
                Intent intent = new Intent(Intro.this, LogIn.class);
                startActivity(intent);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
