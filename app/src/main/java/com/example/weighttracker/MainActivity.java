package com.example.weighttracker;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView weightEntriesListView;
    private DatabaseHelper dbHelper;
    private ImageButton bmiButton;
    private ImageButton addWeightButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weightEntriesListView = findViewById(R.id.weight_entries);
        dbHelper = new DatabaseHelper(this);

        bmiButton = findViewById(R.id.bmi_button);
        addWeightButton = findViewById(R.id.add_weight_button);

        displayWeightEntries();

        bmiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BMICalculatorActivity.class);
                startActivity(intent);
            }
        });

        addWeightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddWeightActivity.class);
                startActivity(intent);
            }
        });
    }

    private void displayWeightEntries() {
        Cursor cursor = dbHelper.getAllWeightEntries();
        ArrayList<String> weightEntries = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                String weight = cursor.getString(cursor.getColumnIndexOrThrow("weight"));
                String date = cursor.getString(cursor.getColumnIndexOrThrow("date"));
                weightEntries.add(date + ": " + weight + " kg");
            } while (cursor.moveToNext());
        }

        cursor.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, weightEntries);
        weightEntriesListView.setAdapter(adapter);
    }
}
