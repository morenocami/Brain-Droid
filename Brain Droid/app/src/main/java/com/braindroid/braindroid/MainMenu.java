package com.braindroid.braindroid;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by Camilo on 11/2/2015.
 */

public class MainMenu extends AppCompatActivity{
    private TextView logo;
    private ImageButton preview;
    private Button G1, G2, G3;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        final Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        preview = (ImageButton) findViewById(R.id.preview);
        G1 = (Button) findViewById(R.id.G1);
        G2 = (Button) findViewById(R.id.G2);
        G3 = (Button) findViewById(R.id.G3);
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
        logo.setVisibility(View.GONE);
        switch (v.getId()){
            case R.id.G1:
                if(G1.getText().equals("Math")){
                    G1.setText("START!");
                    preview.setImageResource(R.drawable.mathgame_preview);
                }
                else{
                    final Intent g1 = new Intent(this,MathGame.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
                            | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(g1);
                    G1.setText("Math");
                }
                G2.setText("Memory");
                G3.setText("Vocab");
                break;
            case R.id.G2:
                if(G2.getText().equals("Memory")) {
                    G2.setText("START!");
                    preview.setImageResource(R.drawable.memorygame_preview);
                }
                else{
                    final Intent g2 = new Intent(this,BeginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
                            | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(g2);
                    G2.setText("Memory");
                }
                G1.setText("Math");
                G3.setText("Vocab");
                break;
            case R.id.G3:
                if(G3.getText().equals("Vocab")) {
                    G3.setText("START!");
                    preview.setImageResource(R.drawable.vocabpreview);
                }
                else{
                    final Intent g3 = new Intent(this,GameTwo.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
                            | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(g3);
                    G3.setText("Vocab");
                }
                //final Intent g3 = new Intent(this,VocabGame.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                //startActivity(g3);
                G1.setText("Math");
                G2.setText("Memory");
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
                back.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
                        | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(back);
                return true;

            case R.id.account:
                final Intent account = new Intent(this, UserAccount.class);
                account.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
                        | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(account);
                return true;

            case R.id.progress:
                final Intent progress = new Intent(this, UserProgress.class);
                progress.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
                        | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(progress);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }


}