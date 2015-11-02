package com.braindroid.braindroid;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.Parse;

/**
 * Created by Camilo on 11/2/2015.
 */
public class Intro extends AppCompatActivity{

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

    public void onAboutUsClick(boolean checked) {
        int color;
        Frag_AboutUs textFragment = (Frag_AboutUs) getSupportFragmentManager().findFragmentById(R.id.Frag_AboutUs);
        if(checked){ color = Color.BLUE;}else{color = Color.BLACK;}
        textFragment.changeText(color);
    }

    public void onAboutAppClick(boolean checked) {
        int color;
        Frag_AboutApp textFragment = (Frag_AboutApp) getSupportFragmentManager().findFragmentById(R.id.Frag_AboutApp);
        if(checked){ color = Color.BLUE;}else{color = Color.BLACK;}
        textFragment.changeText(color);
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
