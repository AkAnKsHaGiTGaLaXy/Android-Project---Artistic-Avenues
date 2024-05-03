package com.example.artisticavenues;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CustomerSignUp extends AppCompatActivity {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    TextView LogIn;
    Button button;
    TextInputEditText name, email, contactno, password, confirmpass, address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_sign_up);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        contactno = findViewById(R.id.contactno);
        address = findViewById(R.id.address);
        password = findViewById(R.id.password);
        confirmpass = findViewById(R.id.confirmpass);
        button = findViewById(R.id.button);
        LogIn = findViewById(R.id.LogIn);

        LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(CustomerSignUp.this, CustomerLogin.class);
                startActivity(loginIntent);
            }
        });


        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Customer"); // "Admin" is the top-level node
        //Toast.makeText(this, "All Ok", Toast.LENGTH_SHORT).show();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertUserData();
            }
        });
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
                        Intent LoginSucces = new Intent(CustomerSignUp.this, CustomerLogin.class);
                        startActivity(LoginSucces);
                        finish();

                    } else {
                        Toast.makeText(this, "Failed to add user data", Toast.LENGTH_SHORT).show();
                    }
                });



    }}

