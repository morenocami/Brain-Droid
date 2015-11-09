package com.example.f.test;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.ActionBarActivity;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends ActionBarActivity
implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, SeekBar.OnSeekBarChangeListener {

    private FButton twitterBtn;
    private FButton disabledBtn;
    private TextView shadowHeight;
    private SeekBar shadowHeightBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
     //   twitterBtn = (FButton) findViewById(R.id.f_twitter_button);
     //   disabledBtn = (FButton) findViewById(R.id.disabled_button);
        Button changeColorBtn = (Button) findViewById(R.id.change_color_button);
        ToggleButton shadowSwitch = (ToggleButton) findViewById(R.id.enable_shadow_switch);
        shadowHeightBar = (SeekBar) findViewById(R.id.shadow_height_seekbar);
        shadowHeight = (TextView) findViewById(R.id.shadow_height_value);

        changeColorBtn.setOnClickListener(this);
        shadowSwitch.setOnCheckedChangeListener(this);
        shadowHeightBar.setOnSeekBarChangeListener(this);

        //Config disabled button
        disabledBtn.setButtonColor(getResources().getColor(R.color.fbutton_color_concrete));
        disabledBtn.setShadowEnabled(true);
        disabledBtn.setShadowHeight(5);
        disabledBtn.setCornerRadius(5);
    }




    @Override
    public void onClick(View v) {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        twitterBtn.setShadowEnabled(isChecked);
        updateShadowHeight(shadowHeightBar.getProgress());
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        shadowHeight.setText(progress + "dp");
        updateShadowHeight(progress);
    }

    private void updateShadowHeight(int height) {
        //Convert from dp to pixel
        int shadowHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, height, getResources().getDisplayMetrics());
        twitterBtn.setShadowHeight(shadowHeight);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
