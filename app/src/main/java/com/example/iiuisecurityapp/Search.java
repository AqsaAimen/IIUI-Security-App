package com.example.iiuisecurityapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Search extends AppCompatActivity {
    DatabaseReference firebaseReference;
    FirebaseDatabase firebaseDatabase;
    LinearLayoutManager manager;
    ArrayList<VehiclesData> data;
    RecyclerView recyclerView;
    AdapterClass myadapter;
    ImageButton search20;
    SearchView searchView;
    ArrayList<VehiclesData> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        search20 = (ImageButton) findViewById(R.id.searchbtn);
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseReference = firebaseDatabase.getInstance("https://transportfirebase-3578e-default-rtdb.firebaseio.com/").getReference().child("Entered Vehicles");
        recyclerView = findViewById(R.id.recyclerview4);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        searchView = findViewById(R.id.searchfield);
        list = new ArrayList<>();
        myadapter = new AdapterClass(this, list);
        recyclerView.setAdapter(myadapter);

        search20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                firebaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            VehiclesData vehiclesData = dataSnapshot.getValue(VehiclesData.class);
                            list.add(vehiclesData);
                        }
                        myadapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }




}
