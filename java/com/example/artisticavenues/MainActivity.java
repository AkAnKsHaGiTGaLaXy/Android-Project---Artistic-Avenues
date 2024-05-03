package com.example.artisticavenues;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CardView cardView1,cardView2,cardView3;
        cardView1=findViewById(R.id.admin);
        cardView2=findViewById(R.id.customer);
        cardView3=findViewById(R.id.owner);
        // Retrieve session data when needed


        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i1=new Intent(MainActivity.this ,AdminLogin.class);
                startActivity(i1);
            }
        });

        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i1=new Intent(MainActivity.this ,CustomerLogin.class);
                startActivity(i1);
            }
        });
        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i1=new Intent(MainActivity.this ,ArtistLogin.class);
                startActivity(i1);
            }
        });
    }}
