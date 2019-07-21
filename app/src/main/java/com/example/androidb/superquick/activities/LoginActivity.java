package com.example.androidb.superquick.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;

import android.content.Intent;
import android.os.Handler;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidb.superquick.R;

public class LoginActivity extends AppCompatActivity{

    Button loginSubmitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginSubmitBtn = findViewById(R.id.loginSubmitBtn);
        loginSubmitBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                        Intent intent=new Intent();
                        intent.setClass(LoginActivity.this,StartMenuActivity.class);
                        startActivity(intent);
            }
        });
    }


}
