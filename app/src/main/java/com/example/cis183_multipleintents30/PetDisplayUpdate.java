package com.example.cis183_multipleintents30;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PetDisplayUpdate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pet_display_update);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //we know we were loaded from MainActivity
        //code to get info passed from mainactiviity.java
        //get the intent that called me

        Intent loadedFrom = getIntent();

        Pet petPassed = (Pet) loadedFrom.getSerializableExtra("PetData");
        if(petPassed != null)
        {
            Log.d("Pet Passed To Me: ", petPassed.getName());
        }
        


    }
}