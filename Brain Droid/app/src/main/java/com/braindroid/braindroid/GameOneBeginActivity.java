package com.braindroid.braindroid;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class GameOneBeginActivity extends Activity implements OnClickListener {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gameonebeginactivity);

		findViewById(R.id.begin).setOnClickListener(this);
		findViewById(R.id.result_help_button).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
			case R.id.begin:

				new Handler().postDelayed(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						startActivity(new Intent(GameOneBeginActivity.this,
								GameOneMainActivity.class)
								.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
										| Intent.FLAG_ACTIVITY_CLEAR_TOP));
						finish();

					}
				}, 1000);
				break;

			case R.id.result_help_button:

				startActivity(new Intent(GameOneBeginActivity.this, GameOneHelpActivity.class));
				finish();
				break;

			default:
				break;
		}

	}

}
