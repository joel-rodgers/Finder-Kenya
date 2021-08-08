package com.example.finderkenya;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupTabFragment extends Fragment {
    EditText email, fullnames, mobile, pass;
    Button signup;
    float v = 0;

    DatabaseReference finderKenya;
    FirebaseDatabase rootNode;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.signup_tab_fragment, container, false);

        email = root.findViewById(R.id.email);
        fullnames = root.findViewById(R.id.fullnames);
        mobile = root.findViewById(R.id.mobile);
        pass = root.findViewById(R.id.pass);
        signup = root.findViewById(R.id.signup);

        email.setTranslationX(800);
        fullnames.setTranslationX(800);
        mobile.setTranslationX(800);
        pass.setTranslationX(800);
        signup.setTranslationX(800);

        email.setAlpha(v);
        fullnames.setAlpha(v);
        mobile.setAlpha(v);
        pass.setAlpha(v);
        signup.setAlpha(v);

        email.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        fullnames.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        mobile.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();
        pass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(900).start();
        signup.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(1100).start();



        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                finderKenya= rootNode.getReference("users");

                finderKenya.setValue("First data storage");

            }
        });
        return root;
    }



}





