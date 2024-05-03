package com.example.artisticavenues;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class AdminEditProfile extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    TextInputEditText NAME, EMAIL, MOBILE, PASS, ADDR;
    Button edit_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_profile);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Admin");
        NAME = findViewById(R.id.name);
        MOBILE = findViewById(R.id.contactno);
        EMAIL = findViewById(R.id.email);
        PASS = findViewById(R.id.password);
        edit_profile = findViewById(R.id.button2);
        ADDR = findViewById(R.id.address);

        // Use your SessionManager to correctly retrieve the user's email
        SessionManager sessionManager = new SessionManager(getApplicationContext());
        String emailToFind = sessionManager.getEmail();

        // Create a query to find the specific user by email
        Query query = databaseReference.orderByChild("email").equalTo(emailToFind);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Assuming there's only one user with the given email
                    DataSnapshot snapshot = dataSnapshot.getChildren().iterator().next();
                    String Name = snapshot.child("name").getValue(String.class);
                    String Email = snapshot.child("email").getValue(String.class);
                    String Mobile = snapshot.child("mobile").getValue(String.class);
                    String Pass = snapshot.child("password").getValue(String.class);
                    String Address = snapshot.child("location").getValue(String.class);


                    NAME.setText(Name);
                    EMAIL.setText(Email);
                    MOBILE.setText(Mobile);
                    PASS.setText(Pass);
                    ADDR.setText(Address);
                } else {
                    // User not found
                    Toast.makeText(AdminEditProfile.this, "User not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle any errors
                Toast.makeText(AdminEditProfile.this, "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the process of updating the user's data
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            DataSnapshot snapshot = dataSnapshot.getChildren().iterator().next();
                            String Email = EMAIL.getText().toString().trim();
                            String Name = NAME.getText().toString().trim();
                            String Mobile = MOBILE.getText().toString().trim();
                            String Pass = PASS.getText().toString().trim();
                            String Address = ADDR.getText().toString().trim();

                            // Update the user's data
                            snapshot.getRef().child("email").setValue(Email);
                            snapshot.getRef().child("name").setValue(Name);
                            snapshot.getRef().child("mobile").setValue(Mobile);
                            snapshot.getRef().child("password").setValue(Pass);
                            snapshot.getRef().child("address").setValue(Address);

                            Toast.makeText(AdminEditProfile.this, "Details Updated!", Toast.LENGTH_SHORT).show();

                            Intent i1=new Intent(AdminEditProfile.this ,AdminProfile.class);
                            startActivity(i1);

                        } else {
                            // User not found
                            Toast.makeText(AdminEditProfile.this, "User not found", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Handle any errors
                        Toast.makeText(AdminEditProfile.this, "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
