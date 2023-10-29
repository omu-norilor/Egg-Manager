package com.example.eggmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.eggmanager.MainActivity;
import com.example.eggmanager.R;

public class DeleteEggActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_egg);

        // Get the egg name passed as an extra from the calling activity (MainActivity)

        int eggId = Integer.parseInt(getIntent().getStringExtra("selectedEggId"));
        Egg selectedEgg = MainActivity.getEgg(eggId);
        // Display the prompt with the egg name
        TextView tvDeletePrompt = findViewById(R.id.tvDeletePrompt);
        tvDeletePrompt.setText("Are you sure you want to delete egg with name: " + selectedEgg.getName()+"?");

        // Get references to the "Yes" and "No" buttons
        Button btnYes = findViewById(R.id.btnYes);
        Button btnNo = findViewById(R.id.btnNo);

        // Set a click listener for the "Yes" button
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform the deletion of the egg using a method in MainActivity
                MainActivity.deleteEgg(eggId);
                // Close the DeleteEggActivity
                finish();
            }
        });

        // Set a click listener for the "No" button
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Close the DeleteEggActivity without deleting
                finish();
            }
        });
    }
}
