package com.example.iiuisecurityapp;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import de.hdodenhof.circleimageview.CircleImageView;

public class createprofile extends AppCompatActivity {
    ImageButton imageButton;
    private CircleImageView profileimg;
    //private DatabaseReference databaseReference;
    //private FirebaseAuth mauth;
    //private StorageTask uploadtask;
    //private StorageReference storageprofilepicreference;
    private String myUri;
    Uri imageuri;
    private static final int PICK_IMAGE = 1;
    Button userprofilebtn;
    Button useraddressbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createprofile);
        userprofilebtn=(Button)findViewById(R.id.userprofiledetails);
        useraddressbtn=(Button)findViewById(R.id.useraddressdetail);
        final Intent INTENT = getIntent();
        //mauth=FirebaseAuth.getInstance();
        //databaseReference= FirebaseDatabase.getInstance().getReference().child("users");
        //storageprofilepicreference= FirebaseStorage.getInstance().getReference().child("profile pic");
        ImageButton imageButton = (ImageButton) findViewById(R.id.arrow2);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(createprofile.this, iiuisecurityfeatures.class);
                startActivity(intent);
                finish();
            }
        });
        profileimg = (CircleImageView) findViewById(R.id.profileimg);
        profileimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                gallery.setType("Image/*");
                //gallery.setAction(Intent.ACTION_GET_CONTENT,MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(Intent.createChooser(gallery,"select picture"),PICK_IMAGE);
                startActivityForResult(gallery, PICK_IMAGE);
                //uploadprofilepic();
            }
        });
        userprofilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intenthh=new Intent(createprofile.this,Userprofile.class);
                startActivity(intenthh);
            }
        });
        useraddressbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentuu=new Intent(createprofile.this,UserAddress.class);
                startActivity(intentuu);
            }
        });
    }

    protected void onActivityResult(int requestcode, int resultcode, Intent data) {
        super.onActivityResult(requestcode, resultcode, data);
        if (requestcode == PICK_IMAGE && resultcode == RESULT_OK) {
            imageuri = data.getData();
            /*try{
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),imageuri);
                profileimg.setImageBitmap(bitmap);
            }catch (IOException e){
                e.printStackTrace();*/
            profileimg.setImageURI(imageuri);
        }
    }
    /*private void uploadprofilepic(){
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("set your profile picture");
        progressDialog.setMessage("please wait we are setting your data");
        progressDialog.show();
        if(imageuri !=null){
            final StorageReference fileRef=storageprofilepicreference.child(mauth.getCurrentUser().getUid() +".jpg");
            uploadtask=fileRef.putFile(imageuri);
            uploadtask.continueWithTask(new Continuation() {
                @Override
                public Object then(@NonNull Task task) throws Exception {
                    if(!task.isSuccessful()){
                        throw task.getException();
                    }
                    return fileRef.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if(task.isSuccessful()){
                        Uri downloaduri=task.getResult();
                        myUri=downloaduri.toString();
                        HashMap<String,Object> usermap= new HashMap<>();
                        usermap.put("image ",myUri);
                        databaseReference.child(mauth.getCurrentUser().getUid()).updateChildren(usermap);
                        progressDialog.dismiss();
                    }
                }
            });
        }
    }*/
    }
