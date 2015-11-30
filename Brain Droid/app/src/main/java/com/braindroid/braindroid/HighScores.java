package com.braindroid.braindroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Camilo on 11/30/2015.
 */
public class HighScores extends AppCompatActivity{

    private TextView title;
    private ArrayList<String> scoreList;
    private ArrayAdapter<String> adapter;
    private ListView lv;
    private Menu menu;
    private Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.highscores);

        i = getIntent();
        title = (TextView)findViewById(R.id.scores_title);

        lv = (ListView)findViewById(R.id.listView);
        scoreList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(HighScores.this,android.R.layout.simple_list_item_1, scoreList);
        lv.setAdapter(adapter);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            final String gameBest = i.getStringExtra("game")+"Best";

            Collections.sort(LogIn.listOfUsers, new Comparator<ParseObject>() {
                @Override
                public int compare(ParseObject po1, ParseObject po2) {
                    return Integer.valueOf(po2.getInt(gameBest)).compareTo(po1.getInt(gameBest));
                }
            });
            adapter.clear();
            int rank=1;
            for(ParseObject po: LogIn.listOfUsers){
                scoreList.add(Integer.toString(rank++) + ")\t" + po.getString("username")+"\t\t\tScore: "+po.getInt(gameBest));
            }
            adapter.notifyDataSetChanged();
            lv.invalidateViews();
            lv.refreshDrawableState();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu=menu;
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.highscore_toolbar, menu);
        menu.findItem(R.id.mathScores).setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_back:
                final Intent back = new Intent(this, MainMenu.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(back);
                return true;

            case R.id.mathScores:
                menu.findItem(R.id.mathScores).setVisible(false);
                menu.findItem(R.id.memoryScores).setVisible(true);
                menu.findItem(R.id.vocabScores).setVisible(true);
                title.setText("MATH");
                i.putExtra("game","math");
                return true;

            case R.id.memoryScores:
                menu.findItem(R.id.mathScores).setVisible(true);
                menu.findItem(R.id.memoryScores).setVisible(false);
                menu.findItem(R.id.vocabScores).setVisible(true);
                title.setText("MEMORY");
                i.putExtra("game","memory");
                return true;

            case R.id.vocabScores:
                menu.findItem(R.id.mathScores).setVisible(true);
                menu.findItem(R.id.memoryScores).setVisible(true);
                menu.findItem(R.id.vocabScores).setVisible(false);
                title.setText("VOCAB");
                i.putExtra("game","vocab");
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }
}
