package com.example.artisticavenues;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageView;

public class UploadPhotoGallery extends AppCompatActivity {
    private final int GALLERY_REQ_CODE = 1000;
    ImageView uploadImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent iGallery = new Intent(Intent.ACTION_PICK);
        iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(iGallery, GALLERY_REQ_CODE);
        setContentView(R.layout.activity_upload_photo);


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){

            if(requestCode == GALLERY_REQ_CODE){
                //for gallery

                //uploadImage.setImageURI(data.getData());

                uploadImage=findViewById(R.id.uploadImage);
                //Bitmap img = (Bitmap)(data.getExtras().get("data"));
                uploadImage.setImageURI(data.getData());
                //uploadImage.setImageBitmap(img);
            }

        }
    }
}