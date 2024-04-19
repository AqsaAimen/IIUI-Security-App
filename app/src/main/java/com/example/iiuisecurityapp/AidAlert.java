package com.example.iiuisecurityapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Html;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class AidAlert extends AppCompatActivity {
Button mybutton;
EditText ed;
AlertDialog.Builder alert;
SwitchCompat switchCompat;
    public static final int CAMERA_PERM_CODE = 101;
    public static final int CAMERA_REQUEST_CODE = 102;
    public static final int GALLERY_REQUEST_CODE = 105;
    FusedLocationProviderClient fusedLocationProviderClient;
    ImageView imageView2;
    ImageView imageView3;
    String currentPhotoPath;
    ProgressDialog progressDialog7;
    TextView b1;
    TextView report;
    TextView textView2;
    CardView cardView;
    EditText editTextname;
    EditText edittextalert;
    int PLACE_PICKER_REQUEST=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aid_alert);
        //fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        //switchCompat=(SwitchCompat)findViewById(R.id.switchbtn2);
        //switchCompat.setChecked();
        //switchCompat.setOnClickListener();
        switchCompat = findViewById(R.id.switchbtn2);
        imageView2 = (ImageView) findViewById(R.id.image2);
        imageView3=(ImageView) findViewById(R.id.image);
        textView2 = (TextView) findViewById(R.id.textview4);
        cardView = (CardView) findViewById(R.id.cardview1);
        editTextname=(EditText) findViewById(R.id.edittext);
        edittextalert=(EditText) findViewById(R.id.b3);
        b1=(TextView) findViewById(R.id.b1);
        report=(TextView) findViewById(R.id.b2);
        //relativeLayout=(RelativeLayout)findViewById(R.id.textview1);
        //SharedPreferences sharedPreferences = getSharedPreferences("save", MODE_PRIVATE);
        //switchCompat.setChecked(sharedPreferences.getBoolean("value", true));
        switchCompat.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if (switchCompat.isChecked()) {
                    cardView.setVisibility(View.VISIBLE);
                    //textView.setText(fusedLocationProviderClient.getCurrentLocation(supportMapFragment.getMapAsync(}
                } else {
                    cardView.setVisibility(View.GONE);
                }
            }
        });
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(AidAlert.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    getLocation();
                } else {
                    ActivityCompat.requestPermissions(AidAlert.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
                }
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askCameraPermissions();
            }
        });
        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog7=new ProgressDialog(AidAlert.this);
                progressDialog7.setTitle("sending alert");
                progressDialog7.setMessage("please wait");
                progressDialog7.show();
                String name=editTextname.getText().toString();
                String alert5=edittextalert.getText().toString();
                Intent intentalert=new Intent(AidAlert.this,Mapbox.class);
                intentalert.putExtra("name",name);
                intentalert.putExtra("alert ",alert5);
                startActivity(intentalert);
            }
        });
        //String name=editText.getText().toString();
        //Intent intent=new Intent(AidAlert.this,)

            }
    private void askCameraPermissions() {
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.CAMERA}, CAMERA_PERM_CODE);
        }else {
            opencamera();
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
                    Geocoder geocoder=new Geocoder(AidAlert.this, Locale.getDefault());
                    try {
                        List<Address> addresses=geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
                        textView2.setText(Html.fromHtml("<font color='#EFECF5'><b>Location:</b></br></font>"+addresses.get(0).getLatitude() + addresses.get(0).getLongitude()+ addresses.get(0).getCountryName()+addresses.get(0).getLocality()+addresses.get(0).getAddressLine(0)));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==CAMERA_PERM_CODE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

            }else {
                Toast.makeText(this, "Camera Permission is Required to Use camera.", Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST_CODE) {
            Bitmap image=(Bitmap) data.getExtras().get("data");
            imageView3.setImageBitmap(image);




        }




    }




    private void opencamera(){
        Intent camera=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camera,102);
    }


}