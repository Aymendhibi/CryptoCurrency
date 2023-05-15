package com.example.cryptocurrency.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cryptocurrency.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class ProfileFragment extends Fragment {

    DatabaseReference databaseReference;
    EditText Key1value, Key2value, Key3value;
    String user_username,user_email,user_name;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        databaseReference = FirebaseDatabase.getInstance().getReference("users");

        Key1value = view.findViewById(R.id.getKey1value);
        Key2value = view.findViewById(R.id.getKey2value);
        Key3value = view.findViewById(R.id.getKey3value);

        showAllUserData();

        view.findViewById(R.id.update_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNameChanged() || isPasswordChanged()) {
                    Toast.makeText(getActivity(), "Data has been updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Data is not updated", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    private void showAllUserData() {
        Intent intent = requireActivity().getIntent();
         user_username = intent.getStringExtra("username");
         user_email = intent.getStringExtra("email");
       user_name = intent.getStringExtra("name");

        Key1value.setText(user_username);
        Key2value.setText(user_name);
        Key3value.setText(user_email);
    }

    private boolean isNameChanged() {
        Intent intent = requireActivity().getIntent();
        String user_email = intent.getStringExtra("email");
        String new_name = Key2value.getText().toString();

        if (!new_name.equals(user_name)) {
            databaseReference.child(user_email).child("name").setValue(new_name);
            return true;
        } else {
            return false;
        }
    }

    private boolean isPasswordChanged() {
        Intent intent = requireActivity().getIntent();
        String user_email = intent.getStringExtra("email");
        String new_username = Key1value.getText().toString();

        if (!new_username.equals(user_username)) {
            databaseReference.child(user_email).child("username").setValue(new_username);
            return true;
        } else {
            return false;
        }
    }
}