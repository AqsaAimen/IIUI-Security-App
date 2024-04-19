package com.example.iiuisecurityapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Ambulance extends AppCompatActivity {
    ImageButton emergency;
    TextView security;
    TextView b9;
    ImageButton imagerr;
    FloatingActionButton chatting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambulance);
        emergency=(ImageButton)findViewById(R.id.emergency);
        emergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentb = new Intent(Ambulance.this,Emergency.class);
                startActivity(intentb);
            }
        });
        security= (TextView) findViewById(R.id.security);
        security.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentm = new Intent(Intent.ACTION_DIAL);
                intentm.setData(Uri.parse("tel:0519262610"));
                startActivity(intentm);
            }
        });
        b9=(TextView)findViewById(R.id.b9);
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentu= new Intent(Intent.ACTION_DIAL);
                intentu.setData(Uri.parse("tel:999"));
                startActivity(intentu);
            }
        });
        imagerr=(ImageButton)findViewById(R.id.imagerr);
        imagerr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentrr1=new Intent(Ambulance.this,MapsActivity.class);
                startActivity(intentrr1);
            }
        });
        chatting=(FloatingActionButton)findViewById(R.id.chatting);
        chatting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent smsIntent = new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", "03155126097", null));
                smsIntent.putExtra("sms_body","please immediately, reach at the my location  I need your help");
                startActivity(smsIntent);
            }
        });
    }
    }
