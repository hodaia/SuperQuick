package com.example.androidb.superquick.activities;

import android.content.ClipData;
import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.androidb.superquick.R;
import com.example.androidb.superquick.dialogs.EditUserDialogFragment;

public class StartMenuActivity extends AppCompatActivity {
    Button shoppingListsBtn;
    Button mapsListBtn;
    View edit_user;
    View change_account;

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

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.start_menu_acton_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.change_account:
                Intent intent = new Intent();
                intent.setClass(StartMenuActivity.this, LoginActivity2.class);
                startActivity(intent);
                return true;
            case R.id.edit_user:
                FragmentManager ft = getSupportFragmentManager();
                EditUserDialogFragment editUserDialogFragment = new EditUserDialogFragment(true);
                editUserDialogFragment.show(ft, "i");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
