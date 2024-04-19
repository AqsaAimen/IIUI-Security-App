package com.example.iiuisecurityapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

import static com.example.iiuisecurityapp.App.Channel_1_ID;
import static com.example.iiuisecurityapp.App.Channel_2_ID;
import static com.example.iiuisecurityapp.App.Channel_3_ID;

public class Notification extends AppCompatActivity {
    NotificationManagerCompat notificationManager;

    TextInputEditText editTextTitle;
    TextInputEditText editTextMessage;
    Button chanel11btn;
    Button chanel12btn;
    Button chanel13btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        notificationManager=NotificationManagerCompat.from(this);
        editTextTitle=(TextInputEditText)findViewById(R.id.edit_text11);
        editTextMessage=(TextInputEditText)findViewById(R.id.edit_text22);
        chanel11btn=(Button)findViewById(R.id.chanel11btn);
        chanel12btn=(Button)findViewById(R.id.chanel12btn);
        chanel13btn=(Button)findViewById(R.id.chanel13btn);
        chanel11btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendOnChannel11();
            }
        });

    }
    public void sendOnChannel11() {
        String title=editTextTitle.getText().toString();
        String message= editTextMessage.getText().toString();
        android.app.Notification notification=new NotificationCompat.Builder(this,Channel_1_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManager.notify(1,notification);

    }
    public void sendOnChannel12(View v) {
        String title=editTextTitle.getText().toString();
        String message= editTextMessage.getText().toString();
        android.app.Notification notification=new NotificationCompat.Builder(this,Channel_2_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManager.notify(2,notification);

    }
    public void sendOnChannel13(View v) {
        String title=editTextTitle.getText().toString();
        String message= editTextMessage.getText().toString();
        android.app.Notification notification=new NotificationCompat.Builder(this,Channel_3_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManager.notify(3,notification);

    }
}