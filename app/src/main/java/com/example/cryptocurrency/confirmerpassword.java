package com.example.cryptocurrency;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class confirmerpassword extends AppCompatActivity {

    EditText eemail , currentPassword ,newPassword;
    Button logi;
    String user_username,user_email,user_name;
    DatabaseReference reference;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmerpassword);
        reference = FirebaseDatabase.getInstance().getReference("users");
        newPassword = findViewById(R.id.conpassword);
        Button bn = findViewById(R.id.logi);
        Intent intent = getIntent();
        user_username =intent.getStringExtra("password");

        newPassword.setText(user_username);
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update(view);
            }
        });
    }

    public void update(View view){
        if( isPasswordChanged()){
            Toast.makeText(this, "Data has been updated", Toast.LENGTH_SHORT).show();
        }
        else             Toast.makeText(this, "Data is not updated", Toast.LENGTH_SHORT).show();

    }

    private boolean isPasswordChanged() {
        if(!user_username.equals(newPassword.getText().toString())){
            reference.child(user_email).child("password").setValue(newPassword.getText().toString());
            return  true;

        }
        else {
            return  false;
        }
    }
}