package com.braindroid.braindroid;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class HelpActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.helpctivity);
		getSupportActionBar().hide();
	}

}
