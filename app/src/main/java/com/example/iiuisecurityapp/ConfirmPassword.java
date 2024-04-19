package com.example.iiuisecurityapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

import static com.example.iiuisecurityapp.DatabaseContract.Users.TABLE_NAME;

public class ConfirmPassword extends AppCompatActivity {
    TextInputEditText textInputEditTextnewPASSWORD;
    TextInputEditText textInputEditTextConfirmpass;
    TextInputEditText textInputEditTextemail;
    Button resetbtn;
    SQLiteDatabase db;
    String email;
    ImageButton imagebtn4;
    DatabaseHelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_password);
        final Intent intent = getIntent();
        //getSupportActionBar().setTitle("Change Password");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //textInputEditTextPASSWORD=(TextInputEditText) findViewById(R.id.textimputpassword);
        //textInputEditTextConfirmpass=(TextInputEditText) findViewById(R.id.textimputconfirmpassword);
        //Button B1=(Button) findViewById(R.id.resetbtn);
        //dbhelper = new DatabaseHelpher(this);
        textInputEditTextemail=(TextInputEditText) findViewById(R.id.email);
        textInputEditTextnewPASSWORD = (TextInputEditText) findViewById(R.id.newpassword);
        textInputEditTextConfirmpass = (TextInputEditText) findViewById(R.id.confirmpassword);
        resetbtn = (Button) findViewById(R.id.resetbtn);
        /*imagebtn2 = (ImageButton) findViewById(R.id.imagebtn2);
        imagebtn2.setOnClickListener((View.OnClickListener) this);
        imagebtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(ConfirmPassword.this,LoginActivity.class);
                startActivity(intent2);
                finish();
            }
        });*/
         resetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              updatepassword();
            }
            } );
        imagebtn4=(ImageButton) findViewById(R.id.imagebtn2);
        imagebtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent6=new Intent(ConfirmPassword.this,ForgotpasswordActivity.class);
                startActivity(intent6);
                finish();
            }
        });
    }
    private boolean ValidateEmail() {
        String emailInput = textInputEditTextemail.getEditableText().toString().trim();
        if (emailInput.isEmpty()) {
            textInputEditTextemail.setError("Field cannot be empty");
            return false;
        }
        else {

           textInputEditTextemail.setError(null);
           return true;
        }
    }
    public void InsertD(View v) {
        // dbhelper=new DatabaseHelper(this);
        db=dbhelper.getWritableDatabase();

        //textInputEditTextentername = (TextInputLayout) findViewById(R.id.inputname);
        textInputEditTextemail = (TextInputEditText) findViewById(R.id.inputemail);
        textInputEditTextnewPASSWORD = (TextInputEditText) findViewById(R.id.inputpassword);
        textInputEditTextConfirmpass = (TextInputEditText) findViewById(R.id.inputconfirmpass);
        String val1 = textInputEditTextemail.getText().toString();
        String val2 = textInputEditTextnewPASSWORD.getText().toString();
        String val3 = textInputEditTextConfirmpass.getText().toString();
        //String val4 = textInputEditTextenterconfirmpass.getEditText().getText().toString();
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.Users.COL_Email, val1);
        values.put(DatabaseContract.Users.COL_newPassword, val2);
        //values.put(DatabaseContract.Users.COL_Password, val3);
        values.put(DatabaseContract.Users.COL_confirmPass, val3);
        long newRowId = db.insert(TABLE_NAME, null, values);
        if (newRowId > 0) {
            Toast.makeText(this, "your password reset: " + newRowId, Toast.LENGTH_SHORT).show();
        }
        db.close();
    }
    void updatepassword()
    {
        String val1=textInputEditTextnewPASSWORD.getText().toString();
        String val2=textInputEditTextConfirmpass.getText().toString();
        String email=textInputEditTextemail.getText().toString();
        if(val1.isEmpty()&&val2.isEmpty())
        {
            Toast.makeText(this,"fill all fiels",Toast.LENGTH_SHORT).show();
            return;
        }
        if (!val1.contentEquals(val2))
        {
            Toast.makeText(this,"password doesnot match",Toast.LENGTH_SHORT).show();
            return;
        }
        /*if(!dbhelper.checkemail(email))
        {
            Toast.makeText(this, "email doesn't exist", Toast.LENGTH_LONG).show();
            return;
        } else {
            Boolean DB=dbhelper.updatepassword1(email, val1);
            if(val2.equals(DB))
            Toast.makeText(this, "password reset successfully", Toast.LENGTH_SHORT).show();
            emptyInputEditText();

            /*Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();*/
        }


    private void emptyInputEditText()
    {
        textInputEditTextnewPASSWORD.setText("");
        textInputEditTextConfirmpass.setText("");
    }
        }

