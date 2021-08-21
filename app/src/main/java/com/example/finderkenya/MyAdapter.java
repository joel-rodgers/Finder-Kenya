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

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;

    ArrayList<MPHelperClass> list;

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

        //Glide.with(holder.postImage.getContext())
               // .load(MPHelperClass.getImageUrl())
               // .placeholder(R.drawable.profile)
               // .circleCrop()
                //.error(R.drawable.profile)
               // .into(holder.postImage);



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static  class MyViewHolder extends RecyclerView.ViewHolder{

        TextView victimName,datePosted,postedBy;
        CircleImageView postImage;

       public MyViewHolder(@NonNull View itemView) {
           super(itemView);

           victimName = itemView.findViewById(R.id.victimname);
           datePosted = itemView.findViewById(R.id.datePosted);
           postedBy = itemView.findViewById(R.id.reporter);
           postImage = itemView.findViewById(R.id.victimImage);
       }
   }
}
