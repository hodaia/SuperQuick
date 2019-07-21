package com.example.androidb.superquick.activities;

import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import com.example.androidb.superquick.R;

public class StartMenuActivity extends AppCompatActivity {
    Button shoppingListsBtn;
    Button mapsListBtn;
    ImageButton settingsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_menu);


        shoppingListsBtn = findViewById(R.id.shoppingListsBtn);
        shoppingListsBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(StartMenuActivity.this, ShoppingListsMenuActivity.class);
                startActivity(intent);
            }
        });
        mapsListBtn = findViewById(R.id.mapsListBtn);
        mapsListBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(StartMenuActivity.this, MapsListMenuActivity.class);
                startActivity(intent);
            }
        });
        settingsBtn = findViewById(R.id.settingsBtn);
        settingsBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(StartMenuActivity.this, LoginActivity2.class);
                startActivity(intent);
            }
        });

    }
}
