package com.example.androidb.superquick.dialogs;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.androidb.superquick.R;
import com.example.androidb.superquick.adapters.CartListAdapter;
import com.example.androidb.superquick.adapters.ShoppingListMenuAdapter;
import com.example.androidb.superquick.entities.Product;
import com.example.androidb.superquick.entities.ProductInShoppingList;
import com.example.androidb.superquick.entities.ProductInSuper;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link CartDialogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartDialogFragment extends DialogFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public CartDialogFragment() {

        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CartDialogFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CartDialogFragment newInstance(String param1, String param2) {
        CartDialogFragment fragment = new CartDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    Context context;
    List<Product> shoppingListContent;
    List<ProductInShoppingList> productsAmount;
    List<ProductInSuper> productPrice;
    CartListAdapter cartListAdapter;
    ListView cartListView;
    Button cancelBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView= inflater.inflate(R.layout.fragment_cart_dialog, container, false);

        // select the lists for the adapter
        shoppingListContent=ProductInShoppingList.getProductsOfShoppingList();
        productsAmount=ProductInShoppingList.getProductsInShoppingList();
        productPrice=ProductInSuper.cartProductPriceInSuper();


        // call the adapter for the cartProductView
        cartListView = fragmentView.findViewById(R.id.cartListView);
        cartListAdapter = new CartListAdapter(getActivity(),shoppingListContent,productsAmount,productPrice);
        cartListView.setAdapter(cartListAdapter);

        Button closeDialogBtn = (Button)fragmentView.findViewById(R.id.closeDialogBtn);
        closeDialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        return fragmentView;
    }
}
