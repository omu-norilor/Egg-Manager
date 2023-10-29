package com.example.eggmanager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CreateEggActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextSpecies;
    private Spinner spinnerSize;
    private EditText editTextDaysToHatch;
    private Button btnCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_egg);

        // Initialize UI components
        editTextName = findViewById(R.id.editTextNameCreate);
        editTextSpecies = findViewById(R.id.editTextSpeciesCreate);
        spinnerSize = findViewById(R.id.spinnerSizeCreate);
        editTextDaysToHatch = findViewById(R.id.editTextDaysToHatchCreate);
        btnCreate = findViewById(R.id.btnCreate);

        // Populate the spinner with size options (replace with your data)
        ArrayAdapter<CharSequence> sizeAdapter = ArrayAdapter.createFromResource(this, R.array.size_array, android.R.layout.simple_spinner_item);
        sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSize.setAdapter(sizeAdapter);

        // Set a click listener for the "Create Egg" button
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get user-entered information
                String name = editTextName.getText().toString();
                String species = editTextSpecies.getText().toString();
                String size = spinnerSize.getSelectedItem().toString();
                int daysToHatch = Integer.parseInt(editTextDaysToHatch.getText().toString());

                // Create an Egg object with the entered data (replace with your Egg class constructor)
                Egg newEgg = new Egg(name, species, size, daysToHatch);

                // Add the new egg to your list (from MainActivity)
                MainActivity.addEgg(newEgg);

                // Close the CreateEggActivity
                finish();
            }
        });
    }
}
