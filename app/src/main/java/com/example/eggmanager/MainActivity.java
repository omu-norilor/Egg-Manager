package com.example.eggmanager;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends Activity {

    private EditText editTextName;
    private EditText editTextSpecies;
    private Spinner spinnerSize;
    private EditText editTextDaysToHatch;
    private Button btnCreate;
    private Button btnRead;
    private Button btnUpdate;
    private Button btnDelete;
    private ListView listView;

    private ArrayList<Egg> eggList = new ArrayList<>();
    private ArrayAdapter<Egg> adapter;

    private Egg selectedEgg = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        editTextSpecies = findViewById(R.id.editTextSpecies);
        spinnerSize = findViewById(R.id.spinnerSize);
        editTextDaysToHatch = findViewById(R.id.editTextDaysToHatch);
        btnCreate = findViewById(R.id.btnCreate);
        btnRead = findViewById(R.id.btnRead);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        listView = findViewById(R.id.listView);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, eggList);
        listView.setAdapter(adapter);

        // Populate the spinner with the size options
        ArrayAdapter<CharSequence> sizeAdapter = ArrayAdapter.createFromResource(this, R.array.size_array, android.R.layout.simple_spinner_item);
        sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSize.setAdapter(sizeAdapter);


        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextName.getText().toString();
                String species = editTextSpecies.getText().toString();
                String size = spinnerSize.getSelectedItem().toString();
                int daysToHatch = Integer.parseInt(editTextDaysToHatch.getText().toString());

                Egg newEgg = new Egg(name, species, size, daysToHatch);
                eggList.add(newEgg);
                adapter.notifyDataSetChanged();

                editTextName.setText("");
                editTextSpecies.setText("");
                spinnerSize.setSelection(0);
                editTextDaysToHatch.setText("");
            }
        });

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (eggList.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "No eggs available.", Toast.LENGTH_SHORT).show();
                } else {
                    // Display the list of eggs (toString method in Egg class is used for display)
                    for (Egg egg : eggList) {
                        Toast.makeText(getApplicationContext(), egg.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                selectedEgg = eggList.get(position);
                editTextName.setText(selectedEgg.getName());
                editTextSpecies.setText(selectedEgg.getSpecies());
                spinnerSize.setSelection(sizeAdapter.getPosition(selectedEgg.getSize()));
                editTextDaysToHatch.setText(String.valueOf(selectedEgg.getDaysToHatch()));
                Toast.makeText(getApplicationContext(), "Selected Egg: " + selectedEgg.getName(), Toast.LENGTH_SHORT).show();
                btnUpdate.setText("Update");
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedEgg != null) {
                    String name = editTextName.getText().toString();
                    String species = editTextSpecies.getText().toString();
                    String size = spinnerSize.getSelectedItem().toString();
                    int daysToHatch = Integer.parseInt(editTextDaysToHatch.getText().toString());

                    selectedEgg.setName(name);
                    selectedEgg.setSpecies(species);
                    selectedEgg.setSize(size);
                    selectedEgg.setDaysToHatch(daysToHatch);

                    adapter.notifyDataSetChanged();

                    editTextName.setText("");
                    editTextSpecies.setText("");
                    spinnerSize.setSelection(0);
                    editTextDaysToHatch.setText("");
                    selectedEgg = null;
                    btnUpdate.setText("Save Update");
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedEgg != null) {
                    eggList.remove(selectedEgg);
                    adapter.notifyDataSetChanged();
                    selectedEgg = null;
                    btnUpdate.setText("Update");
                }
            }
        });
    }
}
