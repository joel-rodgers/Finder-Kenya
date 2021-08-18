package com.example.finderkenya;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.HashMap;
import java.util.Map;

public class UpdateProfile extends AppCompatActivity {
    EditText fullnames_profile,username_profile,email_profile,mobile_profile,pass_profile;
    Button edit;
    public static final String TAG ="TAG";


    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    DatabaseReference reference;
    DocumentReference documentReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);


        Intent data = getIntent();
        String fullname= data.getStringExtra("fname");
        String username= data.getStringExtra("uname");
        String email= data.getStringExtra("mail");
        String mobile= data.getStringExtra("mobileNo");
        String password= data.getStringExtra("pwd");

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();


        fullnames_profile=findViewById(R.id.fullnames_profile_up);
        username_profile=findViewById(R.id.username_profile_up);
        email_profile=findViewById(R.id.email_profile_up);
        mobile_profile=findViewById(R.id.mobile_profile_up);
        pass_profile=findViewById(R.id.pass_profile_up);
        edit=findViewById(R.id.btn_up);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fullnames_profile.getText().toString().isEmpty() || username_profile.getText().toString().isEmpty() || email_profile.getText().toString().isEmpty() || mobile_profile.getText().toString().isEmpty() || pass_profile.getText().toString().isEmpty() ) {
                    Toast.makeText(UpdateProfile.this, "One or Many fields are empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                String email = email_profile.getText().toString();
                firebaseUser.updateEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(@NonNull Void unused) {
                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users").child(firebaseUser.getUid());
                        Map<String,Object> edited = new HashMap<>();
                        edited.put("mail",email);
                        edited.put("fname",fullnames_profile.getText().toString());
                        edited.put("uname",username_profile.getText().toString());
                        edited.put("mobileNo",mobile_profile.getText().toString());
                        edited.put("pwd",pass_profile.getText().toString());
                        reference.updateChildren(edited).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(@NonNull Void unused) {
                                Toast.makeText(UpdateProfile.this, "Profile updated", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),UserProfile.class));
                                finish();
                            }
                        });

                        Toast.makeText(UpdateProfile.this, "Email is changed", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(UpdateProfile.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });



        fullnames_profile.setText(fullname);
        username_profile.setText(username);
        email_profile.setText(email);
        mobile_profile.setText(mobile);
        pass_profile.setText(password);

        Log.d(TAG, "onCreate:" + fullname + " " + username + " " + email + " " + mobile + " " + password + " ");



    }



}