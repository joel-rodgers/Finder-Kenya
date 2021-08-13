package com.example.finderkenya;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupTabFragment extends Fragment {
    EditText email, fullnames, mobile, pass,username;
    Button signup;
    float v = 0;



    DatabaseReference reference;
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

                rootNode = FirebaseDatabase.getInstance();
                reference= rootNode.getReference("users");

                //Get all values

                String fname = fullnames.getEditableText().toString();
                String uname = username.getEditableText().toString();
                String mail = email.getEditableText().toString();
                String mobileNo = mobile.getEditableText().toString();
                String pwd = pass.getEditableText().toString();



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

                Intent intent = new Intent(getContext(),VerifyPhoneNo.class);
                intent.putExtra("mobileNo",mobileNo );
                startActivity(intent);



               // UserHelperClass helperClass = new UserHelperClass(fname,uname,mail,mobileNo,pwd);


               // reference.child(uname).setValue(helperClass);

                /*Toast.makeText(signup.getContext(), "Your Account has been created successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(),LoginTabFragment.class);
                startActivity(intent);*/






            }

        });//Register button method end

        return root;
    }//onCreate Method End



}





