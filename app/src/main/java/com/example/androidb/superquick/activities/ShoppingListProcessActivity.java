package com.example.androidb.superquick.activities;

//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentTransaction;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.androidb.superquick.R;
import com.example.androidb.superquick.fragments.ShoppingCartFragment;
import com.example.androidb.superquick.fragments.ShoppingCategoriesFragment;
import com.example.androidb.superquick.fragments.SuperListFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class ShoppingListProcessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list_process);

        //an object which enables to import a fragment to an activity
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();

        //Create and set Android Fragment as default.
        ShoppingCategoriesFragment shoppingCategoriesFragment = new ShoppingCategoriesFragment();
        this.setDefaultFragment(shoppingCategoriesFragment);

        //manages the fragments to display according to the previous activity
        String intentFragment = getIntent().getExtras().getString("specificFragment");
        switch (intentFragment){
            case "ShoppingCategoriesFragment":
                replaceFragment(shoppingCategoriesFragment);
                break;
            case "ShoppingCartFragment":
                ShoppingCartFragment shoppingCartFragment=new ShoppingCartFragment();
                replaceFragment(shoppingCartFragment);
                break;
            case "null":
                    break;
        }}


    // This method is used to set the default fragment that will be shown.
    private void setDefaultFragment(Fragment defaultFragment)
    {
        this.replaceFragment(defaultFragment);
    }
    // Replace current Fragment with the destination Fragment.
    public void replaceFragment(Fragment destFragment)
    {
        // First get FragmentManager object.
        FragmentManager fragmentManager = this.getSupportFragmentManager();

        // Begin Fragment transaction.
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Replace the layout holder with the required Fragment object.
        fragmentTransaction.replace(R.id.fragmentContainer, destFragment);
        fragmentTransaction.addToBackStack(null);
        // Commit the Fragment replace action.
        fragmentTransaction.commit();
    }
    /*public void replaceFragmentsOnActivity(Fragment fragment) {

        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, fragment);

        fragmentTransaction.commit();
    }*/
}
