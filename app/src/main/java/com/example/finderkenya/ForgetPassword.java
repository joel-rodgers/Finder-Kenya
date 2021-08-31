package com.example.finderkenya;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;

public class ForgetPassword extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private TextView resetState;
    private EditText email;
    private Button reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

         mAuth = FirebaseAuth.getInstance();
         email = findViewById(R.id.email);
         resetState = findViewById(R.id.resetText);
         reset = findViewById(R.id.resetPassword);
         progressBar = findViewById(R.id.progress_bar);

         reset.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 progressBar.setVisibility(View.VISIBLE);
                 mAuth.fetchSignInMethodsForEmail(email.getText().toString()).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                     @Override
                     public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                         if(task.getResult().getSignInMethods().isEmpty()){
                             progressBar.setVisibility(View.GONE);
                             resetState.setText("This is not a registered email, you can create new account");
                         }else{
                             mAuth.sendPasswordResetEmail(email.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                 @Override
                                 public void onComplete(@NonNull Task<Void> task) {
                                     progressBar.setVisibility(View.GONE);
                                     if(task.isSuccessful()){
                                         resetState.setText("A password reset email has been sent to your email address");
                                     }else{
                                         resetState.setText(task.getException().getMessage());
                                     }

                                 }
                             });
                         }

                     }
                 });


             }
         });


    }
}