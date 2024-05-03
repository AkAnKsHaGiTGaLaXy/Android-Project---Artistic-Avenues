package com.example.artisticavenues;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageView;

public class UploadPhoto extends AppCompatActivity {
    private final int CAMERA_REQ_CODE = 100;
    ImageView uploadImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent iCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(iCamera, CAMERA_REQ_CODE);

        setContentView(R.layout.activity_upload_photo);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK){

            if(requestCode == CAMERA_REQ_CODE){
                //for camera
                uploadImage=findViewById(R.id.uploadImage);
                Bitmap img = (Bitmap)(data.getExtras().get("data"));
                uploadImage.setImageBitmap(img);

            }
        }
    }
}