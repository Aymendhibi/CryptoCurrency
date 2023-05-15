package com.example.cryptocurrency;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class verif extends AppCompatActivity {

EditText inputCode1,inputCode2,inputCode3,inputCode4, inputCode5,inputCode6,Code;
private String verificationId;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verif);
       // TextView textMobile = findViewById(R.id.textMobile);
        inputCode1 = findViewById(R.id.inputCode1);
       Code = findViewById(R.id.Code);
        inputCode2 = findViewById(R.id.inputCode2);
        inputCode3 = findViewById(R.id.inputCode3);
        inputCode4 = findViewById(R.id.inputCode4);
        inputCode5 = findViewById(R.id.inputCode5);
        inputCode6 = findViewById(R.id.inputCode6);
        setupOTP();

      final   ProgressBar progressBar = findViewById(R.id.progressBar);
       final Button buttonVerify = findViewById(R.id.buttonVerify);
        verificationId =  getIntent().getStringExtra("verificationId");
        buttonVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if (Code.getText().toString().trim().isEmpty()

 ) {
                  Toast.makeText(verif.this, "Please enter valid code", Toast.LENGTH_SHORT).show();
                  return;
              }
              String code =
                      Code.getText().toString()
                             ;
              if(verificationId != null){

                  progressBar.setVisibility(View.VISIBLE);
                  buttonVerify.setVisibility(View.INVISIBLE);
                  PhoneAuthCredential phoneAuthCredential =  PhoneAuthProvider.getCredential(
                          verificationId,
                          code
                  );
                  FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                          .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                                                     @Override
                                                     public void onComplete(@NonNull Task<AuthResult> task) {
                                                         progressBar.setVisibility(View.GONE);
                                                         buttonVerify.setVisibility(View.VISIBLE);
                                                         if (task.isSuccessful()){
                                                             Intent intent = new Intent(getApplicationContext(), confirmerpassword.class);
                                                             intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                             startActivity(intent);
                                                         }
                                                         else {
                                                             Toast.makeText(verif.this, "Invalid", Toast.LENGTH_SHORT).show();
                                                         }

                                                     }
                                                 }




                          );
              }
            }
        });
    }

          private  void setupOTP(){

        inputCode1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
 if(!s.toString().trim().isEmpty()){
     inputCode2.requestFocus();
 }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

              inputCode2.addTextChangedListener(new TextWatcher() {
                  @Override
                  public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                  }

                  @Override
                  public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                      if(!s.toString().trim().isEmpty()){
                          inputCode3.requestFocus();
                      }
                  }

                  @Override
                  public void afterTextChanged(Editable editable) {

                  }
              });
              inputCode3.addTextChangedListener(new TextWatcher() {
                  @Override
                  public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                  }

                  @Override
                  public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                      if(!s.toString().trim().isEmpty()){
                          inputCode4.requestFocus();
                      }
                  }

                  @Override
                  public void afterTextChanged(Editable editable) {

                  }
              });
              inputCode4.addTextChangedListener(new TextWatcher() {
                  @Override
                  public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                  }

                  @Override
                  public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                      if(!s.toString().trim().isEmpty()){
                          inputCode5.requestFocus();
                      }
                  }

                  @Override
                  public void afterTextChanged(Editable editable) {

                  }
              });
              inputCode5.addTextChangedListener(new TextWatcher() {
                  @Override
                  public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                  }

                  @Override
                  public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                      if(!s.toString().trim().isEmpty()){
                          inputCode6.requestFocus();
                      }
                  }

                  @Override
                  public void afterTextChanged(Editable editable) {

                  }
              });

          }

        }

