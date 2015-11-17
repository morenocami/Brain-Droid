package com.braindroid.braindroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by Camilo on 11/2/2015.
 */

public class LogIn extends AppCompatActivity{
    private EditText username;
    private EditText password;
    static ParseObject user;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);

        try {
            Parse.enableLocalDatastore(this);
            Parse.initialize(this, "vuVEXjX02ghorFfG7HLFrZRuVBC43xhHFzvoPRUX", "QmPTK0yZCCUZhPQjW8CHaOtUw75MFZa8FDy4OyBO");
        }
        catch (Exception e){
            // this catch should be changed to catch the exception Parse gives when already initialized!
        }

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        final Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

    public void login(View v){
        if(username.getText().toString().isEmpty())
            Toast.makeText(getApplicationContext(), "Enter your username.", Toast.LENGTH_SHORT).show();
        else if(password.getText().toString().isEmpty())
            Toast.makeText(getApplicationContext(), "Enter your password.", Toast.LENGTH_SHORT).show();
        else{
            final Button button = (Button)findViewById(R.id.login);
            if(!username.getText().toString().matches("[a-zA-Z0-9]+")) {
                Toast.makeText(getApplicationContext(), "Username is not alphanumeric. No spaces!", Toast.LENGTH_SHORT).show();
            }
            else {
                button.setEnabled(false);
                button.setText("LOADING...");
                final ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("User");

                query.findInBackground(new FindCallback<ParseObject>() {
                    public void done(List<ParseObject> users, ParseException e) {
                        if (e == null) {
                            boolean matches = false;
                            for (int x = 0; x < users.size(); x++) {
                                if (users.get(x).getString("username").equalsIgnoreCase(username.getText().toString())) {
                                    if (users.get(x).getString("password").equals(password.getText().toString())) {
                                        matches = true;
                                        user = users.get(x);
                                        User.userFound(user);
                                        break;
                                    }
                                }
                            }
                            if (matches) {
                                Intent enter = new Intent(LogIn.this, MainMenu.class);
                                startActivity(enter);
                            }
                            else {
                                Toast.makeText(getApplicationContext(), "Username/Password combo incorrect.", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "There was an error. Please try again.", Toast.LENGTH_SHORT).show();
                        }
                        button.setEnabled(true);
                        button.setText("Login");
                        password.setText("");
                    }
                });
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.action_bar, menu);
        menu.findItem(R.id.action_back).setVisible(true);
        menu.findItem(R.id.action_forward).setVisible(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_back:
                final Intent back = new Intent(this, Intro.class);
                back.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(back);
                return true;

            case R.id.action_forward:
                final Intent register = new Intent(this, Register.class);
                startActivity(register);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
