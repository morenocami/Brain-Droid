package com.braindroid.braindroid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by Camilo on 11/8/2015.
 */
public class Account extends AppCompatActivity{

    private EditText username, password, message;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account);

        final TextView name = (TextView)findViewById(R.id.fullname);
        name.setText(User.getFullName());
        final TextView professor = (TextView)findViewById(R.id.professor);
        professor.setText("You are a student of\nProf. " + User.getProfessor());
        username = (EditText)findViewById(R.id.accUser);
        password = (EditText)findViewById(R.id.accPass);
        message = (EditText)findViewById(R.id.message);

        final Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (username.getText().toString().isEmpty() && password.getText().toString().isEmpty())
                        findViewById(R.id.save).setVisibility(View.GONE);
                    else
                        findViewById(R.id.save).setVisibility(View.VISIBLE);
                }
            }
        });
        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(username.getText().toString().isEmpty() && password.getText().toString().isEmpty())
                        findViewById(R.id.save).setVisibility(View.GONE);
                    else
                        findViewById(R.id.save).setVisibility(View.VISIBLE);
                }
            }
        });
        findViewById(R.id.reports).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent a = new Intent(Account.this, ProgressReports.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(a);
            }
        });
        findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().matches("[a-zA-Z0-9]+") || username.getText().toString().isEmpty()) {
                    if (password.getText().toString().matches("[a-zA-Z0-9]+") || password.getText().toString().isEmpty()) {
                        findViewById(R.id.save).setVisibility(View.GONE);
                        User.save(username, password);
                    }
                    else
                        Toast.makeText(getApplicationContext(), "Password is not alphanumeric. No spaces!", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getApplicationContext(), "Username is not alphanumeric. No spaces!", Toast.LENGTH_SHORT).show();

            }
        });

        findViewById(R.id.sendSMS).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sms = message.getText().toString();
                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage("9546967523", null, sms, null, null);
                    Toast.makeText(getApplicationContext(), "SMS Sent!",
                            Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),
                            "SMS failed, please try again later!",
                            Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }

            }
        });
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