package com.example.finderkenya;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Sightings extends AppCompatActivity {

    private EditText victimname,ob,dateseen,victimCondition,locationSighting;
    private ImageButton imgBtn;
    private Button submit;

    private int mYear;
    private int mMonth;
    private int mDay;

    private DatabaseReference databaseRef;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseUsers;
    private FirebaseUser mCurrentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sightings);

        Intent data = getIntent();
        String vname= data.getStringExtra("victimName");
        String obNumber= data.getStringExtra("obNumber");
        String image= data.getStringExtra("postImage");


        //Hooks
        victimname=findViewById(R.id.victimname);
        ob=findViewById(R.id.ob);
        dateseen=findViewById(R.id.dateSeen);
        victimCondition=findViewById(R.id.victimCondition);
        locationSighting=findViewById(R.id.location);
        imgBtn=findViewById(R.id.imgBtn);
        submit=findViewById(R.id.submitSighting);

        databaseRef = FirebaseDatabase.getInstance().getReference().child("sighting_table");
        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();
        mDatabaseUsers = FirebaseDatabase.getInstance().getReference().child("users").child(mCurrentUser.getUid());


        victimname.setText(vname);
        ob.setText(obNumber);
        Glide.with(getApplicationContext()).load(image).into(imgBtn);


        dateseen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v==dateseen){
                    final Calendar c = Calendar.getInstance();
                    mYear = c.get(Calendar.YEAR);
                    mMonth = c.get(Calendar.MONTH);
                    mDay = c.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog datePickerDialog = new DatePickerDialog(Sightings.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            dateseen.setText(dayOfMonth + "-"+(month+1+"-"+year));
                        }
                    },mYear,mMonth,mDay);

                    datePickerDialog.show();
                }

            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Sightings.this,"SUBMITTING...", Toast.LENGTH_LONG).show();
                final String SubmitDateSeen = dateseen.getText().toString().trim();
                final String SubmitVictimCondition = victimCondition.getText().toString().trim();
                final String SubmitLocationSighting = victimname.getText().toString().trim();



                java.util.Calendar calendar = Calendar.getInstance();
                SimpleDateFormat currentDate = new SimpleDateFormat("dd-MM-yyyy");
                final String saveCurrentDate = currentDate.format(calendar.getTime());

                java.util.Calendar calendar1 = calendar.getInstance();
                SimpleDateFormat currentTime= new SimpleDateFormat("HH:mm");
                final String saveCurrentTime = currentTime.format(calendar1.getTime());

                if(!TextUtils.isEmpty(SubmitDateSeen) || !TextUtils.isEmpty(SubmitVictimCondition) || !TextUtils.isEmpty(SubmitLocationSighting)){


                    final DatabaseReference newSighting = databaseRef.push();
                    String mSightingId = databaseRef.push().getKey();
                    //String mCaseId = databaseRef.getKey();


                    mDatabaseUsers.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            newSighting.child("dateSeen").setValue(dateseen.getText().toString());
                            newSighting.child("victimCondition").setValue(victimCondition.getText().toString());
                            newSighting.child("victimName").setValue(victimname.getText().toString());
                            newSighting.child("locationSighting").setValue(locationSighting.getText().toString());
                            newSighting.child("uid").setValue(mCurrentUser.getUid());
                            newSighting.child("sightingId").setValue(mSightingId);
                            newSighting.child("time").setValue(saveCurrentTime);
                            newSighting.child("caseId").setValue(snapshot.child("caseId").getValue());
                            newSighting.child("fname").setValue(snapshot.child("fname").getValue());
                            newSighting.child("mobileNo").setValue(snapshot.child("mobileNo").getValue());
                            newSighting.child("postImage").setValue(snapshot.child("postImage").getValue());
                            newSighting.child("date").setValue(saveCurrentDate).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Intent intent = new Intent(Sightings.this, MainActivity.class);

                                        startActivity(intent);
                                    }

                                }
                            });
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(Sightings.this,error.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });

                }






            }
        });

    }
}