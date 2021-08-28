package com.example.finderkenya;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;


    ArrayList<MPHelperClass> list;
    private  onInfoListener mOnInfoListener;

    public MyAdapter(Context context, ArrayList<MPHelperClass> list, onInfoListener onInfoListener) {
        this.context = context;
        this.list = list;
        this.mOnInfoListener = onInfoListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new MyViewHolder(v,mOnInfoListener);
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

    public static  class MyViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{

        TextView victimName,datePosted,postedBy;
        CircleImageView postImage;
        ImageButton info;
        onInfoListener onInfoListener;

       public MyViewHolder(@NonNull View itemView,onInfoListener onInfoListener) {
           super(itemView);

           victimName = itemView.findViewById(R.id.victimname);
           datePosted = itemView.findViewById(R.id.datePosted);
           postedBy = itemView.findViewById(R.id.reporter);
           postImage = itemView.findViewById(R.id.victimImage);
           info = itemView.findViewById(R.id.info);
           this.onInfoListener =onInfoListener;

           itemView.setOnClickListener(this);



       }

        @Override
        public void onClick(View v) {
            onInfoListener.onInfoClick(getAdapterPosition());

        }
    }

   public interface onInfoListener{
        void onInfoClick(int position);
   }
}
