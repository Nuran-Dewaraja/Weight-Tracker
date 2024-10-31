package com.example.weighttracker;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddWeightActivity extends AppCompatActivity {

    private EditText weightEditText;
    private EditText weightEditDate;
    private Button saveButton;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_weight);

        weightEditText = findViewById(R.id.weight_input);
        weightEditDate = findViewById(R.id.date_input);
        saveButton = findViewById(R.id.save_button);
        dbHelper = new DatabaseHelper(this);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String weight = weightEditText.getText().toString().trim();
                String date = weightEditDate.getText().toString().trim();

                if (weight.length() > 0) {
                    boolean inserted = dbHelper.addWeightEntry(weight, date);
                    if (inserted) {
                        Toast.makeText(AddWeightActivity.this, "Weight saved successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(AddWeightActivity.this, "Error saving weight", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(AddWeightActivity.this, "Please enter a weight", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
