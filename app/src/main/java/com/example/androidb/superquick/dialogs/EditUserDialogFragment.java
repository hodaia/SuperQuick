package com.example.androidb.superquick.dialogs;

import android.content.Context;
import android.content.Intent;
import android.net.ParseException;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.androidb.superquick.General.UserSessionData;
import com.example.androidb.superquick.R;
import com.example.androidb.superquick.activities.LoginActivity2;
import com.example.androidb.superquick.activities.MapsListMenuActivity;
import com.example.androidb.superquick.activities.StartMenuActivity;
import com.example.androidb.superquick.adapters.CityListAdapter;
import com.example.androidb.superquick.entities.City;
import com.example.androidb.superquick.entities.User;
import com.example.androidb.superquick.entities.Users;
import com.parse.GetCallback;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link EditUserDialogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditUserDialogFragment extends DialogFragment implements AdapterView.OnItemSelectedListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private boolean edit;


    public EditUserDialogFragment() {
        // Required empty public constructor
    }

    public EditUserDialogFragment(boolean edit) {
        this.edit = edit;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditUserDialogFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EditUserDialogFragment newInstance(String param1, String param2) {
        EditUserDialogFragment fragment = new EditUserDialogFragment();
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

        }
    }

    Button backBtn;
    Button saveUser;
    EditText editTextUserEmail;
    EditText editTextUserPassword;
    EditText editTextUserId;
    EditText editTextUserName;
    Spinner citiesSelect;
    CheckBox isPermanentCity;
    List<City> parsedCities = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_edit_user_dialog, container, false);
        final Users user = UserSessionData.getInstance().user;
        editTextUserEmail = fragmentView.findViewById(R.id.editTextUserEmail);
        editTextUserEmail.setText(user.getUserEmail());
        editTextUserPassword = fragmentView.findViewById(R.id.editTextUserPassword);
        editTextUserPassword.setText(user.getUserPassword());

        editTextUserId = fragmentView.findViewById(R.id.editTextUserId);
        editTextUserName = fragmentView.findViewById(R.id.editTextUserName);
        isPermanentCity = fragmentView.findViewById(R.id.checkBoxIsPermanentCity);

        //filling the list
        parsedCities = City.getCities();
        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        citiesSelect = (Spinner) fragmentView.findViewById(R.id.citiesSelect);
        citiesSelect.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the bank name list
        //Setting the ArrayAdapter data on the Spinner
        CityListAdapter cityListAdapter = new CityListAdapter(parsedCities, getActivity());
        citiesSelect.setAdapter(cityListAdapter);

        if (edit) {
            editTextUserId.setText(String.valueOf(user.getUserId()));
            editTextUserName.setText(user.getUserName());
            editTextUserPassword.setText(user.getUserPassword());
            editTextUserEmail.setText(user.getUserEmail());
            for (int i = 0; i < parsedCities.size(); i++) {
                if (parsedCities.get(i).getCityId() == user.getUser_cityId())
                    citiesSelect.setSelection(i);

            }
            isPermanentCity.setChecked(user.isPermanentCity());
        }

        backBtn = fragmentView.findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        saveUser = fragmentView.findViewById(R.id.saveUser);
        saveUser.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!edit) {
                    user.setUserId(parseInt(editTextUserId.getText().toString()));
                    user.setUserName(editTextUserName.getText().toString());
                } else {
                    ParseQuery<Users> query = ParseQuery.getQuery("Users");
                    query.getInBackground(user.getObjectId(), new GetCallback<Users>() {
                        @Override
                        public void done(Users entity, com.parse.ParseException e) {
                            if (e == null) {

                                entity.setUserId(parseInt(editTextUserId.getText().toString()));
                                UserSessionData.getInstance().user.setUserId(parseInt(editTextUserId.getText().toString()));

                                entity.setUserName(editTextUserName.getText().toString());
                                UserSessionData.getInstance().user.setUserName(editTextUserName.getText().toString());

                                entity.setUserEmail(editTextUserEmail.getText().toString());
                                UserSessionData.getInstance().user.setUserEmail(editTextUserEmail.getText().toString());

                               entity.setUserPassword(editTextUserPassword.getText().toString());
                                UserSessionData.getInstance().user.setUserPassword(editTextUserPassword.getText().toString());

                                entity.setUser_cityId(UserSessionData.getInstance().userCityId);
                                UserSessionData.getInstance().user.setUser_cityId(UserSessionData.getInstance().userCityId);

                               entity.setPermanentCity(isPermanentCity.isChecked());
                                UserSessionData.getInstance().user.setPermanentCity(isPermanentCity.isChecked());

                               entity.saveInBackground();
                            }
                        }
                    });
                }
                dismiss();
            }
        });
        return fragmentView;
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        UserSessionData.getInstance().userCityId = parsedCities.get(position).getCityId();
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
// TODO Auto-generated method stub
    }

}
