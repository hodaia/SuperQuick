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
import com.example.androidb.superquick.entities.Product;
import com.example.androidb.superquick.entities.ProductInShoppingList;
import com.example.androidb.superquick.fragments.ShoppingCartFragment;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import androidx.appcompat.app.AppCompatActivity;
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

        final int shoppingListId = getIntent().getExtras().getInt("shoppingListId");
        //call the function
        parsedShoppingListContent=ProductInShoppingList.getProductsOfShoppingList(shoppingListId);
        productsAmount=ProductInShoppingList.getProductsInShoppingList(shoppingListId);
        //call the adapter for the shoppingList
        shoppingListContentView = findViewById(R.id.shoppingListContentView);
        shoppingListContentAdapter = new ShoppingListContentAdapter(parsedShoppingListContent,productsAmount,this);
        shoppingListContentView.setAdapter(shoppingListContentAdapter);

        goToCartBtn = findViewById(R.id.goToCartBtn);
        goToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ShoppingListContentActivity.this, ShoppingListProcessActivity.class);
                intent.putExtra("specificFragment", "ShoppingCartFragment");
                startActivity(intent);
            }
        });

        editListBtn = findViewById(R.id.editListBtn);
        editListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ShoppingListContentActivity.this, ShoppingListProcessActivity.class);
                intent.putExtra("specificFragment", "ShoppingCategoriesFragment");
                intent.putExtra("shoppingListId", shoppingListId);
                startActivity(intent);
            }
        });
    }

}
