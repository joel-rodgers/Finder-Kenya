package com.example.finderkenya;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Objects;

public class SignupTabFragment extends Fragment {
    private EditText email, fullnames, mobile, pass,username;
    private Button signup;
    float v = 0;
    private ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;



    private DatabaseReference reference;
    FirebaseDatabase rootNode;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.signup_tab_fragment, container, false);



        email = root.findViewById(R.id.email);
        username = root.findViewById(R.id.username);
        fullnames = root.findViewById(R.id.fullnames);
        mobile = root.findViewById(R.id.mobile);
        pass = root.findViewById(R.id.pass);
        signup = root.findViewById(R.id.signup);
        progressBar = root.findViewById(R.id.progress_bar);
        firebaseAuth = FirebaseAuth.getInstance();

        email.setTranslationX(800);
        username.setTranslationX(800);
        fullnames.setTranslationX(800);
        mobile.setTranslationX(800);
        pass.setTranslationX(800);
        signup.setTranslationX(800);

        email.setAlpha(v);
        username.setAlpha(v);
        fullnames.setAlpha(v);
        mobile.setAlpha(v);
        pass.setAlpha(v);
        signup.setAlpha(v);

        fullnames.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        username.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        email.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();
        mobile.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(900).start();
        pass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(1100).start();
        signup.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(1300).start();




        //Save data in Firebase on button click
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),LoginActivity.class));


                //rootNode = FirebaseDatabase.getInstance();
                //reference= rootNode.getReference("users");

                //Get all values

                final String fname = fullnames.getEditableText().toString();
                final String uname = username.getEditableText().toString();
                final String mail = email.getEditableText().toString();
                final String mobileNo = mobile.getEditableText().toString();
                final String pwd = pass.getEditableText().toString();



                //validate
                if(fname.isEmpty()){
                    fullnames.setError("Full name is required!");
                    fullnames.requestFocus();
                    return;
                }
                if(uname.isEmpty()){
                    username.setError("Username is required!");
                    username.requestFocus();
                    return;
                }

                if(mail.isEmpty()){
                    email.setError("Email is required!");
                    email.requestFocus();
                    return;
                }

                if(!Patterns.EMAIL_ADDRESS.matcher(mail).matches()){
                    email.setError("Please provide valid email!");
                    email.requestFocus();
                    return;
                }

                if(mobileNo.isEmpty()){
                    mobile.setError("Mobile number is required!");
                    mobile.requestFocus();
                    return;
                }

                if(pwd.isEmpty()){
                    pass.setError("Password is required!");
                    pass.requestFocus();
                    return;
                }

                if(pwd.length() < 6){
                    pass.setError("Min password length should be 6 characters!");
                    pass.requestFocus();
                    return;
                }

               // Intent intent = new Intent(getContext(),VerifyPhoneNo.class);
                //intent.putExtra("mobileNo",mobileNo );
               // startActivity(intent);



               // UserHelperClass helperClass = new UserHelperClass(fname,uname,mail,mobileNo,pwd);


               // reference.child(uname).setValue(helperClass);

                /*Toast.makeText(signup.getContext(), "Your Account has been created successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(),LoginTabFragment.class);
                startActivity(intent);*/

                signup(fname,uname,mail,mobileNo,pwd);


            }
            private void signup(String fname,String uname,String mail,String mobileNo, String pwd){
                progressBar.setVisibility(View.VISIBLE);
                firebaseAuth.createUserWithEmailAndPassword(mail,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser rUser = firebaseAuth.getCurrentUser();
                            assert rUser != null;
                            String userId = rUser.getUid();
                            reference=FirebaseDatabase.getInstance().getReference("users").child(userId);
                            HashMap<String,String> hashMap = new HashMap<>();
                            hashMap.put("userId",userId);
                            hashMap.put("fname",fname);
                            hashMap.put("uname",uname);
                            hashMap.put("mail",mail);
                            hashMap.put("mobileNo",mobileNo);
                            hashMap.put("pwd",pwd);
                            hashMap.put("imageUrl","default");
                            reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    progressBar.setVisibility(View.GONE);
                                    if(task.isSuccessful()){
                                        Intent intent = new Intent(getContext(),LoginActivity.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                    }else{
                                        Toast.makeText(getContext(), Objects.requireNonNull(task.getException().getMessage()), Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });

                        }else{
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getContext(),Objects.requireNonNull(task.getException()) .getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }

        });//Register button method end


        return root;
    }//onCreate Method End



}





