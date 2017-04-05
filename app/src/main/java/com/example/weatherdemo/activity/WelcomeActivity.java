package com.example.weatherdemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.weatherdemo.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * author: daxiong9527
 * mail : 15570350453@163.com
 */

public class WelcomeActivity extends Activity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);
                startActivity(intent);
            }
        },2000);
    }
}
