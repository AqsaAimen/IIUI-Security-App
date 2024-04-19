package com.example.iiuisecurityapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.material.textfield.TextInputEditText;

public class ForgotpasswordActivity extends AppCompatActivity implements View.OnClickListener {
TextInputEditText textInputEditTextemail;
Button confirmbtn;
ImageButton imagebtn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgotpassword);
        final Intent intent=getIntent();
        final Intent intent1=getIntent();
        //getActionBar().setDisplayHomeAsUpEnabled(true);
        textInputEditTextemail=(TextInputEditText) findViewById(R.id.email);
        confirmbtn=(Button) findViewById(R.id.b1);
        confirmbtn.setOnClickListener((View.OnClickListener) this);
        confirmbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(ForgotpasswordActivity.this,ConfirmPassword.class);
                startActivity(intent1);
                finish();
            }
        });
        imagebtn2=(ImageButton)findViewById(R.id.imagebtn);
        imagebtn2.setOnClickListener((View.OnClickListener) this);
        imagebtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(ForgotpasswordActivity.this,LoginActivity.class);
                startActivity(intent2);
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent intent1=new Intent(ForgotpasswordActivity.this,ConfirmPassword.class);
        startActivity(intent1);
        finish();
    }
   /* public void onClick1(View v) {
        Intent intent1=new Intent(ForgotpasswordActivity.this,LoginActivity.class);
        startActivity(intent1);
        finish();
    }*/
}