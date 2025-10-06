package com.example.cis183_multipleintents30;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddPet extends AppCompatActivity {



    Button btn_j_addpet_back;

    Intent intent_j_mainIntent;

    EditText et_v_addpet_addName;
    EditText et_v_addpet_addAge;
    EditText et_v_addpet_addType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_pet);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //this is code to get information passed from mainActivity.java
        //get the intent that called me
        Intent cameFrom = getIntent();
        //get the bundle that was passed to me from cameFrom intent
        Bundle infoPassedToMe = cameFrom.getExtras();

        //we do not know if infoPassedToMe will have any data
        //because we do not know who loaded this intent
        if(infoPassedToMe != null)
        {
            String data = infoPassedToMe.getString("InfoPassed");
            Log.d("INFO PASSED FROM MAIN: ", data);
        }

        btn_j_addpet_back = findViewById(R.id.btn_v_addpet_back);
        intent_j_mainIntent = new Intent(AddPet.this, MainActivity.class);

        et_v_addpet_addName = findViewById(R.id.et_v_addpet_addName);
        et_v_addpet_addAge = findViewById(R.id.et_v_addpet_addAge);
        et_v_addpet_addType = findViewById(R.id.et_v_addpet_addType);




        addpetBackButtonOnClickListener();
    }


    private void addpetBackButtonOnClickListener()
    {
        btn_j_addpet_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //Pet p = new Pet("George", 4, Pet.PetType.petAt(0));
                //intent_j_mainIntent.putExtra("PetData", p);
                //startActivity(intent_j_mainIntent);

                getPetDataFromTextBoxes();
            }
        });
    }

    private void getPetDataFromTextBoxes()
    {
        //get data from textboxes
        //make a pet
        //send pet to main
        //add pet to list

        String petName = et_v_addpet_addName.getText().toString();
        int petAge = Integer.parseInt(et_v_addpet_addAge.getText().toString());
        int petType = Integer.parseInt(et_v_addpet_addType.getText().toString());


        Pet p = new Pet(petName, petAge, Pet.PetType.petAt(petType));
        intent_j_mainIntent.putExtra("PetData", p);
        startActivity(intent_j_mainIntent);


    }
}