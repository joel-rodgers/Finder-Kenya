package com.example.finderkenya;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserProfile extends AppCompatActivity {
    private TextView fullnames_field,username_field;
    private EditText fullnames_profile,username_profile,email_profile,mobile_profile,pass_profile;
    private Button update;
    private CircleImageView circleImageView;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;


    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        //reference = FirebaseDatabase.getInstance().getReference("users");

        //Hooks
        circleImageView=findViewById(R.id.profile_image);
        fullnames_field=findViewById(R.id.fullnames_field);
        username_field=findViewById(R.id.username_field);
        fullnames_profile=findViewById(R.id.fullnames_profile);
        username_profile=findViewById(R.id.username_profile);
        email_profile=findViewById(R.id.email_profile);
        mobile_profile=findViewById(R.id.mobile_profile);
        pass_profile=findViewById(R.id.pass_profile);
        update=findViewById(R.id.update);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();



        reference = FirebaseDatabase.getInstance().getReference("users").child(firebaseUser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserHelperClass userHelperClass = snapshot.getValue(UserHelperClass.class);
                assert  userHelperClass !=null;
                fullnames_field.setText(userHelperClass.getFname());
                username_field.setText(userHelperClass.getUname());
                fullnames_profile.setText(userHelperClass.getFname());
                username_profile.setText(userHelperClass.getUname());
                email_profile.setText(userHelperClass.getMail());
                mobile_profile.setText(userHelperClass.getMobileNo());
                pass_profile.setText(userHelperClass.getPwd());


                if(userHelperClass.getImageURL().equals("default")){
                    circleImageView.setImageResource(R.drawable.profile);
                }else{
                    Glide.with(getApplicationContext()).load(userHelperClass.getImageURL()).error(R.drawable.ic_launcher_background).into(circleImageView);
                }

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(UserProfile.this,error.getMessage() , Toast.LENGTH_SHORT).show();

            }


        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfile.this,UpdateProfile.class);

                intent.putExtra("fname", fullnames_profile.getText().toString());
                intent.putExtra("uname", username_profile.getText().toString());
                intent.putExtra("mail", email_profile.getText().toString());
                intent.putExtra("mobileNo",mobile_profile.getText().toString());
                intent.putExtra("pwd",pass_profile.getText().toString());
                startActivity(intent);
            }
        });

        //Show all user data
        //showAllUserData();

    }

   /* private void showAllUserData() {

        Intent intent = getIntent();
        String user_username = intent.getStringExtra("uname");
        String user_name = intent.getStringExtra("fname");
        String user_email = intent.getStringExtra("mail");
        String user_mobileNo = intent.getStringExtra("mobileNo");
        String user_password = intent.getStringExtra("pwd");


        fullnames_field.setText(user_name);
        username_field.setText(user_username);
        fullnames_profile.setText(user_name);
        username_profile.setText(user_username);
        email_profile.setText(user_email);
        mobile_profile.setText(user_mobileNo);
        pass_profile.setText(user_password);

    }*/

}