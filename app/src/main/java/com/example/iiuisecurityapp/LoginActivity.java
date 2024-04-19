package com.example.iiuisecurityapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.StorageReference;


import java.util.Objects;

import static com.example.iiuisecurityapp.R.id.forgot;
import static com.example.iiuisecurityapp.R.id.signup;

public class LoginActivity extends AppCompatActivity  {
TextInputEditText textemail50;
TextInputEditText textpass50;
Button bforgotpass;
Button btnsignin;
Button bsignup;
AllLoginUsers allLoginUsers;
DatabaseReference databaseReference;
FirebaseDatabase firebaseDatabase;
ProgressDialog progressDialog;
    private static final String TAG = "FirebaseEmailPassword";
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        textemail50=(TextInputEditText) findViewById(R.id.username50);
        textpass50=(TextInputEditText) findViewById(R.id.userpass50);
        bforgotpass=(Button) findViewById(forgot);
        btnsignin=(Button) findViewById(R.id.signin);
        bsignup=(Button) findViewById(signup);
        allLoginUsers=new AllLoginUsers();
        databaseReference=firebaseDatabase.getInstance("https://iiuisecurityapp-default-rtdb.firebaseio.com").getReference().child("loginUsers");
        firebaseAuth=FirebaseAuth.getInstance();
        final Intent intent=getIntent();
        final Intent intent1=getIntent();
        //databaseReference=firebaseDatabase.getInstance().getReference().child("LoginUsers");
        bforgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,ForgotpasswordActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*progressDialog=new ProgressDialog(LoginActivity.this);
                progressDialog.setTitle("you are login");
                progressDialog.setMessage("please wait");
                progressDialog.show();
                Intent intent8=new Intent(LoginActivity.this,iiuisecurityfeatures.class);
                startActivity(intent8);
                finish();*/
                signIn(textemail50.getText().toString(),textpass50.getText().toString());
                UPLOAD3();

            }
        });
        bsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = firebaseAuth.getCurrentUser();

    }



   /* private void Login(){
         final String mail= textemail50.getEditableText().toString().trim();
         final String password=textpass50.getEditableText().toString().trim();
         if(mail.isEmpty()){
             textemail50.setError("email is required");
             textemail50.requestFocus();
         }
         if(password.isEmpty()){
             textpass50.setError("password is required");
             textpass50.requestFocus();
         }

        firebaseAuth.signInWithEmailAndPassword(mail,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(LoginActivity.this,"Login successfully",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(LoginActivity.this,"Failed to login",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }*/
   private void signIn(String email, String password) {
       Log.e(TAG, "signIn:" + email);
       if (!validateForm(email, password)) {
           return;
       }

       firebaseAuth.signInWithEmailAndPassword(email, password)
               .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       if (task.isSuccessful()) {
                           Log.e(TAG, "signIn: Success!");

                           // update UI with the signed-in user's information
                           FirebaseUser user = firebaseAuth.getCurrentUser();
                           //updateUI(user);
                           progressDialog=new ProgressDialog(LoginActivity.this);
                           progressDialog.setTitle("you are login");
                           progressDialog.setMessage("please wait");
                           progressDialog.show();
                           Intent intent8=new Intent(LoginActivity.this,iiuisecurityfeatures.class);
                           startActivity(intent8);
                           finish();
                       } else {
                           Log.e(TAG, "signIn: Fail!", task.getException());
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

private  void UPLOAD3(){
       final  String enailo=textemail50.getText().toString();
                 final String passwardo=textpass50.getText().toString();
                 allLoginUsers.setEmail(textemail50.getText().toString());
                 allLoginUsers.setPassword(textpass50.getText().toString());

                 databaseReference.push().setValue(allLoginUsers);
    Toast.makeText(LoginActivity.this,"Saved to database",Toast.LENGTH_SHORT).show();
}

}
