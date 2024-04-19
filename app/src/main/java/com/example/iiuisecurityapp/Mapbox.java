package com.example.iiuisecurityapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;

public class Mapbox extends AppCompatActivity {
    MapView mapView;
TextView name1;
TextView alertmm;
    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onSaveInstanceState(@Nullable Bundle outstate) {
        super.onSaveInstanceState(outstate);
        mapView.onSaveInstanceState(outstate);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_mapbox);
        com.mapbox.mapboxsdk.Mapbox.getInstance(this,"pk.eyJ1Ijoib3p5Z2UtMTIzIiwiYSI6ImNrbjFlYjdtdDBsdWcydWxycjJ4aDQ5cjgifQ.w3B899oVRyfclKTzJlyu-A");
        setContentView(R.layout.activity_mapbox);
        mapView= findViewById(R.id.mapview);
        mapView.onCreate(savedInstanceState);
//        mapView.setScrollBarStyle(Integer.parseInt(Style.MAPBOX_STREETS));
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull MapboxMap mapboxMap) {
                MarkerOptions options=new MarkerOptions();
                options.title("User");
                options.position(new LatLng(33.656731,73.024761));
                mapboxMap.addMarker(options);
                mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {

                        // Map is set up and the style has loaded. Now you can add data or make other map adjustments


                    }
                });

            }
        });
         name1=(TextView) findViewById(R.id.name);
         alertmm=(TextView) findViewById(R.id.alertmm);
         String name=getIntent().getStringExtra("name");
         String alertnn=getIntent().getStringExtra("alert5");
         name1.setText(name);
         alertmm.setText(alertnn);
        /*mapView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/
    }

}
