package com.example.quoraforfarmers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class addYourQuestion extends AppCompatActivity {
    EditText q;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_your_question);
        q=findViewById(R.id.que);
        findViewById(R.id.sendQ).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String que = q.getText().toString();
                if (que.isEmpty()) {
                    q.setError("Enter Your Question");
                } else {
                Intent intent = new Intent(addYourQuestion.this, MainActivity.class);
                intent.putExtra("QUE",que);
                startActivity(intent);
            }
            }
        });
    }
}