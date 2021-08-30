package com.example.finderkenya;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class EditMP extends AppCompatActivity {
    private EditText victimname,ob,lastseen,dob,description,victimhome,vc1,vc2,cstatus;
    private Button update;
    private  DatabaseReference reference;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_mp);

        Intent data = getIntent();
        String vname= data.getStringExtra("victimName");
        String obNumber= data.getStringExtra("obNumber");
        String victimLastSeen= data.getStringExtra("victimLastSeen");
        String victimDob= data.getStringExtra("victimDob");
        String victimDescription= data.getStringExtra("victimDescription");
        String victimHome= data.getStringExtra("victimHome");
        String victimContact1= data.getStringExtra("victimContact1");
        String victimContact2= data.getStringExtra("victimContact2");
        String caseStatus= data.getStringExtra("caseStatus");


        //Hooks
        victimname=findViewById(R.id.victimname);
        ob=findViewById(R.id.ob);
        lastseen=findViewById(R.id.lastseen);
        dob=findViewById(R.id.dob);
        description=findViewById(R.id.description);
        victimhome=findViewById(R.id.victimhome);
        vc1=findViewById(R.id.vc1);
        vc2=findViewById(R.id.vc2);
        cstatus=findViewById(R.id.cstatus);
        update=findViewById(R.id.updateMP);





        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();


       // update.setOnClickListener(new View.OnClickListener() {
            //@Override
            //public void onClick(View v) {


              /*  DatabaseReference reference = FirebaseDatabase.getInstance().getReference("missing_persons").child("");
                Map<String,Object> updated = new HashMap<>();
                updated.put("victimName",victimname.getText().toString());
                updated.put("obNumber",ob.getText().toString());
                updated.put("victimLastSeen",lastseen.getText().toString());
                updated.put("victimDob",dob.getText().toString());
                updated.put("victimDescription",description.getText().toString());
                updated.put("victimHome",victimhome.getText().toString());
                updated.put("victimContact1",vc1.getText().toString());
                updated.put("victimContact2",vc2.getText().toString());
                updated.put("caseStatus",cstatus.getText().toString());
                reference.updateChildren(updated).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(@NonNull Void unused) {
                        Toast.makeText(EditMP.this, "Missing person details updated", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),MPList.class));
                        finish();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(EditMP.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });




            }
        });*/

        victimname.setText(vname);
        ob.setText(obNumber);
        lastseen.setText(victimLastSeen);
        dob.setText(victimDob);
        description.setText(victimDescription);
        victimhome.setText(victimHome);
        vc1.setText(victimContact1);
        vc2.setText(victimContact2);
        cstatus.setText(caseStatus);


    }
}