package com.braindroid.braindroid;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {
	FragmentManager fragment;
	Button button1, button2;
	private Leveltwo lev;
	private Builder alert;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment2);
		fragment = getSupportFragmentManager();
		fragment.beginTransaction()
				.replace(R.id.container, new LeveloneFragment()).commit();
		getSupportActionBar().hide();


		alert = new Builder(this)
				.setIcon(android.R.drawable.ic_dialog_alert)
				.setTitle(R.string.game_quit)
				.setMessage(R.string.game_really_quit)
				.setNegativeButton(R.string.no, null)
				.setPositiveButton(R.string.yes,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
												int which) {startActivity(new Intent(MainActivity.this,BeginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP));
								finish();
							}
						});


	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub


		if(keyCode==KeyEvent.KEYCODE_BACK)
		{
			alert.show();

		}


		return super.onKeyDown(keyCode, event);


	}
	private <T> void startMyActivity(Activity activity, Class<T> class1) {
		// TODO Auto-generated method stub
		startActivity(new Intent(activity,class1).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP));

	}





}
