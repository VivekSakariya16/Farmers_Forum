package com.example.quoraforfarmers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class addAnswer extends AppCompatActivity {
    Button send;
    EditText a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_answer);

        a=findViewById(R.id.ans);
        send=findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int que = Integer.parseInt(a.getText().toString());
                if(que==0){
                    a.setError("Enter Your Ans");
                }else{
                    Intent intent = new Intent(addAnswer.this,MainActivity.class);
                    intent.putExtra("ANS",que);
                    startActivity(intent);
                }
            }
        });
    }
}