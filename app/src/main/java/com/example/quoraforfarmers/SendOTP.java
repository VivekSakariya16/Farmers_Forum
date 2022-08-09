package com.example.quoraforfarmers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class SendOTP extends AppCompatActivity {
    Button send;
    EditText mobile;
    ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private String mobileNo;
    static boolean[] x= {false};

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedpreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
        if (!sharedpreferences.getBoolean("yes", false)) {
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putBoolean("yes", x[0]);
            editor.apply();
        } else {
            Intent intent = new Intent(SendOTP.this,MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_otp);

        send=findViewById(R.id.getOTPButton);
        mobile=findViewById(R.id.getMobileNo);
        progressBar=findViewById(R.id.progressBar);
        mAuth = FirebaseAuth.getInstance();

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mobileNo = "+91"+mobile.getText().toString();
                if(mobile.getText().toString().length()==0){
                    Toast.makeText(SendOTP.this, "Please Enter Mobile Number", Toast.LENGTH_SHORT).show();
                }else {
                    progressBar.setVisibility(View.VISIBLE);
                    send.setVisibility(View.INVISIBLE);
                    sendOTP(mobileNo);
                    x[0]=true;
                }
            }
        });

    }

    private void sendOTP(String mobile) {
        mAuth.useAppLanguage();
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(mobile)
                        .setTimeout(5L, TimeUnit.SECONDS)
                        .setActivity(SendOTP.this)
                        .setCallbacks(mCallbacks)
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            progressBar.setVisibility(View.GONE);
            send.setVisibility(View.VISIBLE);
            Intent intent = new Intent(SendOTP.this, VerifyOTP.class);
            intent.putExtra("mobileNo", mobileNo);
            intent.putExtra("ID", s);
            Toast.makeText(SendOTP.this, "OTP sent", Toast.LENGTH_SHORT).show();
            startActivity(intent);

        }

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            progressBar.setVisibility(View.GONE);
            send.setVisibility(View.VISIBLE);
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            progressBar.setVisibility(View.GONE);
            send.setVisibility(View.VISIBLE);
            Toast.makeText(SendOTP.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    };
}