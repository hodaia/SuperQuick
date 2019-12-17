package com.example.androidb.superquick.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.androidb.superquick.General.UserSessionData;
import com.example.androidb.superquick.R;
import com.example.androidb.superquick.activities.MapsListMenuActivity;
import com.example.androidb.superquick.activities.ShoppingListProcessActivity;
import com.example.androidb.superquick.activities.ShoppingListsMenuActivity;
import com.example.androidb.superquick.activities.StartMenuActivity;
import com.example.androidb.superquick.adapters.ShoppingListContentAdapter;
import com.example.androidb.superquick.entities.Product;
import com.example.androidb.superquick.entities.ProductInShoppingList;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the

 * to handle interaction events.
 * Use the {@link ShoppingCartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShoppingCartFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public ShoppingCartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShoppingCartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShoppingCartFragment newInstance(String param1, String param2) {
        ShoppingCartFragment fragment = new ShoppingCartFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    List<Product> cartList;
    List<ProductInShoppingList> productsAmount;
    ListView cartListView;
    ShoppingListContentAdapter shoppingListContentAdapter;
    View fragmentView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragmentView= inflater.inflate(R.layout.fragment_shopping_cart, container, false);


        Button goToCartBtn = (Button)fragmentView.findViewById(R.id.saveShoppingListBtn);
        goToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), ShoppingListsMenuActivity.class);
                startActivity(intent);
            }
        });

        Button goToSupersListBtn = (Button)fragmentView.findViewById(R.id.goToSupersListBtn);
        goToSupersListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SuperListFragment superListFragment=new SuperListFragment();
                ((ShoppingListProcessActivity)getActivity()).replaceFragment(superListFragment);
            }
        });

        cartList= Product.getCurrentCartList();
        productsAmount=ProductInShoppingList.getProductsInShoppingList();
        //call the adapter for the cartListView
        cartListView = fragmentView.findViewById(R.id.shoppingListMenuView);
        shoppingListContentAdapter = new ShoppingListContentAdapter(cartList,productsAmount,getActivity());
        cartListView.setAdapter(shoppingListContentAdapter);
        return  fragmentView;
    }
}
