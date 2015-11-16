package com.braindroid.braindroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;

public class HelpActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.helplayout);
		getSupportActionBar().hide();

		
		

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub

		if (keyCode == KeyEvent.KEYCODE_BACK) {
			startMyActivity(this, BeginActivity.class);
		}

		return super.onKeyDown(keyCode, event);

	}

	private <T> void startMyActivity(Activity activity, Class<T> class1) {
		// TODO Auto-generated method stub
		startActivity(new Intent(activity, class1)
				.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
						| Intent.FLAG_ACTIVITY_CLEAR_TOP));
		finish();

	}

}
