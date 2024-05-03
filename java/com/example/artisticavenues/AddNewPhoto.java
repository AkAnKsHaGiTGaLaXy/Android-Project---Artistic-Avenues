package com.example.artisticavenues;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class AddNewPhoto extends AppCompatActivity {

    private  final int GALLERY_REQ_CODE = 1000;
    ImageView imgGallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_photo);

        CardView cardView1, cardView2;

        cardView1 = findViewById(R.id.openGallery);
        cardView2 = findViewById(R.id.openCamera);

        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i1=new Intent(AddNewPhoto.this ,UploadPhotoGallery.class);
                startActivity(i1);
            }
        });

        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i1=new Intent(AddNewPhoto.this ,UploadPhoto.class);
                startActivity(i1);
            }
        });


    }



}