package com.example.finderkenya;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class SightingsAdapter extends RecyclerView.Adapter<SightingsAdapter.MyViewHolder> {


    Context context;

    ArrayList<SightingHelperClass> list;

    public SightingsAdapter(Context context, ArrayList<SightingHelperClass> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item2,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        SightingHelperClass sightingHelperClass = list.get(position);
        holder.victimName.setText(sightingHelperClass.getVictimName());
        holder.dateSeen.setText(sightingHelperClass.getDateSeen());
        holder.seenBy.setText(sightingHelperClass.getFname());
        holder.location.setText(sightingHelperClass.getLocationSighting());
        holder.contact.setText(sightingHelperClass.getMobileNo());
        Glide
                .with(context)
                .load(sightingHelperClass.getPostImage())
                .placeholder(R.drawable.profile)
                .circleCrop()
                .error(R.drawable.ic_launcher_background)
                .into(holder.postImage);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder  extends RecyclerView.ViewHolder{

        TextView victimName,dateSeen,seenBy,location, contact;
        CircleImageView postImage;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            victimName = itemView.findViewById(R.id.victimname);
            dateSeen = itemView.findViewById(R.id.dateSeen);
            seenBy = itemView.findViewById(R.id.reporter);
            postImage = itemView.findViewById(R.id.victimImage);
            location = itemView.findViewById(R.id.location);
            contact = itemView.findViewById(R.id.contact);
        }
    }


}
