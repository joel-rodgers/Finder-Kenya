package com.example.finderkenya;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginTabFragment extends Fragment {
    EditText email,pass;
    TextView forgetpass;
    Button login;
    float v=0;

    DatabaseReference reference;
    FirebaseDatabase rootNode;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_tab_fragment, container, false );

        email = root.findViewById(R.id.email);
        pass = root.findViewById(R.id.pass);
        forgetpass = root.findViewById(R.id.forgetPass);
        login = root.findViewById(R.id.login);

        email.setTranslationX(800);
        pass.setTranslationX(800);
        forgetpass.setTranslationX(800);
        login.setTranslationX(800);

        email.setAlpha(v);
        pass.setAlpha(v);
        forgetpass.setAlpha(v);
        login.setAlpha(v);

        email.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        pass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        forgetpass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        login.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();



        //Save data in Firebase on button click
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                rootNode = FirebaseDatabase.getInstance();
                reference= rootNode.getReference("users");

                //Get all values
                String userEnteredEmail = email.getEditableText().toString();
                String userEnteredPassword = pass.getEditableText().toString();



                //validate
                if(userEnteredEmail.isEmpty()){
                    email.setError("Email is required!");
                    email.requestFocus();
                    return;
                }

                if(!Patterns.EMAIL_ADDRESS.matcher(userEnteredEmail).matches()){
                    email.setError("Please provide valid email!");
                    email.requestFocus();
                    return;
                }

                if(userEnteredPassword.isEmpty()){
                    pass.setError("Password is required!");
                    pass.requestFocus();
                    return;
                }

                if(userEnteredPassword.length() < 6){
                    pass.setError("Min password length should be 6 characters!");
                    pass.requestFocus();
                    return;
                }





                Query checkUser = reference.orderByChild("mail").equalTo(userEnteredEmail);

                checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if(snapshot.exists()){

                            email.setError(null);
                            email.setEnabled(false);

                            String passwordFromDB = snapshot.child(userEnteredEmail).child("pwd").getValue(String.class);

                            if(passwordFromDB.equals(userEnteredPassword)){

                                email.setError(null);
                                email.setEnabled(false);


                                String nameFromDB = snapshot.child(userEnteredEmail).child("fname").getValue(String.class);
                                String emailFromDB = snapshot.child(userEnteredEmail).child("mail").getValue(String.class);
                                String mobileNoFromDB = snapshot.child(userEnteredEmail).child("mobileNo").getValue(String.class);

                                Intent intent = new Intent(getContext(),UserProfile.class);

                                intent.putExtra("fname",nameFromDB);
                                intent.putExtra("mail",emailFromDB);
                                intent.putExtra("mobileNo",mobileNoFromDB);
                                intent.putExtra("pwd",passwordFromDB);

                                startActivity(intent);

                            }
                            else{
                                pass.setError("Wrong Password!");
                                pass.requestFocus();
                            }
                        }
                        else{
                            email.setError("No such user exists!");
                            email.requestFocus();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });





            }

        });//Login button method end

        return root;
    }//onCreate Method End




}
