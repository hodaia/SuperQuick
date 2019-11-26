package com.example.androidb.superquick.fragments;

import android.os.Bundle;
//import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.androidb.superquick.General.UserSessionData;
import com.example.androidb.superquick.R;
import com.example.androidb.superquick.activities.ShoppingListContentActivity;
import com.example.androidb.superquick.activities.ShoppingListProcessActivity;
import com.example.androidb.superquick.adapters.SuperListAdapter;
import com.example.androidb.superquick.entities.ProductInShoppingList;
import com.example.androidb.superquick.entities.ProductInSuper;
import com.example.androidb.superquick.entities.Super;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**

 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link SuperListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SuperListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public SuperListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SuperListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SuperListFragment newInstance(String param1, String param2) {
        SuperListFragment fragment = new SuperListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    ListView superListView;
    SuperListAdapter superListAdapter;
    List<Super> parsedSupers;
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
        View fragmentView= inflater.inflate(R.layout.fragment_super_list, container, false);

       /* Button goToCartBtn = (Button)fragmentView.findViewById(R.id.goToCartBtn);
        goToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShoppingCartFragment shoppingCartFragment=new ShoppingCartFragment();
                ((ShoppingListProcessActivity)getActivity()).replaceFragment(shoppingCartFragment);
            }
        });*/
          Button goToMapsBtn = (Button)fragmentView.findViewById(R.id.goToMapsBtn);
          goToMapsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SuperMapFragment SuperMapFragment=new SuperMapFragment();
                ((ShoppingListProcessActivity)getActivity()).replaceFragment(SuperMapFragment);
            }
        });

        //call the function
        parsedSupers=Super.getSupers();

        int shoppingListId;
        if (UserSessionData.getInstance().userCurrentShoppingListId == 0)
            shoppingListId=UserSessionData.getInstance().userShoppingList.getShoppingListId();
        else
        shoppingListId=UserSessionData.getInstance().userCurrentShoppingListId;

        //call the adapter for superListView
        superListView = fragmentView.findViewById(R.id.supersListView);
        superListAdapter = new SuperListAdapter(parsedSupers,getActivity(),shoppingListId);
        superListView.setAdapter(superListAdapter);
        return  fragmentView;
    }
}
