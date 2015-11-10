package com.braindroid.braindroid;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Camilo on 11/9/2015.
 */
public class Splash extends AppCompatActivity {

    private View fau, logo, team;
    private int animDuration;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_intro);

        fau = findViewById(R.id.fauView);
        logo = findViewById(R.id.logoView);
        team = findViewById(R.id.teamView);

        fau.setVisibility(View.VISIBLE);
        logo.setVisibility(View.GONE);
        team.setVisibility(View.GONE);

        animDuration = getResources().getInteger(
                android.R.integer.config_longAnimTime)*3;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        crossFade1();
    }

    private void crossFade1(){
        fau.setVisibility(View.VISIBLE);
        fau.animate().alpha(1f).setDuration(animDuration).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                fau.animate().alpha(0f).setDuration(animDuration).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        fau.setVisibility(View.GONE);
                        crossFade2();
                    }
                });
            }
        });
    }

    private void crossFade2(){
        logo.setVisibility(View.VISIBLE);
        logo.animate().alpha(1f).setDuration(animDuration).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                logo.animate().alpha(0f).setDuration(animDuration).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        logo.setVisibility(View.GONE);
                        crossFade3();
                    }
                });
            }
        });
    }

    private void crossFade3(){
        final Intent out = new Intent(this, Intro.class);
        team.setVisibility(View.VISIBLE);
        team.animate().alpha(1f).setDuration(animDuration).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                team.animate().alpha(0f).setDuration(animDuration).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        team.setVisibility(View.GONE);
                        startActivity(out);
                    }
                });
            }
        });
    }
}
