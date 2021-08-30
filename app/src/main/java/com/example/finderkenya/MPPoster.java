package com.example.finderkenya;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class MPPoster extends AppCompatActivity   {

    private EditText victimname,ob,lastseen,dob,description,victimhome,vc1,vc2,cstatus;
    private FloatingActionButton edit,share,download;
    private TextView  sighting;
    private ImageButton imgBtn;

    public static final String TAG ="TAG";


    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private DatabaseReference reference;

    private MPHelperClass mpHelperClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mpposter);

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
        String image= data.getStringExtra("postImage");

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
        imgBtn=findViewById(R.id.imgBtn);
        sighting=findViewById(R.id.sighting);
        edit=findViewById(R.id.fabEdit);



        victimname.setText(vname);
        ob.setText(obNumber);
        lastseen.setText(victimLastSeen);
        dob.setText(victimDob);
        description.setText(victimDescription);
        victimhome.setText(victimHome);
        vc1.setText(victimContact1);
        vc2.setText(victimContact2);
        cstatus.setText(caseStatus);
        Glide.with(getApplicationContext()).load(image).into(imgBtn);








        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();


       // Log.d(TAG, "onCreate:" + vname + " " + obNumber + " " + victimLastSeen + " " + victimDob + " " + victimHome + " ");



        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MPPoster.this,EditMP.class);
                intent.putExtra("victimName",victimname.getText().toString());
                intent.putExtra("obNumber",ob.getText().toString() );
                intent.putExtra("victimLastSeen",lastseen.getText().toString());
                intent.putExtra("victimDob",dob.getText().toString());
                intent.putExtra("victimDescription",description.getText().toString());
                intent.putExtra("victimHome",victimhome.getText().toString());
                intent.putExtra("victimContact1",vc1.getText().toString());
                intent.putExtra("victimContact2",vc2.getText().toString());
                intent.putExtra("caseStatus",cstatus.getText().toString());


                startActivity(intent);
            }
        });


        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        sighting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Sightings.class);
                intent.putExtra("victimName",victimname.getText().toString());
                intent.putExtra("obNumber",ob.getText().toString() );
               // intent.putExtra("postImage",list.get(getAdapterPosition()).getPostImage());
                startActivity(intent);

            }
        });




    }


}