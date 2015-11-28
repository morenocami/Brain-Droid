package com.braindroid.braindroid;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;

public class BeginActivity extends Activity implements OnClickListener {

	Builder alert;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.begintestfragment);

		Log.i(User.TAG, "BeginActivity.java");

		alert = new AlertDialog.Builder(this)
				.setIcon(android.R.drawable.ic_dialog_alert)
				.setTitle(R.string.quit)
				.setMessage(R.string.really_quit)
				.setNegativeButton(R.string.no, null)
				.setPositiveButton(R.string.yes,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
												int which) {

								Intent intent = new Intent(
										getApplicationContext(),
										SplashActivity.class);
								intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
								intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
								intent.putExtra("EXIT", true);
								startActivity(intent);
								// ProfileActivity1.this.finish();
								Log.e("CHECK", "GOING TO LOGIN");
								finish();
							}
						});
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
						startActivity(new Intent(BeginActivity.this,
								MainActivity.class)
								.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
										| Intent.FLAG_ACTIVITY_CLEAR_TOP));
						finish();

					}
				}, 1000);
				break;

			case R.id.result_help_button:

				startActivity(new Intent(BeginActivity.this, HelpActivity.class));
				finish();

				break;

			default:
				break;
		}

	}

}
