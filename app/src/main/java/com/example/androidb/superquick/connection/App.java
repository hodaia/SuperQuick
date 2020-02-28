package com.example.androidb.superquick.connection;

import android.app.Application;

import com.example.androidb.superquick.R;
import com.example.androidb.superquick.entities.Category;
import com.example.androidb.superquick.entities.City;
import com.example.androidb.superquick.entities.Column;
import com.example.androidb.superquick.entities.Map;
import com.example.androidb.superquick.entities.Product;
import com.example.androidb.superquick.entities.ProductInShoppingList;
import com.example.androidb.superquick.entities.ProductInSuper;
import com.example.androidb.superquick.entities.Row;
import com.example.androidb.superquick.entities.ShoppingList;
import com.example.androidb.superquick.entities.SubCategory;
import com.example.androidb.superquick.entities.Super;
import com.example.androidb.superquick.entities.Users;
import com.example.androidb.superquick.entities.User;
import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParseObject;

import android.app.Application;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
            ParseObject.registerSubclass(Category.class);
            ParseObject.registerSubclass(Product.class);
            ParseObject.registerSubclass(ProductInShoppingList.class);
            ParseObject.registerSubclass(ProductInSuper.class);
            ParseObject.registerSubclass(ShoppingList.class);
            ParseObject.registerSubclass(SubCategory.class);
            ParseObject.registerSubclass(Super.class);
            ParseObject.registerSubclass(Users.class);
            ParseObject.registerSubclass(User.class);
            ParseObject.registerSubclass(Column.class);
            ParseObject.registerSubclass(Row.class);
            ParseObject.registerSubclass(City.class);
            ParseObject.registerSubclass(Map.class);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.back4app_app_id))
                // if defined
                .clientKey(getString(R.string.back4app_client_key))
                .server(getString(R.string.back4app_server_url))
                .build()

        );
        ParseInstallation.getCurrentInstallation().saveInBackground();

    }
}
