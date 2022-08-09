package com.example.quoraforfarmers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class setProfile extends AppCompatActivity {
    EditText name;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_profile);
        name=findViewById(R.id.name);
        submit=findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameX=name.getText().toString();
                if(nameX.isEmpty()){
                    name.setError("Enter Your Name");
                }else {
                    Intent intent = new Intent(setProfile.this, MainActivity.class);
                    intent.putExtra("ABC",nameX);
                    startActivity(intent);
                }
            }
        });
    }
}