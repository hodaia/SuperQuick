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
import android.widget.Spinner;

import com.example.androidb.superquick.R;
import com.example.androidb.superquick.activities.ShoppingListProcessActivity;
import com.example.androidb.superquick.adapters.CityListAdapter;
import com.example.androidb.superquick.entities.City;
import com.example.androidb.superquick.fragments.SuperListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link CitiesFragmentDialog#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CitiesFragmentDialog extends DialogFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public CitiesFragmentDialog() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CitiesFragmentDialog.
     */
    // TODO: Rename and change types and number of parameters
    public static CitiesFragmentDialog newInstance(String param1, String param2) {
        CitiesFragmentDialog fragment = new CitiesFragmentDialog();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    ListView cityListView;
    List<City> parsedCities=new ArrayList<>();
    Button goToSupersListBtn;
    Button cancelBtn;
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
        // Inflate the layout for this fragment
        View fragmentView= inflater.inflate(R.layout.fragment_cities_fragment_dialog, container, false);
        cityListView=(ListView) fragmentView.findViewById(R.id.cityListView);
        parsedCities= City.getCities();

        CityListAdapter cityListAdapter=new CityListAdapter(parsedCities,getActivity());
        cityListView.setAdapter(cityListAdapter);

        goToSupersListBtn=fragmentView.findViewById(R.id.goToSupersListBtn);
        goToSupersListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SuperListFragment superListFragment = new SuperListFragment();
                ((ShoppingListProcessActivity) getActivity()).replaceFragment(superListFragment);
                dismiss();
            }
        });

        cancelBtn=fragmentView.findViewById(R.id.cancelBtn);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return fragmentView;

    }

}
