package com.example.finderkenya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.finderkenya.databinding.ActivityEmergencyServicesBinding;

import java.util.ArrayList;

public class EmergencyServices extends AppCompatActivity {

    ActivityEmergencyServicesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEmergencyServicesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int[] imageId = {R.drawable.police,R.drawable.cwsk,R.drawable.mck,R.drawable.dci,R.drawable.redcross};
        String[] organization = {"Kenya Police","Child Welfare Society of Kenya","Missing Child Kenya","Directorate of Criminal Investigation","Red Cross Kenya"};

        ArrayList<ServicesList> servicesListArrayList = new ArrayList<>();

        for(int i = 0; i< imageId.length;i++){

            ServicesList servicesList = new ServicesList(organization[i],imageId[i]);
            servicesListArrayList.add(servicesList);
        }

        ServicesAdapter servicesAdapter = new ServicesAdapter(EmergencyServices.this,servicesListArrayList);

        binding.listview.setAdapter(servicesAdapter);
        binding.listview.setClickable(true);
        binding.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Uri myUri = Uri.parse("tel:0758851555");
                Intent callIntent = new Intent(Intent.ACTION_DIAL,myUri);
                startActivity(callIntent);

            }
        });

    }
}