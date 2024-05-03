package com.example.artisticavenues;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {
    ImageView logoImageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        logoImageView = findViewById(R.id.imageView);
        logoImageView.setScaleX(0.5f);
        logoImageView.setScaleY(0.5f);

        // Calculate the initial and final scales
        float initialScaleX = 0.5f;
        float initialScaleY = 0.5f;
        float finalScaleX = 1.0f;
        float finalScaleY = 1.0f;

        // Create a scale animation
        logoImageView.animate()
                .scaleX(finalScaleX)
                .scaleY(finalScaleY)
                .setDuration(3000)
                .setInterpolator(new DecelerateInterpolator())
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        // Animation is complete, navigate to the main activity
                        Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                        startActivity(intent);

                        // Finish the current activity to prevent returning to it with the back button
                        finish();
                    }
                })
                .start();
    }
}
