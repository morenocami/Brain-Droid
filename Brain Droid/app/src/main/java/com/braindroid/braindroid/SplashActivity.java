package com.braindroid.braindroid;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;


public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        if (getIntent().getBooleanExtra("EXIT", false)) {
			Log.e("SPLASH","FINISHING");
	         finish();
	     	android.os.Process.killProcess(android.os.Process.myPid());
	    }else{
        
	    	

        new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				startActivity(new Intent(SplashActivity.this,BeginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP));
				finish();
				
			}
		}, 3000);
        
	    }
    
    }
    
}