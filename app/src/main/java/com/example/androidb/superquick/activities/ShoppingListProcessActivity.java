package com.example.androidb.superquick.activities;

//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentTransaction;
//import android.support.v7.app.AppCompatActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.androidb.superquick.R;
import com.example.androidb.superquick.dialogs.CitiesFragmentDialog;
import com.example.androidb.superquick.entities.ShoppingList;
import com.example.androidb.superquick.fragments.ShoppingCartFragment;
import com.example.androidb.superquick.fragments.ShoppingCategoriesFragment;
import com.example.androidb.superquick.fragments.SuperListFragment;
import com.example.androidb.superquick.fragments.SuperMapFragment;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class ShoppingListProcessActivity extends AppCompatActivity {

    ImageView mapsIcon;
    ImageView shekelIcon;
    ImageView superIcon;
    ImageView cartIcon;
    Boolean isMapsFragment ;
    private Menu menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list_process);

        //an object which enables to import a fragment to an activity
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        isMapsFragment=false;
        //Create and set Android Fragment as default.
        ShoppingCategoriesFragment shoppingCategoriesFragment = new ShoppingCategoriesFragment();
        this.setDefaultFragment(shoppingCategoriesFragment);

        //manages the fragments to display according to the previous activity
        String intentFragment = getIntent().getExtras().getString("specificFragment");
        switch (intentFragment) {
            case "ShoppingCategoriesFragment":
                replaceFragment(shoppingCategoriesFragment);
                break;
            case "ShoppingCartFragment":
                ShoppingCartFragment shoppingCartFragment = new ShoppingCartFragment();
                replaceFragment(shoppingCartFragment);
                break;
            case "SuperListFragment":
                SuperListFragment superListFragment = new SuperListFragment();
                replaceFragment(superListFragment);
                break;
            case "CityDialog":
                FragmentManager ft = getSupportFragmentManager();
                CitiesFragmentDialog citiesFragmentDialog = new CitiesFragmentDialog();
                citiesFragmentDialog.show(ft, "i");
                break;
            case "null":
                break;
        }
    }

    @Override
    public void onBackPressed() {

        if (getFragmentManager().getBackStackEntryCount() == 0) {
            this.finish();
        } else {
            //   String tag = getSupportFragmentManager().getBackStackEntryAt(getSupportFragmentManager().getBackStackEntryCount() - 1).getName();
            getFragmentManager().popBackStack();
        }
    }

    // This method is used to set the default fragment that will be shown.
    private void setDefaultFragment(Fragment defaultFragment) {
        this.replaceFragment(defaultFragment);
    }

    // Replace current Fragment with the destination Fragment.
    // setting the icon according to the process
    public void replaceFragment(Fragment destFragment) {
        mapsIcon = findViewById(R.id.mapsIcon);
        shekelIcon = findViewById(R.id.shekelIcon);
        superIcon = findViewById(R.id.superIcon);
        cartIcon = findViewById(R.id.cartIcon);

        if (destFragment instanceof ShoppingCategoriesFragment) {
            cartIcon.setImageResource(R.drawable.cart);
            superIcon.setImageResource(R.drawable.super_grey);
            shekelIcon.setImageResource(R.drawable.shekel_grey);
            mapsIcon.setImageResource(R.drawable.map_grey);
        }
        else if (destFragment instanceof SuperListFragment) {
            cartIcon.setImageResource(R.drawable.cart_grey);
            superIcon.setImageResource(R.drawable.super_market);
            shekelIcon.setImageResource(R.drawable.shekel_grey);
            mapsIcon.setImageResource(R.drawable.map_grey);
        }
        else if (destFragment instanceof ShoppingCartFragment){
            cartIcon.setImageResource(R.drawable.cart_grey);
            superIcon.setImageResource(R.drawable.super_grey);
            shekelIcon.setImageResource(R.drawable.shekel);
            mapsIcon.setImageResource(R.drawable.map_grey);
    }
        else if(destFragment instanceof SuperMapFragment) {
            cartIcon.setImageResource(R.drawable.cart_grey);
            superIcon.setImageResource(R.drawable.super_grey);
            shekelIcon.setImageResource(R.drawable.shekel_grey);
            mapsIcon.setImageResource(R.drawable.map);
            isMapsFragment=true;
            updateMenuTitles();
        }
        // First get FragmentManager object.
        FragmentManager fragmentManager = this.getSupportFragmentManager();

        // Begin Fragment transaction.
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Replace the layout holder with the required Fragment object.
        fragmentTransaction.replace(R.id.fragmentContainer, destFragment);
        //fragmentTransaction.addToBackStack();
        // Commit the Fragment replace action.
        fragmentTransaction.commit();

    }
    /*public void replaceFragmentsOnActivity(Fragment fragment) {

        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, fragment);

        fragmentTransaction.commit();
    }*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_maps, menu);
        this.menu=menu;
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_map:
            Toast.makeText(this,R.string.saved_map,Toast.LENGTH_SHORT).show();
            return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
    private void updateMenuTitles() {
        MenuItem bedMenuItem = menu.findItem(R.id.save_map);
        if(isMapsFragment) {
            bedMenuItem.setIcon(R.drawable.ic_file_download);
        }
    }
}
