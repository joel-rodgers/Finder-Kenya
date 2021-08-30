package com.example.finderkenya;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
