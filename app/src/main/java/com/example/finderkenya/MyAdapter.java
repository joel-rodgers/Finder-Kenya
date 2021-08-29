package com.example.finderkenya;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;


    static ArrayList<MPHelperClass> list;


    public MyAdapter(Context context, ArrayList<MPHelperClass> list) {
        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        MPHelperClass mpHelperClass = list.get(position);
        holder.victimName.setText(mpHelperClass.getVictimName());
        holder.datePosted.setText(mpHelperClass.getDate());
        holder.postedBy.setText(mpHelperClass.getFname());
        holder.obNumber.setText(mpHelperClass.getObNumber());
        holder.victimLastSeen.setText(mpHelperClass.getVictimLastSeen());
        holder.victimDob.setText(mpHelperClass.getVictimDob());
        holder.victimDescription.setText(mpHelperClass.getVictimDescription());
        holder.victimHome.setText(mpHelperClass.getVictimHome());
        holder.victimContact1.setText(mpHelperClass.getVictimContact1());
        holder.victimContact2.setText(mpHelperClass.getVictimContact2());
        holder.caseStatus.setText(mpHelperClass.getCaseStatus());
        boolean isVisible =  mpHelperClass.visibility;
        holder.constraintLayout.setVisibility(isVisible ? View.VISIBLE : View.GONE);


        Glide.with(holder.itemView.getContext())
               .load(MPHelperClass.getPostImage())
                .placeholder(R.drawable.profile)
                .circleCrop()
                .error(R.drawable.ic_launcher_background)
                .into(holder.postImage);







    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView victimName,datePosted,postedBy;
        EditText obNumber,victimLastSeen,victimDob,victimDescription,victimHome,victimContact1,victimContact2,caseStatus;
        CircleImageView postImage;
        ImageButton info;

        ConstraintLayout constraintLayout;

       public MyViewHolder(@NonNull View itemView) {
           super(itemView);

           victimName = itemView.findViewById(R.id.victimname);
           datePosted = itemView.findViewById(R.id.datePosted);
           postedBy = itemView.findViewById(R.id.reporter);
           postImage = itemView.findViewById(R.id.victimImage);
           info = itemView.findViewById(R.id.info);


           obNumber = itemView.findViewById(R.id.ob);
           victimLastSeen = itemView.findViewById(R.id.lastseen);
           victimDob = itemView.findViewById(R.id.dob);
           victimDescription = itemView.findViewById(R.id.description);
           victimHome = itemView.findViewById(R.id.victimhome);
           victimContact1 = itemView.findViewById(R.id.vc1);
           victimContact2 = itemView.findViewById(R.id.vc2);
           caseStatus = itemView.findViewById(R.id.cstatus);
           constraintLayout = itemView.findViewById(R.id.expandedLayout);

           victimName.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   MPHelperClass mpHelperClass    = list.get(getAdapterPosition());
                   mpHelperClass.setVisibility(!mpHelperClass.isVisibility());
                   notifyItemChanged(getAdapterPosition());
               }
           });

           datePosted.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   MPHelperClass mpHelperClass    = list.get(getAdapterPosition());
                   mpHelperClass.setVisibility(!mpHelperClass.isVisibility());
                   notifyItemChanged(getAdapterPosition());
               }
           });

           postedBy.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   MPHelperClass mpHelperClass    = list.get(getAdapterPosition());
                   mpHelperClass.setVisibility(!mpHelperClass.isVisibility());
                   notifyItemChanged(getAdapterPosition());
               }
           });





       }


    }


}
