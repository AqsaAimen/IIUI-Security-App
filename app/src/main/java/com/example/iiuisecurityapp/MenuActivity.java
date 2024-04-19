package com.example.iiuisecurityapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

public class MenuActivity<myaccount> extends AppCompatActivity {
private DrawerLayout mdrawerlayout;
private ActionBarDrawerToggle mtoggle;
private Toolbar mtoolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        mdrawerlayout=(DrawerLayout)  findViewById(R.id.drawerlayout);
        mtoggle=new ActionBarDrawerToggle(this,mdrawerlayout,R.string.open,R.string.close);
        mdrawerlayout.addDrawerListener(mtoggle);
        NavigationView nav=(NavigationView)findViewById(R.id.nav_view);
        mtoggle.syncState();
      nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
          @Override
          public boolean onNavigationItemSelected(@NonNull MenuItem menuitem) {
              switch(menuitem.getItemId())
              {
                  case R.id.my_account :
                      Intent intent=new Intent(MenuActivity.this,createprofile.class);
                      startActivity(intent);
                      //mdrawerlayout.closeDrawer(GravityCompat.START);
                      break;
                  case R.id.settings :
                      Intent intent2=new Intent(MenuActivity.this,SettingsActivity.class);
                      startActivity(intent2);
                      //mdrawerlayout.closeDrawer(GravityCompat.START);
                      break;
                  case R.id.report_tip :
                      Intent intent3=new Intent(MenuActivity.this,Report_A_tip.class);
                      startActivity(intent3);
                      //mdrawerlayout.closeDrawer(GravityCompat.START);
                      break;
                  case R.id.information :
                      Intent intent4=new Intent(MenuActivity.this,ActivityInformation.class);
                      startActivity(intent4);
                      //mdrawerlayout.closeDrawer(GravityCompat.START);
                      break;
                  case R.id.notification :
                      Intent intent5=new Intent(MenuActivity.this,Notification.class);
                      startActivity(intent5);
                      //mdrawerlayout.closeDrawer(GravityCompat.START);
                      break;
                  case R.id.regions :
                      Intent intent6=new Intent(MenuActivity.this,createprofile.class);
                      startActivity(intent6);
                      //mdrawerlayout.closeDrawer(GravityCompat.START);
                      break;
                  case R.id.diagonstics :
                      Intent intent7=new Intent(MenuActivity.this,createprofile.class);
                      startActivity(intent7);
                      //mdrawerlayout.closeDrawer(GravityCompat.START);
                      break;
                  case R.id.version :
                      Intent intent8=new Intent(MenuActivity.this,createprofile.class);
                      startActivity(intent8);
                      //mdrawerlayout.closeDrawer(GravityCompat.START);
                      break;
                  case R.id.feedback :
                      Intent intent9=new Intent(MenuActivity.this,createprofile.class);
                      startActivity(intent9);
                      //mdrawerlayout.closeDrawer(GravityCompat.START);
                      break;
                  case R.id.privacy :
                      Intent intent0=new Intent(MenuActivity.this,createprofile.class);
                      startActivity(intent0);
                      //mdrawerlayout.closeDrawer(GravityCompat.START);
                      break;
              }
              return true;
          }
      });
    }


}