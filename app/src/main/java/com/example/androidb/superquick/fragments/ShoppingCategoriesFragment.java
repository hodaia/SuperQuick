package com.example.androidb.superquick.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
//import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.example.androidb.superquick.General.UserSessionData;
import com.example.androidb.superquick.R;
import com.example.androidb.superquick.activities.ShoppingListContentActivity;
import com.example.androidb.superquick.activities.ShoppingListProcessActivity;
import com.example.androidb.superquick.activities.ShoppingListsMenuActivity;
import com.example.androidb.superquick.activities.StartMenuActivity;
import com.example.androidb.superquick.adapters.CategoryListAdapter;
import com.example.androidb.superquick.adapters.ShoppingListMenuAdapter;
import com.example.androidb.superquick.entities.Category;
import com.example.androidb.superquick.entities.Product;
import com.example.androidb.superquick.entities.ProductInShoppingList;
import com.example.androidb.superquick.entities.ShoppingList;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link ShoppingCategoriesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShoppingCategoriesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ShoppingCategoriesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShoppingCategoriesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShoppingCategoriesFragment newInstance(String param1, String param2) {
        ShoppingCategoriesFragment fragment = new ShoppingCategoriesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    List<Category> ParsedCategories;
    GridView shoppingCategoriesGridView;
    CategoryListAdapter shoppingCategoriesAdapter;
    View fragmentView;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    List<Product> productList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_shopping_categories, container, false);
        int shoppingListId = (int) getActivity().getIntent().getIntExtra("shoppingListId", -1);
        if (UserSessionData.getInstance().userShoppingList == null)
            UserSessionData.getInstance().userCurrentShoppingListId = shoppingListId;


        Button saveShoppingListBtn = (Button) fragmentView.findViewById(R.id.saveShoppingListBtn);
        saveShoppingListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (UserSessionData.getInstance().userShoppingListContent.size() > 0) {

                    UserSessionData.getInstance().userShoppingList.saveInBackground();
                    for (ProductInShoppingList p : UserSessionData.getInstance().userShoppingListContent) {
                        p.saveInBackground();
                    }
                    Intent intent = new Intent();
                    if (UserSessionData.getInstance().userCurrentShoppingListId == 0)
                        intent.putExtra("shoppingListId", UserSessionData.getInstance().userShoppingList.getShoppingListId());
                    else
                        intent.putExtra("shoppingListId", UserSessionData.getInstance().userCurrentShoppingListId);
                    intent.setClass(getActivity(),ShoppingListContentActivity.class);
                    startActivity(intent);
                } else {
                    createAlertDialog();
                }
            }
        });



        Button goToSupersListBtn = (Button) fragmentView.findViewById(R.id.goToSupersListBtn);
        goToSupersListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (UserSessionData.getInstance().userShoppingListContent.size() > 0) {

                    UserSessionData.getInstance().userShoppingList.saveInBackground();
                    for (ProductInShoppingList p : UserSessionData.getInstance().userShoppingListContent) {
                        p.saveInBackground();
                    }
                    SuperListFragment superListFragment = new SuperListFragment();
                    ((ShoppingListProcessActivity) getActivity()).replaceFragment(superListFragment);
                }
                else {
                    createAlertDialog();
                }
            }
        });


        //else{
        //UserSessionData.emptyNewShoppingList();
        //UserSessionData.getInstance().userShoppingList=null;}
                /*hoppingCartFragment shoppingCartFragment = new ShoppingCartFragment();
                ((ShoppingListProcessActivity) getActivity()).replaceFragment(shoppingCartFragment);*/
        /*Button goToCartBtn = (Button) fragmentView.findViewById(R.id.goToCartBtn);
        goToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (UserSessionData.getInstance().userShoppingListContent.size() != 0) {
                    UserSessionData.getInstance().userShoppingList.saveInBackground();
                    for (ProductInShoppingList p : UserSessionData.getInstance().userShoppingListContent) {
                        p.saveInBackground();
                    }
                }
                //else{
                //UserSessionData.emptyNewShoppingList();
                //UserSessionData.getInstance().userShoppingList=null;}
                ShoppingCartFragment shoppingCartFragment = new ShoppingCartFragment();
                ((ShoppingListProcessActivity) getActivity()).replaceFragment(shoppingCartFragment);
            }
        });*/

        //call  the function
        ParsedCategories = Category.getCategories();

        //call the adapter for the shoppingCategoriesGridView
        shoppingCategoriesGridView = fragmentView.findViewById(R.id.shoppingCategoriesGridView);
        shoppingCategoriesAdapter = new

                CategoryListAdapter(getActivity(), ParsedCategories);
        shoppingCategoriesGridView.setAdapter(shoppingCategoriesAdapter);
        return fragmentView;
    }

    private void createAlertDialog() {
        // Create the object of
        // AlertDialog Builder class
        AlertDialog.Builder builder
                = new AlertDialog
                .Builder(getActivity());

        // Set the message show for the Alert time
        builder.setMessage(R.string.emptyListAlertDialogMsg);

        // Set Alert Title
        builder.setTitle(R.string.emptyListAlertDialogTitle);

        // Set Cancelable false
        // for when the user clicks on the outside
        // the Dialog Box then it will remain show
        builder.setCancelable(false);

        // Set the positive button with yes name
        // OnClickListener method is use of
        // DialogInterface interface.

        builder
                .setPositiveButton(
                        "ok",
                        new DialogInterface
                                .OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {

                                // When the user click yes button
                                // then app will close
                                dialog.cancel();
                            }
                        });


        // Create the Alert dialog
        AlertDialog alertDialog = builder.create();

        // Show the Alert Dialog box
        alertDialog.show();


    }

}
