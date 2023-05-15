package com.example.cryptocurrency;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class register extends AppCompatActivity {
FirebaseDatabase rootNode;
DatabaseReference reference;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://crypto-e1eaa-default-rtdb.firebaseio.com/");
    @SuppressLint("MissingInflatedId")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        EditText regName = findViewById(R.id.fullname);
        EditText regEmail = findViewById(R.id.email);
        EditText regUsername = findViewById(R.id.phone);
        EditText regPassword = findViewById(R.id.password);
        EditText regPhone = findViewById(R.id.conpassword);
        Button registerBtn = findViewById(R.id.registerBtn);
        TextView loginNowBtn = findViewById(R.id.loginNow);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("users");
                //get data
                final String name = regName.getText().toString();
                final String email = regEmail.getText().toString();
                final String username = regUsername.getText().toString();
                final String password = regPassword.getText().toString();
                final String phone = regPhone.getText().toString();

                //chech if user fill all fields befor
HelperClass helperClass= new HelperClass( name,  email, username,  password);
               reference.child(email).setValue(helperClass);

            }
        });


        loginNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}