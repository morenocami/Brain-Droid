package com.braindroid.braindroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by Camilo on 11/2/2015.
 */

public class Register extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final String[] paths = {"Matthew", "Robert", "Plugues"};
    private EditText username, password, firstname, lastname;
    private String teacher;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        firstname = (EditText) findViewById(R.id.firstname);
        lastname = (EditText) findViewById(R.id.lastname);

        Spinner spinner = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(Register.this,
                android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        final Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

    private void register(){
        final ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("User");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> users, ParseException e) {
                if (e == null) {
                    boolean match = false;
                    for (ParseObject x : users) {
                        if (x.getString("username").equalsIgnoreCase(username.getText().toString())) {
                            Toast.makeText(getApplicationContext(), "Username is taken, try another.", Toast.LENGTH_SHORT).show();
                            match = true;
                        }
                    }
                    if (!match) {
                        LogIn.user = new ParseObject("User");
                        LogIn.user.put("username", username.getText().toString());
                        LogIn.user.put("password", password.getText().toString());
                        LogIn.user.put("firstname", firstname.getText().toString());
                        LogIn.user.put("lastname", lastname.getText().toString());
                        LogIn.user.put("studentOf", teacher);
                        LogIn.user.put("mathBest", 0);
                        LogIn.user.put("mathRight", 0);
                        LogIn.user.put("mathWrong", 0);
                        LogIn.user.put("memoryBest", 0);
                        LogIn.user.put("memoryRight", 0);
                        LogIn.user.put("memoryWrong", 0);
                        LogIn.user.put("vocabBest", 0);
                        LogIn.user.put("vocabRight", 0);
                        LogIn.user.put("vocabWrong", 0);
                        LogIn.user.saveInBackground();
                        User.userFound(LogIn.user);
                        final Intent next = new Intent(Register.this, MainMenu.class);
                        startActivity(next);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "There was an error. Please try again.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
        switch (position) {
            case 0:
                teacher="Matthew";
                break;
            case 1:
                teacher="Robert";
                break;
            case 2:
                teacher="Plugues";
                break;
            default:
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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
                final Intent back = new Intent(this, LogIn.class);
                startActivity(back);
                return true;

            case R.id.action_forward:
                if(firstname.getText().toString().isEmpty())
                    Toast.makeText(getApplicationContext(), "Fill in all fields", Toast.LENGTH_SHORT).show();
                else if(lastname.getText().toString().isEmpty())
                    Toast.makeText(getApplicationContext(), "Fill in all fields", Toast.LENGTH_SHORT).show();
                else if(username.getText().toString().isEmpty())
                    Toast.makeText(getApplicationContext(), "Fill in all fields", Toast.LENGTH_SHORT).show();
                else if(password.getText().toString().isEmpty())
                    Toast.makeText(getApplicationContext(), "Fill in all fields", Toast.LENGTH_SHORT).show();
                else if(username.getText().toString().matches("[a-zA-Z0-9]+")) {
                    if(password.getText().toString().matches("[a-zA-Z0-9]+")) {
                        register();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Password is not alphanumeric. No spaces!", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                }
                else
                    Toast.makeText(getApplicationContext(), "Username is not alphanumeric. No spaces!", Toast.LENGTH_SHORT).show();

                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
