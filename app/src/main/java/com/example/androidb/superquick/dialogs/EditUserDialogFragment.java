package com.example.androidb.superquick.dialogs;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.androidb.superquick.General.UserSessionData;
import com.example.androidb.superquick.R;
import com.example.androidb.superquick.activities.LoginActivity2;
import com.example.androidb.superquick.activities.MapsListMenuActivity;
import com.example.androidb.superquick.activities.StartMenuActivity;
import com.example.androidb.superquick.entities.User;
import com.example.androidb.superquick.entities.Users;

import static java.lang.Integer.parseInt;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link EditUserDialogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditUserDialogFragment extends DialogFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public EditUserDialogFragment() {
        // Required empty public constructor
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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    Button saveUser;
    EditText editTextUserEmail;
    EditText editTextUserPassword;
    EditText editTextUserId;
    EditText editTextUserName;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView= inflater.inflate(R.layout.fragment_edit_user_dialog, container, false);
        final Users user=UserSessionData.getInstance().user;
        editTextUserEmail =fragmentView.findViewById(R.id.editTextUserEmail) ;
        editTextUserEmail.setText(user.getUserEmail());
        editTextUserPassword =fragmentView.findViewById(R.id.editTextUserPassword) ;
        editTextUserPassword.setText(user.getUserPassword());

        editTextUserId =fragmentView.findViewById(R.id.editTextUserId) ;
        editTextUserName =fragmentView.findViewById(R.id.editTextUserName) ;

        saveUser=fragmentView.findViewById(R.id.saveUser);
        saveUser.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                user.setUserId(parseInt(editTextUserId.getText().toString()));
                user.setUserName(editTextUserName.getText().toString());
                user.saveInBackground();
                Intent intent = new Intent();
                intent.setClass(getContext(), StartMenuActivity.class);
                startActivity(intent);
            }
        });
        return fragmentView;
    }

}
