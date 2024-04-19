package com.example.iiuisecurityapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static androidx.constraintlayout.motion.widget.Debug.getLocation;

public class iiuisecurityfeatures extends AppCompatActivity {
    ImageButton call_btn;
    //ImageButton call_btn2;
    ImageButton aidbtn;
    ImageButton btn2;
    ImageButton emergencybtn;
    ImageButton safety;
    ImageButton showalert;
    ImageView logobtn;
    Button informationbtn;
    ImageButton imagerr;
    ProgressDialog progressDialog;
    ProgressDialog progressDialog1;
    ProgressDialog progressDialog2;
    ProgressDialog progressDialog3;
    ProgressDialog progressDialog4;
    FusedLocationProviderClient fusedLocationProviderClient;
    FloatingActionButton floatbtn;
    TextView text1;
    ProgressBar progressBar;
    ImageView imageView;
    ImageButton transportbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iiuisecurityfeatures);
        //call_btn1=(ImageButton) findViewById(R.id.imagebtn7);
        text1 = (TextView) findViewById(R.id.textview1);
        floatbtn = (FloatingActionButton) findViewById(R.id.chating);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ImageButton call_btn = (ImageButton) findViewById(R.id.imagebtn7);
        call_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog1 = new ProgressDialog(iiuisecurityfeatures.this);
                progressDialog1.setTitle("Emergency call");
                progressDialog1.setMessage("please wait");
                progressDialog1.show();
                Intent intent10 = new Intent(Intent.ACTION_DIAL);
                intent10.setData(Uri.parse("tel:911"));
                startActivity(intent10);

            }
        });
        logobtn = (ImageView) findViewById(R.id.logobtn);
        logobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog2 = new ProgressDialog(iiuisecurityfeatures.this);
                progressDialog2.setTitle("menu is opening");
                progressDialog2.setMessage("please wait ");
                progressDialog2.show();
                Intent intent = new Intent(iiuisecurityfeatures.this, MenuActivity.class);
                startActivity(intent);
            }
        });
        progressBar = (ProgressBar) findViewById(R.id.progessbar2);
        imageView = (ImageView) findViewById(R.id.image5);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);

                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.INVISIBLE);
                        Intent intent8 = new Intent(iiuisecurityfeatures.this, Services.class);
                        startActivity(intent8);

                    }
                }, 4000);

            }
        });
        ImageButton btn2 = (ImageButton) findViewById(R.id.secbtn);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog3 = new ProgressDialog(iiuisecurityfeatures.this);
                progressDialog3.setTitle("send Alert to IIUI Security services");
                progressDialog3.setMessage("please wait");
                progressDialog3.show();
                Intent intentx = new Intent(Intent.ACTION_DIAL);
                intentx.setData(Uri.parse("tel:051-9262610"));
                startActivity(intentx);

            }
        });
        emergencybtn = (ImageButton) findViewById(R.id.emerbtn);
        emergencybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog4 = new ProgressDialog(iiuisecurityfeatures.this);
                progressDialog4.setTitle("send Alert to IIUI Emergency services");
                progressDialog4.setMessage("please wait");
                progressDialog4.show();
                Intent intentz = new Intent(Intent.ACTION_DIAL);
                intentz.setData(Uri.parse("tel:112"));
                startActivity(intentz);
            }
        });
        /*progressDialog=new ProgressDialog(iiuisecurityfeatures.this);
        progressDialog.setTitle("wait to send alert");
        progressDialog.setMessage("please wait for alert");
        progressDialog.show();*/
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        informationbtn = (Button) findViewById(R.id.informbtn);
        informationbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(iiuisecurityfeatures.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    getLocation();
                } else {
                    ActivityCompat.requestPermissions(iiuisecurityfeatures.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
                }
            }
        });
        imagerr = (ImageButton) findViewById(R.id.imagerr);
        imagerr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentrrr = new Intent(iiuisecurityfeatures.this, MapsActivity.class);
                startActivity(intentrrr);
            }
        });
        //fusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(this);
        safety = (ImageButton) findViewById(R.id.imagebtn9);
        safety.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentff = new Intent(iiuisecurityfeatures.this, CampusSafety.class);
                startActivity(intentff);
            }
        });
        showalert = (ImageButton) findViewById(R.id.imagebtn8);
        showalert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentalert = new Intent(iiuisecurityfeatures.this, Mapbox.class);
                startActivity(intentalert);
            }
        });
        floatbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.fromParts("sms","03105046515","please help me I need your help immediately ")));
            }

        });

        transportbtn=(ImageButton)findViewById(R.id.sec2btn);
        transportbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           Intent intenttransport=new Intent(iiuisecurityfeatures.this,TransportActivity.class);
           startActivity(intenttransport);
            }
        });
    }


    //getSupportActionBar().setIcon(R.drawable.th);

    /*public void setSupportActionBar(Toolbar supportActionBar) {
        this.supportActionBar = supportActionBar;
    }*/
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
                Geocoder geocoder=new Geocoder(iiuisecurityfeatures.this, Locale.getDefault());
                try {
                    List<Address> addresses=geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
                    text1.setText(Html.fromHtml("<font color='#EFECF5'><b>Location:</b></br></font>"+addresses.get(0).getLatitude() + addresses.get(0).getLongitude()+ addresses.get(0).getCountryName()+addresses.get(0).getLocality()+addresses.get(0).getAddressLine(0)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            }
        });
    }

        public ActionBar getSupportActionBar () {
            return null;
        }

    /*public void onClick(View view) {
        Intent intent10=new Intent(Intent.ACTION_CALL,Uri.parse("tel:911"));
                /*if(ActivityCompat.checkSelfPermission(iiuisecurityfeatures.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                    return;
                }*/

}