package com.example.eggmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.eggmanager.Egg;
import com.example.eggmanager.MainActivity;
import com.example.eggmanager.R;

import java.util.ArrayList;

public class ReadEggsActivity extends AppCompatActivity {

    private ListView listView;
    private Button btnUpdate;
    private Button btnDelete;
    private TextView selectedEggText;
    private ArrayAdapter<Egg> adapter;
    private ArrayList<Egg> eggList;

    Egg selectedEgg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_eggs);

        // Initialize your ListView and buttons
        listView = findViewById(R.id.listView);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        selectedEggText = findViewById(R.id.readSelectedEgg);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedEgg = (Egg) parent.getItemAtPosition(position);
                selectedEggText.setText("Selected Egg: " + selectedEgg.getName());
            }
        });
        // Retrieve the list of eggs received from MainActivity
        eggList = MainActivity.getEggList();

        // Initialize an adapter to populate the ListView with egg data
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, eggList);
        listView.setAdapter(adapter);
        // Set click listeners for update and delete buttons
        btnUpdate.setOnClickListener(view -> {
            // open the UpdateEggActivity and pass the selected
                if(selectedEgg != null){
                Intent intent = new Intent(ReadEggsActivity.this, UpdateEggActivity.class);
                intent.putExtra("selectedEggId", String.valueOf(selectedEgg.getId()));
                startActivity(intent);
                finish();
            }
                else
            Toast.makeText(ReadEggsActivity.this, "Please select an egg to delete", Toast.LENGTH_SHORT).show();
        });

        btnDelete.setOnClickListener(view -> {
            // open the DeleteEggActivity and pass the selected egg
            if(selectedEgg != null){
                Intent intent = new Intent(ReadEggsActivity.this, DeleteEggActivity.class);
                intent.putExtra("selectedEggId", String.valueOf(selectedEgg.getId()));
                startActivity(intent);
                finish();
            }
            else
                Toast.makeText(ReadEggsActivity.this, "Please select an egg to delete", Toast.LENGTH_SHORT).show();
        });
    }
}
