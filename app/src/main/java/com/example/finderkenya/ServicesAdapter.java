package com.example.finderkenya;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ServicesAdapter extends ArrayAdapter<ServicesList> {

    public ServicesAdapter(Context context, ArrayList<ServicesList> servicesListArrayList){
        super(context,R.layout.list_item,servicesListArrayList);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ServicesList servicesList = getItem(position);

        if(convertView == null){

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }

        CircleImageView imageView = convertView.findViewById(R.id.profile);
        TextView organization = convertView.findViewById(R.id.organizationName);

        imageView.setImageResource(servicesList.imageId);
        organization.setText(servicesList.serviceName);

        return convertView;
    }
}
