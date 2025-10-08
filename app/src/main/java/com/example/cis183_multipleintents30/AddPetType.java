package com.example.cis183_multipleintents30;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddPetType extends AppCompatActivity {


    TextView tv_j_error;
    EditText et_j_type;
    Button btn_j_back;
    Button btn_j_add;

    Intent intent_j_addPet;


    //can use array of errors
    //String[] errors = {};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_pet_type);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tv_j_error = findViewById(R.id.tv_v_addType_error);
        et_j_type = findViewById(R.id.et_v_addType_add);
        btn_j_back = findViewById(R.id.btn_v_petType_back);
        btn_j_add = findViewById(R.id.btn_v_petType_add);

        intent_j_addPet = new Intent(AddPetType.this, AddPet.class);


        setAllClickListeners();

    }


    private void setAllClickListeners()
    {
        btn_j_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent_j_addPet);
            }
        });

        btn_j_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int errorCode = checkUserInput();

                if(errorCode == 1)
                {
                    tv_j_error.setText("Error: you need to fill out textbox");
                    tv_j_error.setVisibility(View.VISIBLE);
                }
                else if (errorCode == 2)
                {
                    tv_j_error.setText("Error: this type already exists");
                    tv_j_error.setVisibility(View.VISIBLE);
                }
                else if(errorCode == 0)
                {
                    Pet.PetType.addPetType(et_j_type.getText().toString());
                    startActivity(intent_j_addPet);
                }

            }
        });
    }


    private int checkUserInput()
    {
        //0 : good input
        //1 : no data/invalid input
        //2 : type already exists

        String input = et_j_type.getText().toString();

        if(input.isEmpty())
        {
            return 1;
        }
        else
        {
            //if the input was not null
            //but contains a digit
            //return 1
            for(char c: input.toCharArray())
            {
                if(Character.isDigit(c))
                {
                    return 1;
                }
            }
            //if the input was not null
            //but is already in our list
            //return 2
            for(int i = 0; i < Pet.PetType.getAllPetTypes().size(); i++)
            {
                if(input.equalsIgnoreCase(Pet.PetType.petAt(i)))
                {
                    //this type that was keyed in by the user already exists
                    return 2;
                }
            }

        }

        //if i have made it this far and not hit a return yet
        //the input is good.
        return 0;
    }
}