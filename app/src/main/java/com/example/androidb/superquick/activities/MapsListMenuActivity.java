package com.example.androidb.superquick.activities;

//import android.support.v7.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.androidb.superquick.General.UserSessionData;
import com.example.androidb.superquick.R;
import com.example.androidb.superquick.adapters.MapsMenuAdapter;
import com.example.androidb.superquick.adapters.ShoppingListMenuAdapter;
import com.example.androidb.superquick.entities.Map;
import com.example.androidb.superquick.entities.ShoppingList;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MapsListMenuActivity extends AppCompatActivity {

    FloatingActionButton addShoppingListBtn;
    ListView mapsMenuView;
    List<Map> parsedMaps;
    MapsMenuAdapter mapsMenuAdapter;
    static boolean loaded = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_list_menu);

        //call the function
        parsedMaps= Map.getMapsByUserId();

        //call the adapter for the shoppingListMenuView
        mapsMenuView = findViewById(R.id.mapsListMenuView);
        mapsMenuAdapter = new MapsMenuAdapter(parsedMaps,this);
        mapsMenuView.setAdapter(mapsMenuAdapter);
    }
}
