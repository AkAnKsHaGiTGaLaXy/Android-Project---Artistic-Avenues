package com.example.artisticavenues;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CustomerProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_profile); // Set the layout for this activity

        CardView cardView1, cardView2;
        cardView1 = findViewById(R.id.artist);
        cardView2 = findViewById(R.id.favorites);
        Button button = findViewById(R.id.button);
        Button button1 = findViewById(R.id.button1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(CustomerProfile.this, CustomerEditProfile.class);
                startActivity(i1);
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SessionManager sm=new SessionManager(getApplicationContext());
                sm.clearSession();
                Toast.makeText(CustomerProfile.this, "Logout successfully", Toast.LENGTH_SHORT).show();
                Intent i1 = new Intent(CustomerProfile.this, CustomerLogin.class);
                startActivity(i1);
                finish();
            }
        });

        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(CustomerProfile.this, CustomerViewArtist.class);
                startActivity(i1);
            }
        });

        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(CustomerProfile.this, CustomerFavorites.class);
                startActivity(i1);
            }
        });
    }
}
