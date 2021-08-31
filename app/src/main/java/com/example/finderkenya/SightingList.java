package com.example.finderkenya;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SightingList extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference database;
    SightingsAdapter sightingsAdapter;
    ArrayList<SightingHelperClass> list;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sighting_list);

        recyclerView= findViewById(R.id.sightingList);
        database = FirebaseDatabase.getInstance().getReference("sighting_table");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        sightingsAdapter = new SightingsAdapter(this,list);
        recyclerView.setAdapter(sightingsAdapter);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SightingList.this, MainActivity.class );
                startActivity(intent);
                finish();
            }
        });

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot : snapshot.getChildren()){

                    SightingHelperClass sightingHelperClass = dataSnapshot.getValue(SightingHelperClass.class);
                    list.add(sightingHelperClass);

                }
                sightingsAdapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}