package com.braindroid.braindroid;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by Camilo on 11/11/2015.
 */
public class MathGame extends AppCompatActivity{

    private LinearLayout mathLayout, questionLayout;
    private TextView operand1, operand2, operator, answer, score;
    private ImageView check;
    private int op1, op2, opr, ans;
    private static int count=0;
    private boolean newBest;
    private Random rand = new Random();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mathgame);

        mathLayout = (LinearLayout) findViewById(R.id.mathLayout);
        questionLayout = (LinearLayout) findViewById(R.id.questionLayout);
        operand1 = (TextView) findViewById(R.id.operand1);
        operand2 = (TextView) findViewById(R.id.operand2);
        operator = (TextView) findViewById(R.id.operator);
        answer = (TextView) findViewById(R.id.answerText);
        score = (TextView) findViewById(R.id.score);
        check = (ImageView) findViewById(R.id.mathImage);

        newBest=false;

        final Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

    private void generate(){
        opr=rand.nextInt(4);
        switch (opr) {
            case 0:
                op1=rand.nextInt(100)+1;
                op2=rand.nextInt(100)+1;
                ans = op1 + op2;
                operator.setText("+");
                break;
            case 1:
                op1=rand.nextInt(100)+1;
                op2=rand.nextInt(100)+1;
                ans = op1 - op2;
                operator.setText("-");
                break;
            case 2:
                op1=rand.nextInt(20)+1;
                op2=rand.nextInt(20)+1;
                ans = op1 * op2;
                operator.setText("*");
                break;
            case 3:
                op1=rand.nextInt(100)+1;
                op2=getDividend();
                ans = op1 / op2;
                operator.setText("/");
                break;
            default:
        }
        operand1.setText(Integer.toString(op1));
        operand2.setText(Integer.toString(op2));
        answer.setText("?");
    }

    private int getDividend(){
        op2=rand.nextInt(10)+1;
        while((op1%op2)!=0){
            op2++;
            if(op2>op1){op2=rand.nextInt(10)+1;}
        }
        return op2;
    }

    public void input(View v){
        final Button b = (Button) v;
        if(answer.getText().equals("?")) {
            if(b.getText().equals("+/-")){return;}
            answer.setText(b.getText());
        }
        else if(answer.getText().equals("0"))
            answer.setText(b.getText());
        else if(b.getText().equals("+/-")){
            if(answer.getText().charAt(0)=='-'){return;}
            answer.setText("-"+answer.getText().toString());
        }
        else
            answer.setText(answer.getText().toString()+b.getText().toString());
    }

    public void clear(View v){
        answer.setText("?");
    }

    public void submit(View v){
        if (answer.getText().equals("?"))
            Toast.makeText(getApplicationContext(), "Enter your answer!", Toast.LENGTH_SHORT).show();
        else {
            findViewById(R.id.math_enter).setClickable(false);
            //answer is right
            if (ans==Integer.parseInt(answer.getText().toString())){
                check.setImageResource(R.drawable.correct);
                User.incRight(User.Game.MATH);
                count++;
            }
            //answer is wrong
            else{
                check.setImageResource(R.drawable.incorrect);
                User.incWrong(User.Game.MATH);
                if(count==0){}
                else count--;
            }

            if(User.checkBest(count,User.Game.MATH) && !newBest) {
                Toast.makeText(getApplicationContext(), "New personal best!", Toast.LENGTH_SHORT).show();
                newBest = true;
            }

            score.setText("Score: " + count);
            answer.setVisibility(View.GONE);
            check.setVisibility(View.VISIBLE);
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    answer.setVisibility(View.VISIBLE);
                    check.setVisibility(View.GONE);
                    generate();
                    score.setText("Score: " + count);
                    findViewById(R.id.math_enter).setClickable(true);
                }
            }, 1000);
        }
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
            generate();
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //Handle the back button
        if(keyCode == KeyEvent.KEYCODE_BACK && this==MathGame.this) {
            count = 0;
            final Intent back = new Intent(this, MainMenu.class);
            back.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(back);
            return super.onKeyDown(keyCode, event);
        }
        else
            return super.onKeyDown(keyCode, event);
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
                onKeyDown(KeyEvent.KEYCODE_BACK, new KeyEvent(0, 0));
                return true;

            case R.id.action_forward:
                //final Intent highscores = new Intent(this, HighScores.class);
                //startActivity(highscores);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

}
