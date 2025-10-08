package com.example.cis183_multipleintents30;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddPet extends AppCompatActivity {



    Button btn_j_add_back;
    Button getBtn_j_add_add;

    Intent intent_j_mainIntent;
    Intent intent_j_addPetType;

    EditText et_j_add_name;
    EditText et_j_add_age;
    TextView tv_j_add_type;

    Spinner sp_j_type;

    //String firstPetType = Pet.PetType.petAt(0);

    //we can use an array adapter for the spinner

    ArrayAdapter<String> adapter;



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

        btn_j_add_back = findViewById(R.id.btn_v_add_back);
        getBtn_j_add_add = findViewById(R.id.btn_v_add_add);

        intent_j_mainIntent = new Intent(AddPet.this, MainActivity.class);
        intent_j_addPetType = new Intent(AddPet.this, AddPetType.class);

        et_j_add_name = findViewById(R.id.et_v_add_name);
        et_j_add_age = findViewById(R.id.et_v_add_age);
        tv_j_add_type = findViewById(R.id.tv_v_add_newPetType);
        sp_j_type = findViewById(R.id.sp_v_add_type);


        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, Pet.PetType.getAllPetTypes());
        sp_j_type.setAdapter(adapter);


        //addpetBackButtonOnClickListener();

        addButtonClickListener();
        addpetBackButtonOnClickListener();
        addNewTypeEventListener();
    }


    private void addpetBackButtonOnClickListener()
    {
        btn_j_add_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //Pet p = new Pet("George", 4, Pet.PetType.petAt(0));
                //intent_j_mainIntent.putExtra("PetData", p);
                startActivity(intent_j_mainIntent);


            }
        });
    }


    private void addButtonClickListener()
    {
        getBtn_j_add_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPetDataFromTextBoxes();
            }
        });
    }

    private void addNewTypeEventListener()
    {
        tv_j_add_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent_j_addPetType);
            }
        });
    }

    private void getPetDataFromTextBoxes()
    {
        //get data from textboxes
        //make a pet
        //send pet to main
        //add pet to list

        //String petName = et_v_addpet_addName.getText().toString();
        //int petAge = Integer.parseInt(et_v_addpet_addAge.getText().toString());
        //int petType = Integer.parseInt(et_v_addpet_addType.getText().toString());


        //Pet p = new Pet(petName, petAge, Pet.PetType.petAt(petType));



        Pet p = new Pet();
        p.setName(et_j_add_name.getText().toString());
        p.setType(sp_j_type.getSelectedItem().toString());
        p.setAge(Integer.parseInt(et_j_add_age.getText().toString()));


        intent_j_mainIntent.putExtra("PetData", p);

        startActivity(intent_j_mainIntent);


    }



}