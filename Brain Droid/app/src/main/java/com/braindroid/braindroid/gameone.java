package com.braindroid.braindroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.parse.Parse;

/**
 * Created by f on 11/3/2015.
 */
public class gameone extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameone);

        final Toolbar myToolbar = (Toolbar) findViewById(R.id.gameOne_toolbar);
        setSupportActionBar(myToolbar);


    }
}
