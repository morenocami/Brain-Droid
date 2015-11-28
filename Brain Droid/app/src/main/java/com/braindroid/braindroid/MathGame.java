package com.braindroid.braindroid;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
    private int op1, op2, opr, ans, count=0;
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

        final Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

    private void generate(){
        op1=rand.nextInt(12)+1;
        op2=rand.nextInt(12)+1;
        opr=rand.nextInt(4);
        operand1.setText(Integer.toString(op1));
        operand2.setText(Integer.toString(op2));
        answer.setText("?");
        switch (opr) {
            case 0:
                ans = op1 + op2;
                operator.setText("+");
                break;
            case 1:
                ans = op1 - op2;
                operator.setText("-");
                break;
            case 2:
                ans = op1 * op2;
                operator.setText("*");
                break;
            case 3:
                ans = op1 / op2;
                Toast.makeText(getApplicationContext(), "Integer Division! (truncates)", Toast.LENGTH_SHORT).show();
                operator.setText("/");
                break;
            default:
        }
    }

    public void input(View v){
        final Button b = (Button) v;
        if(answer.getText().equals("?")) {
            if(b.getText().equals("+/-")){return;}
            answer.setText(b.getText());
        }
        else if(answer.getText().equals("0"))
            answer.setText(b.getText());
        else if(b.getText().equals("+/-"))
            answer.setText("-"+answer.getText().toString());
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
            if (ans==Integer.parseInt(answer.getText().toString())){
                check.setImageResource(R.drawable.correct);
                User.incRight();
                count++;
            }
            else{
                check.setImageResource(R.drawable.incorrect);
                User.incWrong();
                if(count==0){}
                else count-=1;
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
                final Intent back = new Intent(this, MainMenu.class);
                back.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(back);
                return true;

            /*case R.id.action_forward:
                final Intent register = new Intent(this, Register.class);
                startActivity(register);
                return true;
            */
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

}
