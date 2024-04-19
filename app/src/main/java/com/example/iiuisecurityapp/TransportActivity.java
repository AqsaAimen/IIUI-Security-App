package com.example.iiuisecurityapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class TransportActivity extends AppCompatActivity {
    Button vehicleinfo;
    Button vehleave5;
    ImageButton search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport);
        vehicleinfo=(Button)findViewById(R.id.detailpess);
        vehicleinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentinfo=new Intent(TransportActivity.this,VehicleEntrance.class);
                startActivity(intentinfo);
            }
        });
        vehleave5=(Button)findViewById(R.id.vehleav);
        vehleave5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentlea=new Intent(TransportActivity.this,VehiclesLeaving.class);
                startActivity(intentlea);

            }
        });
        search=(ImageButton)findViewById(R.id.search5);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentsearch=new Intent(TransportActivity.this,Search.class);
                startActivity(intentsearch);
            }
        });

    }
}