package com.example.artisticavenues;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AdminProfile extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profile);

        Button button = findViewById(R.id.button);
        Button button1 = findViewById(R.id.button1);

        CardView cardView1,cardView2,cardView3;
        cardView1 = findViewById(R.id.gallery);
        cardView2 = findViewById(R.id.artistRecords);
        cardView3 = findViewById(R.id.customerRecords);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i1=new Intent(AdminProfile.this ,AdminEditProfile.class);
                startActivity(i1);
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SessionManager sm=new SessionManager(getApplicationContext());
                sm.clearSession();
                Toast.makeText(AdminProfile.this, "Logout successfully", Toast.LENGTH_SHORT).show();
                Intent i1=new Intent(AdminProfile.this ,AdminLogin.class);
                startActivity(i1);
                finish();
            }
        });

        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i1=new Intent(AdminProfile.this ,AdminVerifyArtist.class);
                startActivity(i1);
            }
        });

        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i1=new Intent(AdminProfile.this ,AdminViewArtistRecords.class);
                startActivity(i1);
            }
        });

        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i1=new Intent(AdminProfile.this ,AdminViewCustomerRecords.class);
                startActivity(i1);
            }
        });
    }
}