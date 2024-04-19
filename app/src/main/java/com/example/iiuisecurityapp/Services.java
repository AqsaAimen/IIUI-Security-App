package com.example.iiuisecurityapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Services extends AppCompatActivity {
    ImageButton health;
    ImageButton covid;
    ImageButton ambulance;
    ImageButton fire;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
        ambulance=(ImageButton) findViewById(R.id.imagebtn3);
        covid=(ImageButton) findViewById(R.id.imagebtn2);
        health=(ImageButton) findViewById(R.id.imagebtn);
        covid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewActivity newActivity=new NewActivity();
                newActivity.show(getSupportFragmentManager(),"emergencyactivity");

            }
        });
        ambulance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Services.this,Ambulance.class);
                startActivity(intent);
            }
        });
        health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentr=new Intent(Services.this,AidAlert.class);
                startActivity(intentr);
            }
        });
    }
}