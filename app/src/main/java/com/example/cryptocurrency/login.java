package com.example.cryptocurrency;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cryptocurrency.fragment.ProfileFragment;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {

    EditText email, password;
    Button loginBtn, singnup_screen;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://crypto-e1eaa-default-rtdb.firebaseio.com/");

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final ImageView signInBtn=findViewById(R.id.signInBtn);
        GoogleSignInOptions googleSignInOptions= new  GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(this,googleSignInOptions);
        GoogleSignInAccount googleSignInAccount = GoogleSignIn.getLastSignedInAccount(this);
        //checking if user already sinrd in
        if (googleSignInAccount !=null){
            //open main activity
            startActivity(new Intent(login.this,MainActivity.class));
            finish();
        }
        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                // getting signed in account after user selected an account from google account dialog
                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(result.getData());

                handleSignInTask(task);
            }
        });

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent signInIntent = googleSignInClient.getSignInIntent();
                activityResultLauncher.launch(signInIntent);
            }
        });

        email = findViewById(R.id.phone);
        password = findViewById(R.id.idpassword);
        loginBtn = findViewById(R.id.loginBtn);
        singnup_screen = findViewById(R.id.singnup_screen);
        TextView registerNowBtn = findViewById(R.id.registerNowBtn);


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser(view);

            }
        });
        registerNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this, register.class);
                startActivity(intent);
            }
        });
        singnup_screen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this, confirmerpassword.class);
                startActivity(intent);
            }
        });
    }

    private Boolean validateUsername() {

        String val = email.getText().toString();

        if (val.isEmpty()) {

            password.setError("Field cannot be empty");
            return false;

        } else {

            password.setError(null);


            return true;
        }


    }

    private Boolean validatePassword() {

        String val = password.getText().toString();

        if (val.isEmpty()) {

            password.setError("Field cannot be empty");
            return false;

        } else {

            password.setError(null);


            return true;
        }


    }



    public  void loginUser(View view){
        
        if (!validateUsername() | !validatePassword()){
            return;
        }
        else{
            isUser();
        }
    }

    private void isUser() {


        String userEnteredUsername= email.getText().toString().trim();
        String userEnteredPassword= password.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference(  "users");

        Query checkUser = reference.orderByChild("email").equalTo (userEnteredUsername);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    email.setError(null);
//                    email.setError(null);

                    String passwordFromDB = dataSnapshot.child(userEnteredUsername).child("password").getValue(String.class);

                    if (passwordFromDB.equals(userEnteredPassword)) {
                        email.setError(null);
//                    email.setError(null);

                        String nameFromDB = dataSnapshot.child(userEnteredUsername).child("name").getValue(String.class);

                        String usernameFrompe = dataSnapshot.child(userEnteredUsername).child("username").getValue(String.class);
                        String emailFromDB = dataSnapshot.child(userEnteredUsername).child("email").getValue(String.class);



                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                        intent.putExtra("name", nameFromDB);
                        intent.putExtra("username", usernameFrompe);
                        intent.putExtra("email", emailFromDB);
                        startActivity(intent);
                    }
                    else {
                        password.setError("wrong Password");
                        password.requestFocus();

                    }


                }
            else{
                email.setError("No such User exist");
                    email.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private  void  handleSignInTask(Task<GoogleSignInAccount> task){
        try {
            GoogleSignInAccount account =task.getResult(ApiException.class);
//getting account data
            final String getFullname = account.getDisplayName();
            final String getEmail = account.getEmail();
            final Uri getPhotoUrl = account.getPhotoUrl();

            startActivity(new Intent(login.this,MainActivity.class));
            finish();


        } catch (ApiException e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed or Cancelled", Toast.LENGTH_SHORT).show();
        }
    }


}