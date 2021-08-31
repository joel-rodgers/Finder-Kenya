package com.example.finderkenya;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserProfile extends AppCompatActivity {
    private TextView fullnames_field;
    private EditText fullnames_profile,username_profile,email_profile,mobile_profile,pass_profile;
    private Button update;
    private CircleImageView circleImageView;
    private ImageRecyclerAdapter imageRecyclerAdapter;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private List<ImageList> imagesList;
    private static  final int IMAGE_REQUEST =1;
    private  StorageTask storageTask;
    private StorageReference storageReference;
    private Uri imageUri;
    private UserHelperClass userHelperClass;


    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        //reference = FirebaseDatabase.getInstance().getReference("users");

        //Hooks
        imagesList = new ArrayList<>();
        circleImageView=findViewById(R.id.profile_image);
        fullnames_field=findViewById(R.id.fullnames_field);
        //username_field=findViewById(R.id.username_field);
        fullnames_profile=findViewById(R.id.fullnames_profile);
        username_profile=findViewById(R.id.username_profile);
        email_profile=findViewById(R.id.email_profile);
        mobile_profile=findViewById(R.id.mobile_profile);
        pass_profile=findViewById(R.id.pass_profile);
        update=findViewById(R.id.update);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();
        storageReference= FirebaseStorage.getInstance().getReference("profile_images");


        reference = FirebaseDatabase.getInstance().getReference("users").child(firebaseUser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userHelperClass = snapshot.getValue(UserHelperClass.class);
                assert  userHelperClass !=null;
                fullnames_field.setText(userHelperClass.getFname());
                //username_field.setText(userHelperClass.getUname());
                fullnames_profile.setText(userHelperClass.getFname());
                username_profile.setText(userHelperClass.getUname());
                email_profile.setText(userHelperClass.getMail());
                mobile_profile.setText(userHelperClass.getMobileNo());
                pass_profile.setText(userHelperClass.getPwd());


                if(userHelperClass.getImageURL().equals("default")){
                    circleImageView.setImageResource(R.drawable.profile);
                }else{
                    Glide.with(getApplicationContext()).load(userHelperClass.getImageURL()).error(R.drawable.profile).into(circleImageView);
                }

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(UserProfile.this,error.getMessage() , Toast.LENGTH_SHORT).show();

            }


        });
        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(UserProfile.this);
                builder.setCancelable(true);
                View mView = LayoutInflater.from(UserProfile.this).inflate(R.layout.select_image_layout,null);
                RecyclerView recyclerView = mView.findViewById(R.id.recyclerView);
                collectOldImages();
                recyclerView.setLayoutManager(new GridLayoutManager(UserProfile.this,3));
                recyclerView.setHasFixedSize(true);
                imageRecyclerAdapter = new ImageRecyclerAdapter(imagesList,UserProfile.this);
                recyclerView.setAdapter(imageRecyclerAdapter);
                imageRecyclerAdapter.notifyDataSetChanged();
                Button openImage = mView.findViewById(R.id.openImages);
                openImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openImage();
                    }
                });
                builder.setView(mView);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();



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

    private void openImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,IMAGE_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == IMAGE_REQUEST && resultCode == RESULT_OK && data !=null && data.getData() !=null) {
            imageUri = data.getData();
            if(storageTask != null && storageTask.isInProgress()){
                Toast.makeText(this, "Uploading is in progress", Toast.LENGTH_SHORT).show();
            }
            else{
                uploadImage();
            }

        }
    }

    private void uploadImage() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Uploading image");
        progressDialog.show();

        if(imageUri != null){
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),imageUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            assert bitmap != null;
            bitmap.compress(Bitmap.CompressFormat.JPEG,25,byteArrayOutputStream);
            byte[] imageFileToByte = byteArrayOutputStream.toByteArray();
            final StorageReference imageReference = storageReference.child(userHelperClass.getFname()+System.currentTimeMillis()+"jpg");
            storageTask = imageReference.putBytes(imageFileToByte);
            storageTask.continueWithTask((Continuation<UploadTask.TaskSnapshot, Task<Uri>>)task ->{
                if(! task.isSuccessful()){
                    throw task.getException();
                }
                return imageReference.getDownloadUrl();

            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if(task.isSuccessful()){
                        Uri downloadUri = task.getResult();
                        String sDownloadUri = downloadUri.toString();
                        Map<String,Object> hashmap = new HashMap<>();
                        hashmap.put("imageUrl",sDownloadUri);
                        reference.updateChildren(hashmap);
                        final DatabaseReference profileImagesReference = FirebaseDatabase.getInstance().getReference("profile_images").child(firebaseUser.getUid());
                        profileImagesReference.push().setValue(hashmap).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    progressDialog.dismiss();
                                }else{
                                    progressDialog.dismiss();
                                    Toast.makeText(UserProfile.this,task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    }else{
                        Toast.makeText(UserProfile.this, "Failed!", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressDialog.dismiss();
                    Toast.makeText(UserProfile.this,e.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
        }
        }


    private void collectOldImages() {
        DatabaseReference imageListReference = FirebaseDatabase.getInstance().getReference("profile_image").child(firebaseUser.getUid());
        imageListReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                imagesList.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    imagesList.add(dataSnapshot.getValue(ImageList.class));
                }

                imageRecyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UserProfile.this,error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

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