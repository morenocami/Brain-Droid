package com.braindroid.braindroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class GameOneResultActivity extends ActionBarActivity implements OnClickListener {

	Context context;
	Intent intent;
	int level, score;
	ImageButton trygain, menubutton;
	private TextView level_text,tip,score_text;
	private ImageView result_img;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		context = GameOneResultActivity.this;
		setContentView(R.layout.gameoneresultactivity);
		intent = getIntent();
		level = intent.getExtras().getInt(GameOneAppConstants.levelkey);
		score = intent.getExtras().getInt(GameOneAppConstants.scorekey);
		menubutton = (ImageButton) findViewById(R.id.resultmenubutton);
		trygain = (ImageButton) findViewById(R.id.resulttryagainbutton);
		level_text = (TextView) findViewById(R.id.result_level_text);
		score_text = (TextView) findViewById(R.id.result_score_text);
		result_img=(ImageView)findViewById(R.id.result_dia_img);
		tip=(TextView)findViewById(R.id.resulttip_textview);
		menubutton.setOnClickListener(this);
		trygain.setOnClickListener(this);

		level_text.setText("" + context.getResources().getString(R.string.result_level, (((level - 1) / 5) + 1)));
		score_text.setText(""+context.getResources().getString(R.string.result_score,score));
		tip.setText(GameOneGameConstants.generateRandomTip(context));
		result_img.setImageResource(GameOneGameConstants.generateRandomDrawableResource());
		GameOneLevelFragment.newBest=false;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
			case R.id.resulttryagainbutton:

				startMyActivity(this,GameOneMainActivity.class);

				break;

			case R.id.resultmenubutton:

				startMyActivity(this,GameOneBeginActivity.class);

				break;

			default:
				break;
		}
	}

	private <T> void startMyActivity(Activity activity, Class<T> class1) {
		// TODO Auto-generated method stub
		startActivity(new Intent(activity,class1).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP));
		finish();
	}
}
