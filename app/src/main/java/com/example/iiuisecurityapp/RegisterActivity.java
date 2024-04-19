package com.example.iiuisecurityapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.auth.User;

import java.util.Objects;

import static com.example.iiuisecurityapp.DatabaseContract.Users.TABLE_NAME;

public class RegisterActivity extends AppCompatActivity  {
    TextInputEditText enteremail;
    TextInputEditText enterpass;
    TextInputEditText enterconfirmpass;
    Button creataccountbtn;
    ImageButton imageButton;
    FirebaseAuth firebaseAuth;
    FirebaseUser users;
    //private String newname, newemail, newpassword, confirmpassword;
    private static final String TAG = "FirebaseEmailPassword";
    private String email;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        enteremail = findViewById(R.id.edittextemail);
        enterpass = findViewById(R.id.edittextpassword);
        creataccountbtn=(Button) findViewById(R.id.createaccountbtn);
        enterconfirmpass = (TextInputEditText) findViewById(R.id.edittextconfirmpass);
        firebaseAuth = FirebaseAuth.getInstance();
        imageButton = (ImageButton) findViewById(R.id.arrow1);
        //imageButton.setOnClickListener((View.OnClickListener) this);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        creataccountbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount(enteremail.getText().toString(),enterpass.getText().toString());
            }
        });
    }

    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        //updateUI(currentUser);
    }

    /*public void Register() {


        enteremail = (TextInputLayout) findViewById(R.id.inputemail);
        enterpass = (TextInputLayout) findViewById(R.id.inputpassword);
        enterconfirmpass = (TextInputLayout) findViewById(R.id.inputconfirmpass);
        final String enailo = enteremail.getEditText().getText().toString();
        final String passwardo = enterpass.getEditText().getText().toString();
        String confirmpassword = enterconfirmpass.getEditText().getText().toString();

        if (enailo.isEmpty()) {
            enteremail.setError("email is required");
            enteremail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(enailo).matches()) {
            enteremail.setError("please enter a valid password");
            enteremail.requestFocus();
        }
        if (passwardo.isEmpty()) {
            enterpass.setError("password is required");
            enterpass.requestFocus();
            return;
        }
        if (confirmpassword.isEmpty()) {
            enterconfirmpass.setError("again password is required");
            enterconfirmpass.requestFocus();
            return;
        }



    }*/
    private void createAccount(String email,String password) {
        Log.e(TAG, "createAccount:" + email);
        if (!validateForm(email, password)) {
            return;
        }
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.e(TAG, "createAccount: Success!");

                            // update UI with the signed-in user's information
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            //updateUI(user);
                        } else {
                            Log.e(TAG, "createAccount: Fail!", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed!", Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                    }
                });
    }


    private boolean validateForm(String email, String password) {

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (password.length() < 6) {
            Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }



    /*private void updateUI(FirebaseUser user) {
        if (user != null) {
            txtStatus.setText("User Email: " + user.getEmail() + "(verified: " + user.isEmailVerified() + ")");
            txtDetail.setText("Firebase User ID: " + user.getUid());

        }
    }*/
}