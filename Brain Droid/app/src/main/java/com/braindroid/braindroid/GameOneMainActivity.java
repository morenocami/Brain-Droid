package com.braindroid.braindroid;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;


public class GameOneMainActivity extends AppCompatActivity {
	FragmentManager fragment;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gameonemainactivity);
		fragment = getSupportFragmentManager();
		fragment.beginTransaction()
				.replace(R.id.container, new GameOneLevelFragment()).commit();
		getSupportActionBar().hide();

	}
}
