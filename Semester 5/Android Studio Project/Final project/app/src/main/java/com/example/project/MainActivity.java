package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;



import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Thread myThread = new Thread() {
            public void run() {
                try {
                    sleep(3000);
                    //Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                   Intent intent=new Intent(MainActivity.this , screen2.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };myThread.start();

        ImageView background,logo,appName1,appName2;
        LottieAnimationView lottieAnimationView;


            background=findViewById(R.id.background);
            logo=findViewById(R.id.logo);
            appName1=findViewById(R.id.text);
            appName2=findViewById(R.id.text1);
            lottieAnimationView=findViewById(R.id.lottie_main);

            background.animate().translationY(-1600).setDuration(1000).setStartDelay(4000);
            logo.animate().translationY(1400).setDuration(1000).setStartDelay(4000);
            appName1.animate().translationY(1400).setDuration(1000).setStartDelay(4000);
            appName2.animate().translationY(1400).setDuration(1000).setStartDelay(4000);
            lottieAnimationView.animate().translationY(1400).setDuration(1000).setStartDelay(4000);

            



        }
}