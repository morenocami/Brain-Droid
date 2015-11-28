package com.braindroid.braindroid;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;


public class GameOneMainActivity extends ActionBarActivity  {
	FragmentManager fragment;
	Builder alert;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gameonemainactivity);
		fragment = getSupportFragmentManager();
		fragment.beginTransaction()
				.replace(R.id.container, new GameOneLevelFragment()).commit();
		getSupportActionBar().hide();


		alert = new AlertDialog.Builder(this)
				.setIcon(android.R.drawable.ic_dialog_alert)
				.setTitle(R.string.game_quit)
				.setMessage(R.string.game_really_quit)
				.setNegativeButton(R.string.no, null)
				.setPositiveButton(R.string.yes,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
												int which) {
								startActivity(new Intent(GameOneMainActivity.this,GameOneBeginActivity.class)
										.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP));
								finish();
							}
						});
	}
}
