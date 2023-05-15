package com.example.cryptocurrency;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Forget extends AppCompatActivity {



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        Button buttonGetOTP = findViewById(R.id.buttonGetOTP);
        ProgressBar  progressBar = findViewById(R.id.progressBar);
        EditText inputMobile = findViewById(R.id.inputMobile);

        buttonGetOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 if(inputMobile.getText().toString().trim().isEmpty()){
                     Toast.makeText(Forget.this, "Enter Mobile", Toast.LENGTH_SHORT).show();

                 }

                progressBar.setVisibility(View.VISIBLE);
                buttonGetOTP.setVisibility(View.VISIBLE);
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+216"+
                    inputMobile.getText().toString(),

                60,
                            TimeUnit.SECONDS,Forget.this,
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {


                    @Override

                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        progressBar.setVisibility(View.GONE);
                        buttonGetOTP.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                      progressBar.setVisibility(View.GONE);
                      buttonGetOTP.setVisibility(View.VISIBLE);
                        Toast.makeText(Forget.this,e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                    @Override
                    public   void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken){
                        progressBar.setVisibility(View.GONE);
                        buttonGetOTP.setVisibility(View.VISIBLE);
                        Intent intent =new Intent(getApplicationContext(),verif.class);
                        intent.putExtra("mobile", inputMobile.getText().toString());
                       intent.putExtra("verificationId",verificationId);

                        startActivity(intent);
                    }}
                );

            }
        });



}

}