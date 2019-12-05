package com.example.androidb.superquick.activities;

import android.content.Intent;
import android.os.Bundle;

import com.example.androidb.superquick.General.UserSessionData;
import com.example.androidb.superquick.adapters.ShoppingListMenuAdapter;
import com.example.androidb.superquick.entities.ShoppingList;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ListView;

import com.example.androidb.superquick.R;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class ShoppingListsMenuActivity extends AppCompatActivity {

    FloatingActionButton addShoppingListBtn;
    ListView shoppingListMenuView;
    List<ShoppingList> parsedShoppingList;
    ShoppingListMenuAdapter shoppingListMenuAdapter;
    public List<ShoppingList> userShoppingList;
    static boolean loaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_lists_menu);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        addShoppingListBtn = (FloatingActionButton) findViewById(R.id.addShoppingListBtn);
        addShoppingListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserSessionData.newUserListContent();
                UserSessionData.newUserList();
                Intent intent = new Intent();
                intent.setClass(ShoppingListsMenuActivity.this, ShoppingListProcessActivity.class);
                intent.putExtra("specificFragment", "ShoppingCategoriesFragment");
                intent.putExtra("shoppingListId",UserSessionData.newUserList() );

                startActivity(intent);
            }
        });

        //call the function
        parsedShoppingList=ShoppingList.getShoppingListByUserId();

        //call the adapter for the shoppingListMenuView
        shoppingListMenuView = findViewById(R.id.shoppingListMenuView);
        shoppingListMenuAdapter = new ShoppingListMenuAdapter(parsedShoppingList,this);
        shoppingListMenuView.setAdapter(shoppingListMenuAdapter);

    }
    @Override
    protected void onResume() {
        super.onResume();
        if (!loaded) {
            //First time just set the loaded flag true
            loaded = true;
        } else {
            //Log.i("Resuming", "back to my first activity");

            //Reload data
            parsedShoppingList=new ArrayList<>();
            parsedShoppingList = ShoppingList.getShoppingListByUserId();
            shoppingListMenuAdapter = new ShoppingListMenuAdapter(parsedShoppingList,this);
            shoppingListMenuView.setAdapter(shoppingListMenuAdapter);

        }
    }

}
