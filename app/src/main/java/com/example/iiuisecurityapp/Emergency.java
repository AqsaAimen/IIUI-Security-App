package com.example.iiuisecurityapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Emergency extends AppCompatActivity {
    ImageButton location;
    ImageButton alert;
    ImageButton acknowledge;
    ImageView view,view2,view3;
    ImageButton imagerr;
    FusedLocationProviderClient fusedLocationProviderClient;
    TextView textView;
    Button information;
    TextView sendalert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);
        view=(ImageView) findViewById(R.id.view);
        view2=(ImageView) findViewById(R.id.view2);
        view3=(ImageView) findViewById(R.id.view3);
        information=(Button) findViewById(R.id.information);
        alert=(ImageButton) findViewById(R.id.alert);
        sendalert=(TextView) findViewById(R.id.alert1);
        textView=(TextView) findViewById(R.id.textviewrr);
        acknowledge=(ImageButton) findViewById(R.id.acknowledge);
        location=(ImageButton) findViewById(R.id.location);
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.setVisibility(View.VISIBLE);
            }
        });
        sendalert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view2.setVisibility(View.VISIBLE);
            }
        });
        acknowledge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view3.setVisibility(View.VISIBLE);
            }
        });
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(Emergency.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    getLocation();
                } else {
                    ActivityCompat.requestPermissions(Emergency.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
                }
            }
        });
        imagerr=(ImageButton)findViewById(R.id.imagerr);
        imagerr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentrr2=new Intent(Emergency.this,MapsActivity.class);
                startActivity(intentrr2);
            }
        });
    }
    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location=task.getResult();
                if(location !=null){
                    Geocoder geocoder=new Geocoder(Emergency.this, Locale.getDefault());
                    try {
                        List<Address> addresses=geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
                        textView.setText(Html.fromHtml("<font color='#EFECF5'><b>Location:</b></br></font>"+addresses.get(0).getLatitude() + addresses.get(0).getLongitude()+ addresses.get(0).getCountryName()+addresses.get(0).getLocality()+addresses.get(0).getAddressLine(0)+addresses.get(0).getPostalCode()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}