package com.example.androidb.superquick.activities;

import android.content.Intent;
import android.os.Handler;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidb.superquick.General.UserSessionData;
import com.example.androidb.superquick.R;
import com.example.androidb.superquick.entities.Super;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent();

                if(UserSessionData.instanceExist()) {
                    intent.setClass(SplashActivity.this, StartMenuActivity.class);
                }
                else {
                    intent.setClass(SplashActivity.this, LoginActivity2.class);
                }
                startActivity(intent);
                finish();

            }
        },2*1000);
    }
}
