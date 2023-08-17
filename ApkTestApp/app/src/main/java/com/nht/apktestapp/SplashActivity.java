package com.nht.apktestapp;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    TextView txtName, txtSlogan;
    ImageView imgLogo;
    View topView1, topView2, topView3, bottomView1, bottomView2, bottomView3;

    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                |View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                |View.SYSTEM_UI_FLAG_FULLSCREEN
                |View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                |View.SYSTEM_UI_FLAG_FULLSCREEN
                |View.SYSTEM_UI_FLAG_IMMERSIVE);


        txtName = findViewById(R.id.txtName);
        txtSlogan = findViewById(R.id.txtSlogan);
        imgLogo = findViewById(R.id.imgLogo);
        txtName.setVisibility(View.INVISIBLE);
        txtSlogan.setVisibility(View.INVISIBLE);
        imgLogo.setVisibility(View.INVISIBLE);

        topView1 = findViewById(R.id.topView1);
        topView2 = findViewById(R.id.topView2);
        topView3 = findViewById(R.id.topView3);

        bottomView1 = findViewById(R.id.bottomView1);
        bottomView2 = findViewById(R.id.bottomView2);
        bottomView3 = findViewById(R.id.bottomView3);

        Animation logoAnimation = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.zoom_animation);
        Animation nameAnimation = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.zoom_animation);

        Animation topView1Animation = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.top_views_animation);
        Animation topView2Animation = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.top_views_animation);
        Animation topView3Animation = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.top_views_animation);

        Animation bottomView1Animation = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.bottom_views_animation);
        Animation bottomView2Animation = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.bottom_views_animation);
        Animation bottomView3Animation = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.bottom_views_animation);

        topView1.startAnimation(topView1Animation);
        bottomView1.startAnimation(bottomView1Animation);

        topView1Animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                topView2.setVisibility(View.VISIBLE);
                bottomView2.setVisibility(View.VISIBLE);

                topView2.startAnimation(topView2Animation);
                bottomView2.startAnimation(bottomView2Animation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        topView2Animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                topView3.setVisibility(View.VISIBLE);
                bottomView3.setVisibility(View.VISIBLE);

                topView3.startAnimation(topView3Animation);
                bottomView3.startAnimation(bottomView3Animation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        topView3Animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imgLogo.setVisibility(View.VISIBLE);
                imgLogo.startAnimation(logoAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        logoAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                txtName.setVisibility(View.VISIBLE);
                txtName.startAnimation(nameAnimation);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        nameAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                txtSlogan.setVisibility(View.VISIBLE);
                final String txtAnimate = txtSlogan.getText().toString();
                txtSlogan.setText("");
                count = 0;

                new CountDownTimer(txtAnimate.length() * 100, 100){

                    @Override
                    public void onTick(long millisUntilFinished) {
                        txtSlogan.setText(txtSlogan.getText().toString() +
                                txtAnimate.charAt(count));
                        count++;
                    }

                    @Override
                    public void onFinish() {
                        //chuyển qua main
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }, 500); // Thời gian chuyển đổi là 4 gi
                    }
                }.start();


            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }
}