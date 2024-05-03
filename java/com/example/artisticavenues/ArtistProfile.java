package com.example.artisticavenues;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ArtistProfile extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_profile);
        CardView cardView1;

        cardView1 = findViewById(R.id.newPhoto);
        Button button = findViewById(R.id.button);
        Button button1 = findViewById(R.id.button1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i1=new Intent(ArtistProfile.this ,ArtistEditProfile.class);
                startActivity(i1);
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SessionManager sm=new SessionManager(getApplicationContext());
                sm.clearSession();
                Toast.makeText(ArtistProfile.this, "Logout successfully", Toast.LENGTH_SHORT).show();
                Intent i1=new Intent(ArtistProfile.this ,ArtistLogin.class);
                startActivity(i1);
                finish();
            }
        });

        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i1=new Intent(ArtistProfile.this ,AddNewPhoto.class);
                startActivity(i1);
            }
        });
    }
}