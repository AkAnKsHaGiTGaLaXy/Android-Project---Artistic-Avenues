package com.example.artisticavenues;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class AdminVerifyArtist extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    TextView NAME, EMAIL, MOBILE, ADDRESS;
    String email="";
    AppCompatButton accept,delete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_verify_artist);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Artist");
        Intent intent = getIntent();
        email = intent.getStringExtra("Email");

        NAME=findViewById(R.id.textView);
        EMAIL=findViewById(R.id.textView2);
        MOBILE=findViewById(R.id.textView3);
        ADDRESS=findViewById(R.id.textView4);
        accept=findViewById(R.id.accept);
        delete=findViewById(R.id.delete);

        // Create a query to find the specific user by email
        Query query = databaseReference.orderByChild("email").equalTo(email);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String Name = snapshot.child("name").getValue(String.class);
                    String Email = snapshot.child("email").getValue(String.class);
                    String Mobile = snapshot.child("mobile").getValue(String.class);
                    String Address = snapshot.child("location").getValue(String.class);

                    NAME.setText(Name);
                    EMAIL.setText(Email);
                    MOBILE.setText(Mobile);
                    ADDRESS.setText(Address);


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle any errors
                Toast.makeText(AdminVerifyArtist.this, "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the reject button click

                // Create a query to find the specific child in the "Owner" entity
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {



                            // The child with the matching email has been found, remove it
                            snapshot.getRef().removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        // Child removed successfully
                                        Toast.makeText(v.getContext(), "Customer Profile  Deleted", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(AdminVerifyArtist.this,ArtistList.class);
                                        startActivity(intent);
                                        // Finish the current activity (optional)
                                        finish();
                                    } else {
                                        // Failed to remove the child
                                        Toast.makeText(v.getContext(), "Failed to Delete Customer", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                            break;
                        }
                    }


                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Handle any errors
                    }
                });
            }
        });

        }
    }
