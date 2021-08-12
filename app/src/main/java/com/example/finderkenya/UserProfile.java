package com.example.finderkenya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserProfile extends AppCompatActivity {
    TextView fullnames_field,username_field;
    EditText fullnames_profile,username_profile,email_profile,mobile_profile,pass_profile;
    Button update;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        reference = FirebaseDatabase.getInstance().getReference("users");

        //Hooks
        fullnames_field=findViewById(R.id.fullnames_field);
        username_field=findViewById(R.id.username_field);
        fullnames_profile=findViewById(R.id.fullnames_profile);
        username_profile=findViewById(R.id.username_profile);
        email_profile=findViewById(R.id.email_profile);
        mobile_profile=findViewById(R.id.mobile_profile);
        pass_profile=findViewById(R.id.pass_profile);
        update=findViewById(R.id.update);

        //Show all user data
        showAllUserData();

    }

    private void showAllUserData() {

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

    }
   public void update(View view){

        //if(isNameChanged() || isPasswordChanged()){
            //Toast.makeText(this, "Data has been updated", Toast.LENGTH_LONG).show();
            
        //}

   }

    //private boolean isPasswordChanged() { }

    //private boolean isNameChanged() {

        //if()
    //}
}