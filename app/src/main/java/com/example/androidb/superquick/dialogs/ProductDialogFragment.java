package com.example.androidb.superquick.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
//import android.support.v4.app.DialogFragment;
//import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.example.androidb.superquick.R;
import com.example.androidb.superquick.activities.ShoppingListProcessActivity;
import com.example.androidb.superquick.adapters.ExpandableListAdapter;
import com.example.androidb.superquick.adapters.ShoppingListContentAdapter;
import com.example.androidb.superquick.entities.Category;
import com.example.androidb.superquick.entities.Product;
import com.example.androidb.superquick.entities.SubCategory;
import com.example.androidb.superquick.fragments.SuperListFragment;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**

 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link ProductDialogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductDialogFragment extends DialogFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    int categoryId;
    List<Product> addList;
    List<Product> updateList;


    public ProductDialogFragment() {
        // Required empty public constructor
    }
    public ProductDialogFragment(int categoryId) {
         this.categoryId=categoryId;
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     *
     * @return A new instance of fragment ProductDialogFragment.
     */
    public static ProductDialogFragment newInstance() {
        return null;
    }

    // TODO: Rename and change types and number of parameters
    public static ProductDialogFragment newInstance(String param1, String param2) {
        ProductDialogFragment fragment = new ProductDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    ExpandableListView categoriesProductsView;
    ShoppingListContentAdapter shoppingListContentAdapter;
    List<SubCategory> ParsedSubCategories;
    HashMap<SubCategory,List<Product>> ParsedProducts;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        //1
        final AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        //2

        //buttons
        builder.setNeutralButton("save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        //3
        AlertDialog dialog=builder.create();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView= inflater.inflate(R.layout.fragment_product_dialog, container, false);

        //שליפת רשימת התת-קטדגוריות עפ"י הקטגוריה שנלחצה

        Button closeDialogBtn = (Button)fragmentView.findViewById(R.id.closeDialogBtn);
        closeDialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        //call the first function to get all the subCategories of the current Category
        ParsedSubCategories=SubCategory.getSubCategory(categoryId);

        //call the second function to get all the products of the current subCategories
        ParsedProducts=Product.getProductsBySubCategory(ParsedSubCategories);


        //expandableList קריאה לאדפטר של
        categoriesProductsView = fragmentView.findViewById(R.id.categoriesProductsExpandableListView);
        ExpandableListAdapter expandableListAdapter= new ExpandableListAdapter(getActivity(),ParsedSubCategories,ParsedProducts);
        categoriesProductsView.setAdapter(expandableListAdapter);


        return fragmentView;   }

    // TODO: Rename method, update argument and hook method into UI event

}
