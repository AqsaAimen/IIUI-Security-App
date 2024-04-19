package com.example.iiuisecurityapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ActivityInformation extends AppCompatActivity {
    ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        imageButton=(ImageButton)findViewById(R.id.backbtn);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ActivityInformation.this,iiuisecurityfeatures.class);
                startActivity(intent);
            }
        });
    }
}