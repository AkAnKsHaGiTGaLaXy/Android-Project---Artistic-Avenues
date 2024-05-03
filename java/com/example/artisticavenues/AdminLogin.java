package com.example.artisticavenues;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminLogin extends AppCompatActivity {

    private DatabaseReference usersRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        usersRef = FirebaseDatabase.getInstance().getReference("Admin");

        Button button = findViewById(R.id.button);
        EditText emailEditText = findViewById(R.id.email);
        EditText passwordEditText = findViewById(R.id.password);
        TextView SignUp = findViewById(R.id.SignUp);

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i1=new Intent(AdminLogin.this ,AdminSignUp.class);
                startActivity(i1);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (TextUtils.isEmpty(email) && TextUtils.isEmpty(password)) {
                    Toast.makeText(AdminLogin.this, "Please enter both email and password", Toast.LENGTH_SHORT).show();
                    return;
                }

                usersRef.addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        boolean isAuthenticated = false;
                        String userId="";
                        //Toast.makeText(AdminLogin.this, "okk", Toast.LENGTH_SHORT).show();


                        for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                            String storedEmail = userSnapshot.child("email").getValue(String.class);
                            String storedPassword = userSnapshot.child("password").getValue(String.class);
                            userId = userSnapshot.child("id").getValue(String.class);

                            // Compare the input email with storedEmail and hashed password with input password
                            if (email.equals(storedEmail) && yourPasswordHashingFunction(password).equals(storedPassword)) {
                                isAuthenticated = true;

                                break;
                            }
                            //Toast.makeText(AdminLogin.this, "okk", Toast.LENGTH_SHORT).show();
                        }

                        if (isAuthenticated) {
                            // Authentication successful
                            SessionManager sm = new SessionManager(getApplicationContext());
                            sm.createSession(userId,email);
                            Toast.makeText(AdminLogin.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            Intent LoginSucces=new Intent(AdminLogin.this ,AdminProfile.class);
                            startActivity(LoginSucces);
                            finish();
                        } else {
                            // Authentication failed
                            Toast.makeText(AdminLogin.this, "Authentication failed. Check email and password.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(AdminLogin.this, "Database Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }

    // Replace this with your actual password hashing function (e.g., bcrypt)
    private String yourPasswordHashingFunction(String password) {
        // Implement your password hashing logic here
        return password; // For demonstration, just returning the plain password
    }
}