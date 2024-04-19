package com.example.iiuisecurityapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserAddress extends AppCompatActivity {
    EditText etaddress;
    EditText etcity;
    EditText etphoneno;
    Button save2;
    AllUsersAddress allUsersAddress;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_address);
        etaddress=(EditText)findViewById(R.id.address);
        etcity=(EditText)findViewById(R.id.city);
        etphoneno=(EditText)findViewById(R.id.phoneno);
        save2=(Button)findViewById(R.id.save2);
        allUsersAddress=new AllUsersAddress();
        databaseReference=firebaseDatabase.getInstance().getReference("UsersAddress");
        save2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploaddata2();
            }
        });

    }
    private void uploaddata2(){
        final String address=etaddress.getText().toString();
        final String city=etcity.getText().toString();
        final String phoneno=etphoneno.getText().toString();
        allUsersAddress.setAddress(etaddress.getText().toString());
        allUsersAddress.setCity(etcity.getText().toString());
        allUsersAddress.setPhoneno(etphoneno.getText().toString());
        databaseReference.push().setValue(allUsersAddress);
        Toast.makeText(UserAddress.this,"Saved to database",Toast.LENGTH_SHORT).show();


    }

}