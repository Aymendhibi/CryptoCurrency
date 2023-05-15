package com.example.cryptocurrency;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Mainprofil extends AppCompatActivity {
    String user_username,user_email,user_name;

    DatabaseReference databaseReference;
    EditText Key1value,Key2value,Key3value;
    DatabaseReference reference;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainprofil);

        reference = FirebaseDatabase.getInstance().getReference("users");

        Key1value = findViewById(R.id.getKey1value);
         Key2value = findViewById(R.id.getKey2value);
        Key3value = findViewById(R.id.getKey3value);

        // Get the user ID of the currently logged-in user
showAllUserData();
    }

    private void showAllUserData() {
        Intent intent = getIntent();
         user_username =intent.getStringExtra("username");
         user_email =intent.getStringExtra("email");
         user_name =intent.getStringExtra("name");



        Key1value.setText(user_username);
        Key2value.setText(user_name);
        Key3value.setText(user_email);


    }
    public void update(View view){
        if(isNameChanged()|| isPasswordChanged()){
            Toast.makeText(this, "Data has been updated", Toast.LENGTH_SHORT).show();
        }
        else             Toast.makeText(this, "Data is not updated", Toast.LENGTH_SHORT).show();

    }

    private boolean isNameChanged() {


        if(!user_name.equals(Key2value.getText().toString())){
 reference.child(user_email).child("name").setValue(Key2value.getText().toString());
            return  true;

        }
        else {
return  false;
        }
    }
    private boolean isPasswordChanged() {
        if(!user_username.equals(Key1value.getText().toString())){
            reference.child(user_email).child("username").setValue(Key1value.getText().toString());
            return  true;

        }
        else {
            return  false;
        }
    }
}