package com.example.eggmanager;

import android.app.Activity;
import android.content.Intent;
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
    private static int index = 0;

    public static ArrayList<Egg> eggList = new ArrayList<>();
    private ArrayAdapter<Egg> adapter;

    private Egg selectedEgg = null;

    public static ArrayList<Egg> getEggList() {
        return eggList;
    }

    public static void addEgg(Egg egg) {
        egg.setId(index);
        eggList.add(egg);
        index++;
    }

    public static void updateEgg(Egg updatedEgg) {
        for (int i = 0; i < eggList.size(); i++) {
            if (eggList.get(i).getId() == updatedEgg.getId()) {
                eggList.set(i, updatedEgg);
                break;
            }
        }
    }

    public static Egg getEgg(int eggId) {
        for (Egg egg : eggList) {
            if (egg.getId() == eggId) {
                return egg;
            }
        }
        return null;
    }

    public static void deleteEgg(int eggId) {
        for (Egg egg : eggList) {
            if (egg.getId() == eggId) {
                eggList.remove(egg);
                break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnCreateEgg = findViewById(R.id.btnCreateEgg);
        Button btnReadEggs = findViewById(R.id.btnReadEggs);

        // insert some realistic eggs
        Egg egg1 = new Egg("Mirela", "Gallus gallus domesticus", "Medium", 15);
        Egg egg2 = new Egg("Doru", "Falco", "Small", 3);
        Egg egg3 = new Egg("Ionica", "Dromaius novaehollandiae", "Large", 30);
        Egg egg4 = new Egg("Janos", "Paridae", "Small", 22);

        eggList.add(egg1);
        eggList.add(egg2);
        eggList.add(egg3);
        eggList.add(egg4);
        
        btnCreateEgg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start the Create Egg activity
                Intent intent = new Intent(MainActivity.this, CreateEggActivity.class);
                startActivity(intent);
            }
        });

        btnReadEggs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start the Read Eggs activity
                Intent intent = new Intent(MainActivity.this, ReadEggsActivity.class);
                startActivity(intent);
            }
        });

    }
}
