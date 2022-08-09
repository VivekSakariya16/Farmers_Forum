package com.example.quoraforfarmers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    RecyclerView rv;
    Button addQ;
    ImageView image;
    TextView name;
    String [] que ={"What is Square of 2?","What is Square of 2?","What is Square of 2?","What is Square of 2?","What is Square of 2?","What is Square of 2?","What is Square of 2?","What is Square of 2?","What is Square of 2?","What is Square of 2?","What is Square of 2?","What is Square of 2?"};
    int [] ans1 ={15,20,48,60,10,50,90,120,35,56,42,36};
    double [] ans2 ={0.1,0.3,0.5,0.7,0.1,0.6,1,1.3,0.7,0.9,35,25};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv=findViewById(R.id.recycleView);
        addQ=findViewById(R.id.addQuestion);
        image=findViewById(R.id.imageView2);
        name=findViewById(R.id.nameM);

        String q = getIntent().getStringExtra("QUE");
        int a = getIntent().getIntExtra("ANS",50);
        ans1[0]=a;

        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.addItemDecoration(new
                DividerItemDecoration(MainActivity.this,
                DividerItemDecoration.VERTICAL));
        CustomAdapter c = new CustomAdapter(que,ans1,ans2);
        rv.setAdapter(c);

        que[0]=q;
        addQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,addYourQuestion.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Can You Exit the Application?");
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}