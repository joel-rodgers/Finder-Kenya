package com.example.finderkenya;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MissingPersons extends AppCompatActivity {
     private ImageButton imageBtn;
     private EditText victimname,ob,lastseen,dob,description,victimhome,vc1,vc2,cstatus;
     private Button postBtn;

    private int mYear;
    private int mMonth;
    private int mDay;


    private StorageReference mStorageRef;
     private DatabaseReference databaseRef;
     private FirebaseAuth mAuth;
     private DatabaseReference mDatabaseUsers;
     private FirebaseUser mCurrentUser;

     private static final int GALLERY_REQUEST_CODE = 2;
     private Uri uri =null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_missing_persons);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        postBtn= findViewById(R.id.postMP);
        victimname= findViewById(R.id.victimname);
        ob= findViewById(R.id.ob);
        lastseen= findViewById(R.id.lastseen);
        description= findViewById(R.id.description);
        victimhome= findViewById(R.id.victimhome);
        vc1= findViewById(R.id.vc1);
        vc2= findViewById(R.id.vc2);
        cstatus= findViewById(R.id.cstatus);

        mStorageRef = FirebaseStorage.getInstance().getReference();
        databaseRef = FirebaseDatabase.getInstance().getReference().child("missing_persons");
        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();
        mDatabaseUsers = FirebaseDatabase.getInstance().getReference().child("users").child(mCurrentUser.getUid());


        dob= findViewById(R.id.dob);
        dob.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(v==dob){
                            final Calendar c = Calendar.getInstance();
                            mYear = c.get(Calendar.YEAR);
                            mMonth = c.get(Calendar.MONTH);
                            mDay = c.get(Calendar.DAY_OF_MONTH);

                            DatePickerDialog datePickerDialog = new DatePickerDialog(MissingPersons.this, new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                    dob.setText(dayOfMonth + "-"+(month+1+"-"+year));
                                }
                            },mYear,mMonth,mDay);

                            datePickerDialog.show();
                        }

                    }
        });



        imageBtn = findViewById(R.id.imgBtn);
        imageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent,GALLERY_REQUEST_CODE);
            }
        });


        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MissingPersons.this,"POSTING...", Toast.LENGTH_LONG).show();
                final String PostVictimName = victimname.getText().toString().trim();
                final String PostOb = ob.getText().toString().trim();
                final String PostLastSeen = lastseen.getText().toString().trim();
                final String PostDob = dob.getText().toString().trim();
                final String PostDescription = description.getText().toString().trim();
                final String PostVictimHome = victimhome.getText().toString().trim();
                final String PostVictimContact1 = vc1.getText().toString().trim();
                final String PostVictimContact2 = vc2.getText().toString().trim();
                final String PostCaseStatus = cstatus.getText().toString().trim();

                java.util.Calendar calendar = Calendar.getInstance();
                SimpleDateFormat currentDate = new SimpleDateFormat("dd-MM-yyyy");
                final String saveCurrentDate = currentDate.format(calendar.getTime());

                java.util.Calendar calendar1 = calendar.getInstance();
                SimpleDateFormat currentTime= new SimpleDateFormat("HH:mm");
                final String saveCurrentTime = currentTime.format(calendar1.getTime());

                if(!TextUtils.isEmpty(PostVictimName) || !TextUtils.isEmpty(PostOb) || !TextUtils.isEmpty(PostLastSeen) || !TextUtils.isEmpty(PostDob)
                    || !TextUtils.isEmpty(PostDescription) || !TextUtils.isEmpty(PostVictimHome) || !TextUtils.isEmpty(PostVictimContact1)
                    || !TextUtils.isEmpty(PostVictimContact2) || !TextUtils.isEmpty(PostCaseStatus)){


                    StorageReference filepath = mStorageRef.child("post_victim_images").child(uri.getLastPathSegment());
                    filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                            if(taskSnapshot.getMetadata() != null){
                                if(taskSnapshot.getMetadata().getReference() !=null){
                                    Task<Uri> result = taskSnapshot.getStorage().getDownloadUrl();
                                    result.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(@NonNull Uri uri) {
                                            final String imageUrl = uri.toString();
                                            Toast.makeText(getApplicationContext(),"Succesfully Uploaded...", Toast.LENGTH_SHORT).show();
                                            final DatabaseReference newPost = databaseRef.push();

                                            mDatabaseUsers.addValueEventListener(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                    newPost.child("victimName").setValue(victimname.getText().toString());
                                                    newPost.child("obNumber").setValue(ob.getText().toString());
                                                    newPost.child("victimLastSeen").setValue(lastseen.getText().toString());
                                                    newPost.child("victimDob").setValue(dob.getText().toString());
                                                    newPost.child("victimDescription").setValue(description.getText().toString());
                                                    newPost.child("victimHome").setValue(victimhome.getText().toString());
                                                    newPost.child("victimContact1").setValue(vc1.getText().toString());
                                                    newPost.child("victimContact2").setValue(vc2.getText().toString());
                                                    newPost.child("caseStatus").setValue(cstatus.getText().toString());
                                                    newPost.child("postImage").setValue(imageUrl);
                                                    newPost.child("uid").setValue(mCurrentUser.getUid());
                                                    newPost.child("time").setValue(saveCurrentTime);
                                                    newPost.child("fname").setValue(snapshot.child("fname").getValue());
                                                    newPost.child("date").setValue(saveCurrentDate).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            if(task.isSuccessful()){
                                                                Intent intent = new Intent(MissingPersons.this, MPPoster.class);
                                                                intent.putExtra("victimName", victimname.getText().toString());
                                                                intent.putExtra("obNumber", ob.getText().toString());
                                                                intent.putExtra("victimLastSeen", lastseen.getText().toString());
                                                                intent.putExtra("victimDob",dob.getText().toString());
                                                                intent.putExtra("victimDescription",description.getText().toString());
                                                                intent.putExtra("victimHome",victimhome.getText().toString());
                                                                intent.putExtra("victimContact1",vc1.getText().toString());
                                                                intent.putExtra("victimContact2",vc2.getText().toString());
                                                                intent.putExtra("caseStatus",cstatus.getText().toString());
                                                                intent.putExtra("postImage",imageUrl.toString());

                                                                startActivity(intent);
                                                            }
                                                        }
                                                    });


                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError error) {
                                                    Toast.makeText(MissingPersons.this,error.getMessage(), Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                        }
                                    });
                                }
                            }

                        }
                    });
                }

            }
        });









    }


    @Override
    protected void onActivityResult(int requestCode, int resultcode, Intent data){
        super.onActivityResult(requestCode, resultcode, data);

        if(requestCode == GALLERY_REQUEST_CODE && resultcode == RESULT_OK){
            uri = data.getData();
            imageBtn.setImageURI(uri);
        }
    }
}