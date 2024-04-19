package com.example.iiuisecurityapp;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class App extends Application {
    public  static final String Channel_1_ID="IIUI ADMIN";
    public  static final String Channel_2_ID="IIUI Teachers";
    public  static final String Channel_3_ID="IIUI Student";

    @Override
    public void onCreate() {
        super.onCreate();

        createNotificationChannels();
    }

    private void createNotificationChannels() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel Admin=new NotificationChannel(Channel_1_ID,"IIUI ADMIN", NotificationManager.IMPORTANCE_HIGH);
            Admin.setDescription("Notification for IIUI Admin");
            NotificationChannel Teacher=new NotificationChannel(Channel_2_ID,"IIUI Teachers", NotificationManager.IMPORTANCE_LOW);
            Teacher.setDescription("Notification for IIUI Teacher");
            NotificationChannel Students=new NotificationChannel(Channel_3_ID,"IIUI Students", NotificationManager.IMPORTANCE_LOW);
            Students.setDescription("Notification for IIUI Teacher");
            NotificationManager manager=getSystemService(NotificationManager.class);
            manager.createNotificationChannel(Admin);
            manager.createNotificationChannel(Teacher);
            manager.createNotificationChannel(Students);
        }
    }
}
