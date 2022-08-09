package com.example.quoraforfarmers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class VerifyOTP extends AppCompatActivity {
    private EditText otp1,otp2,otp3,otp4,otp5,otp6;
    Button verify;
    TextView mobile,RESEND;
    ProgressBar progressBar;
    private String verificationID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);

        otp1=findViewById(R.id.input1);
        otp2=findViewById(R.id.input2);
        otp3=findViewById(R.id.input3);
        otp4=findViewById(R.id.input4);
        otp5=findViewById(R.id.input5);
        otp6=findViewById(R.id.input6);
        mobile=findViewById(R.id.setMobile);
        RESEND=findViewById(R.id.resendOTP);
        verify=findViewById(R.id.verifyButton);
        progressBar=findViewById(R.id.progressBar2);

        String mobileNo = getIntent().getStringExtra("mobileNo");
        mobile.setText(mobileNo);
        setupOTPInputs();
        verificationID=getIntent().getStringExtra("ID");

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(otp1.getText().toString().isEmpty()||
                        otp2.getText().toString().isEmpty()||
                        otp3.getText().toString().isEmpty()||
                        otp4.getText().toString().isEmpty()||
                        otp5.getText().toString().isEmpty()||
                        otp6.getText().toString().isEmpty()){
                    Toast.makeText(VerifyOTP.this, "Please Enter Valid Code", Toast.LENGTH_SHORT).show();
                }
                else {
                    String OTP=otp1.getText().toString()+
                            otp2.getText().toString()+
                            otp3.getText().toString()+
                            otp4.getText().toString()+
                            otp5.getText().toString()+
                            otp6.getText().toString();
                    if(verificationID!=null){
                        progressBar.setVisibility(View.VISIBLE);
                        verify.setVisibility(View.INVISIBLE);
                        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
                                verificationID,OTP
                        );
                        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Intent intent=new Intent(VerifyOTP.this,setProfile.class);
                                    startActivity(intent);
                                    finish();
                                }else {
                                    Toast.makeText(VerifyOTP.this, Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }
            }
        });
        findViewById(R.id.resendOTP).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhoneAuthOptions options =
                        PhoneAuthOptions.newBuilder(FirebaseAuth.getInstance())
                                .setPhoneNumber(mobileNo)
                                .setTimeout(5L, TimeUnit.SECONDS)
                                .setActivity(VerifyOTP.this)
                                .setCallbacks(mCallbacks)
                                .build();
                PhoneAuthProvider.verifyPhoneNumber(options);
            }
        });


    }
    private void setupOTPInputs(){
        otp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().length()==1){
                    otp2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        otp2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().length()==1){
                    otp3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        otp3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().length()==1){
                    otp4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        otp4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().length()==1){
                    otp5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        otp5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().length()==1){
                    otp6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onCodeSent(@NonNull String s2, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            progressBar.setVisibility(View.GONE);
            verify.setVisibility(View.VISIBLE);
            verificationID=s2;
            Toast.makeText(VerifyOTP.this, "OTP sent", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(VerifyOTP.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    };
}