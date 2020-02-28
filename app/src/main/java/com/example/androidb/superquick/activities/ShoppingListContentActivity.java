package com.example.androidb.superquick.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.Menu;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.androidb.superquick.General.UserSessionData;
import com.example.androidb.superquick.R;
import com.example.androidb.superquick.adapters.ShoppingListContentAdapter;
import com.example.androidb.superquick.dialogs.CitiesFragmentDialog;
import com.example.androidb.superquick.entities.City;
import com.example.androidb.superquick.entities.Product;
import com.example.androidb.superquick.entities.ProductInShoppingList;
import com.example.androidb.superquick.entities.ShoppingList;
import com.example.androidb.superquick.fragments.ShoppingCartFragment;
import com.example.androidb.superquick.fragments.SuperListFragment;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class ShoppingListContentActivity extends AppCompatActivity {

   public ListView shoppingListContentView;
    List<Product> parsedShoppingListContent;
    List<ProductInShoppingList> productsAmount;
    ShoppingListContentAdapter shoppingListContentAdapter;
    Button editListBtn;
    Button goToCartBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list_content);
        int shoppingListId;
        if(UserSessionData.getInstance().userCurrentShoppingListId==0)
          shoppingListId = UserSessionData.getInstance().userShoppingList.getShoppingListId();
        else
            shoppingListId = UserSessionData.getInstance().userCurrentShoppingListId;

        //call the function
        parsedShoppingListContent=ProductInShoppingList.getProductsOfShoppingList();
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.change_shopping_list_name, menu);
        return super.onCreateOptionsMenu(menu);
    }
    ParseQuery<ShoppingList> queryShoppingListName;
    String shoppingListObjectId;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit_name:
                ShoppingList shoppingList=null;
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                final EditText edittext = new EditText(this);
                 shoppingListObjectId=getIntent().getStringExtra("shoppingListObjectId");
                 queryShoppingListName = ParseQuery.getQuery("ShoppingList");
                try{
                   shoppingList=queryShoppingListName.getFirst();}
                catch (Exception e){}

                edittext.setHint(shoppingList.getShoppingListName());
                alert.setMessage("שם הרשימה החדש");
                alert.setTitle("רשימת קניות");
                alert.setView(edittext);

                alert.setPositiveButton("שמור", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //What ever you want to do with the value
                         final Editable YouEditTextValue = edittext.getText();



                        queryShoppingListName.getInBackground(shoppingListObjectId, new GetCallback<ShoppingList>() {
                            @Override
                            public void done(ShoppingList object, ParseException e) {
                                object.setShoppingListName(YouEditTextValue.toString());
                                object.saveInBackground();

                            }
                        })
                        ;
                    }
                });

                alert.setNegativeButton("חזור", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // what ever you want to do with No option.
                    }
                });

                alert.show();
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }
    public void removeItem(int position){
        productsAmount.remove(position);
        shoppingListContentView.setAdapter(null);
        shoppingListContentAdapter.notifyDataSetChanged();
    }

}
