package com.example.artisticavenues;

//import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ArtistSignUp extends AppCompatActivity {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    TextView LogIn;
    Button button;
    TextInputEditText name, email, contactno, password, confirmpass, address;
    private static final int PICK_IMAGE = 100;
    private Button selectImageBtn;
    private ImageView imageView1;
    private Uri imageUri;

   // @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_sign_up);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        contactno = findViewById(R.id.contactno);
        address = findViewById(R.id.address);
        password = findViewById(R.id.password);
        confirmpass = findViewById(R.id.confirmpass);
        button = findViewById(R.id.button);
        LogIn = findViewById(R.id.LogIn);
        selectImageBtn = findViewById(R.id.selectImageBtn);
        imageView1 = findViewById(R.id.imageView1);

        selectImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });

        LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(ArtistSignUp.this, ArtistLogin.class);
                startActivity(loginIntent);
            }
        });


        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Artist"); // "Admin" is the top-level node
        //Toast.makeText(this, "All Ok", Toast.LENGTH_SHORT).show();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertUserData();
            }
        });
    }

    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            imageUri = data.getData();
            imageView1.setImageURI(imageUri);
        }
    }

    private void insertUserData() {
        String Name = name.getText().toString().trim();
        String Email = email.getText().toString().trim();
        String Number = contactno.getText().toString().trim();
        String Password = password.getText().toString().trim();
        String Address = address.getText().toString().trim();
        String ConfirmPassword = confirmpass.getText().toString().trim();

        if (TextUtils.isEmpty(Name) || TextUtils.isEmpty(Email) || TextUtils.isEmpty(Number) || TextUtils.isEmpty(Password) || TextUtils.isEmpty(ConfirmPassword) || TextUtils.isEmpty(Address)) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!Password.equals(ConfirmPassword)) {
            Toast.makeText(this, "The confirmed password must match the password.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Generate a unique user ID
        String userId = databaseReference.push().getKey();

        if (userId == null) {
            Toast.makeText(this, "Failed to create a user", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a User object
        Customer user = new Customer(userId, Name, Email, Number, Password, Address);

        // Insert data into the "users" node under the unique user ID
        databaseReference.child(userId).setValue(user)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        SessionManager sm = new SessionManager(getApplicationContext());
                        sm.createSession(userId, Email);
                        Toast.makeText(this, "User data added successfully", Toast.LENGTH_SHORT).show();
                        Intent LoginSucces = new Intent(ArtistSignUp.this, ArtistLogin.class);
                        startActivity(LoginSucces);
                        finish();

                    } else {
                        Toast.makeText(this, "Failed to add user data", Toast.LENGTH_SHORT).show();
                    }
                });



    }}

