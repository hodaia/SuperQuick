package com.example.androidb.superquick.activities;

import android.content.Intent;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.androidb.superquick.General.UserSessionData;
import com.example.androidb.superquick.R;
import com.example.androidb.superquick.adapters.ShoppingListContentAdapter;
import com.example.androidb.superquick.dialogs.CitiesFragmentDialog;
import com.example.androidb.superquick.entities.Product;
import com.example.androidb.superquick.entities.ProductInShoppingList;
import com.example.androidb.superquick.fragments.ShoppingCartFragment;
import com.example.androidb.superquick.fragments.SuperListFragment;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class ShoppingListContentActivity extends AppCompatActivity {

    ListView shoppingListContentView;
    List<Product> parsedShoppingListContent;
    List<ProductInShoppingList> productsAmount;
    ShoppingListContentAdapter shoppingListContentAdapter;
    Button editListBtn;
    Button goToCartBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list_content);

        final int shoppingListId = UserSessionData.getInstance().userCurrentShoppingListId;
        //call the function
        parsedShoppingListContent=ProductInShoppingList.getProductsOfShoppingList(shoppingListId);
        productsAmount=ProductInShoppingList.getProductsInShoppingList();
        //call the adapter for the shoppingList
        shoppingListContentView = findViewById(R.id.shoppingListContentView);
        shoppingListContentAdapter = new ShoppingListContentAdapter(parsedShoppingListContent,productsAmount,this);
        shoppingListContentView.setAdapter(shoppingListContentAdapter);

        goToCartBtn = findViewById(R.id.goToCartBtn);
        goToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(UserSessionData.getInstance().user.isPermanentCity())
                {
                    UserSessionData.getInstance().userCityId=UserSessionData.getInstance().user.getUser_cityId();
                    Intent intent = new Intent();
                    intent.setClass(ShoppingListContentActivity.this, ShoppingListProcessActivity.class);
                    intent.putExtra("specificFragment", "SuperListFragment");
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent();
                    intent.setClass(ShoppingListContentActivity.this, ShoppingListProcessActivity.class);
                    intent.putExtra("specificFragment", "CityDialog");
                    startActivity(intent);
                }
            }
        });

        editListBtn = findViewById(R.id.editListBtn);
        editListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ShoppingListContentActivity.this, ShoppingListProcessActivity.class);
                intent.putExtra("specificFragment", "ShoppingCategoriesFragment");
                startActivity(intent);
            }
        });
    }
    @Override
    public void onBackPressed()
    {

        Intent i = new Intent(this,ShoppingListsMenuActivity.class );
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }



}
