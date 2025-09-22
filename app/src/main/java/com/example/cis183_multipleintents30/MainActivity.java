package com.example.cis183_multipleintents30;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {

    static private ArrayList<Pet> listOfPets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //I need a list to house all pets for the vet clinic
        listOfPets = new ArrayList<>();
        Pet pet = new Pet();
        pet.setName("Tito");
        pet.setAge(12);
        pet.setType("Dog");

        Pet anotherpet = new Pet("Willow", 5, "Dog");


        Log.d("Pet Dat:", pet.getName() + " is a " + pet.getType() + " and is " + pet.getAge() + " years old");
    }


    private void addDummyDataToArrayList()
    {
        //create a new pet object
        //fill in all pet information
        //add that pet to the arraylist
        Pet newPet = new Pet("Tito", 7, Pet.PetType.petAt(0));
        listOfPets.add(newPet);
        newPet = new Pet("Willow", 3, Pet.PetType.petAt(0));
        listOfPets.add(newPet);
        newPet = new Pet("Whiskers", 9, Pet.PetType.petAt(1));
        listOfPets.add(newPet);

    }
}