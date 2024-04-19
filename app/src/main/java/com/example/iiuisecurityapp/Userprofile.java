package com.example.iiuisecurityapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class Userprofile extends AppCompatActivity {
    CircleImageView circleImageView;
    Uri imageuri;
    UploadTask uploadTask;
    StorageReference storageReference;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseFirestore firebaseFirestore;
    DocumentReference documentReference;
    private static final int PICK_IMAGE=1;
    CircleImageView profileimg;
    EditText etname;
    EditText etprofession;
    EditText etBio;
    EditText etemail;
    EditText etwebsite;
    AllUserMembers allUserMembers;
    FirebaseUser user;
    String currentuserid;
    Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);
        allUserMembers=new AllUserMembers();
        etname=(EditText)findViewById(R.id.name);
        etprofession=(EditText)findViewById(R.id.profession);
        etBio=(EditText)findViewById(R.id.bio);
        etemail=(EditText)findViewById(R.id.email);
        etwebsite=(EditText)findViewById(R.id.website);
        profileimg=(CircleImageView)findViewById(R.id.profileimg);
        save=(Button) findViewById(R.id.save);
        //FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        //currentuserid =user.getUid();

        //documentReference=firebaseFirestore.collection("user").document(currentuserid);
        storageReference= FirebaseStorage.getInstance().getReference().child("profile image");

        databaseReference=firebaseDatabase.getInstance().getReference().child("UsersProfile");
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploaddata1();

            }
        });
        profileimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent00=new Intent();
                intent00.setType("image/*");
                intent00.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent00,"select picture"),PICK_IMAGE);
                startActivityForResult(intent00,PICK_IMAGE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if(requestCode==PICK_IMAGE || requestCode==RESULT_OK || data!=null || data.getData() !=null){
                imageuri=data.getData();
                profileimg.setImageURI(imageuri);

                Picasso.get().load(imageuri).into(circleImageView);
            }
        }catch (Exception e){
            Toast.makeText(Userprofile.this,"Error",Toast.LENGTH_SHORT).show();
        }

    }
    private void uploaddata1(){
        final String name=etname.getText().toString();
        final String bio=etBio.getText().toString();
        final String web=etwebsite.getText().toString();
        final String prof=etprofession.getText().toString();
        final String email=etemail.getText().toString();
        allUserMembers.setName(etname.getText().toString());
        allUserMembers.setProf(etprofession.getText().toString());
        allUserMembers.setBio(etBio.getText().toString());
        allUserMembers.setEmail(etemail.getText().toString());
        allUserMembers.setWebsite(etwebsite.getText().toString());
        databaseReference.push().setValue(allUserMembers);
        Toast.makeText(Userprofile.this,"Saved to database",Toast.LENGTH_SHORT).show();


    }

}