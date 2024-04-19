package com.example.iiuisecurityapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class MapsActivity extends AppCompatActivity {
    SupportMapFragment supportMapFragment;
    FusedLocationProviderClient fusedLocationProviderClient;
    SwitchCompat switchCompatbtn;
    Marker marker;
    TextView address;
    ChildEventListener mchildeventlistener;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        switchCompatbtn=(SwitchCompat) findViewById(R.id.switchbtn7);
        address=(TextView) findViewById(R.id.address);
        supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.google_maps);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getcurrentlocation();
        }else
        {
            ActivityCompat.requestPermissions(MapsActivity.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},4400);
        }
        switchCompatbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    getLocation();
                } else {
                    ActivityCompat.requestPermissions(MapsActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
                }
            }
        });

    }
    private void getcurrentlocation() {
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
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(final Location location) {
                GoogleMap googleMap;
                if(location !=null){
                    supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(final GoogleMap googleMap) {

                            LocationHelper helper=new LocationHelper(location.getLongitude(),location.getLatitude());
                            FirebaseDatabase.getInstance().getReference("current location").child(UUID.randomUUID().toString()).setValue(helper).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(MapsActivity.this,"location saved",Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                            LatLng latLng=new LatLng(location.getLatitude(),location.getLongitude());
                            MarkerOptions options=new MarkerOptions().position(latLng).title("I am here");
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));
                            googleMap.addMarker(options);
                            FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
                            DatabaseReference databaseReference=firebaseDatabase.getReference().child(UUID.randomUUID().toString()+ "UsersLocation");
                            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    for(DataSnapshot s:snapshot.getChildren()){
                                        LocationHelper users=s.getValue(LocationHelper.class);
                                        LatLng location=new LatLng(users.getLatitude(),users.getLongitude());
                                        googleMap.addMarker(new MarkerOptions().position(location).icon(BitmapDescriptorFactory.defaultMarker()).title("user"));
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        }
                    });
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==4400){
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                getcurrentlocation();
            }
        }
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
                    Geocoder geocoder=new Geocoder(MapsActivity.this, Locale.getDefault());
                    try {
                        List<Address> addresses=geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
                        address.setText(Html.fromHtml("<font color='#EFECF5'><b>Location:</b></br></font>"+addresses.get(0).getLatitude() + addresses.get(0).getLongitude()+ addresses.get(0).getCountryName()+addresses.get(0).getLocality()+addresses.get(0).getAddressLine(0)));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}