package com.example.weighttracker;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class BMICalculatorActivity extends AppCompatActivity {

    private EditText weightEditText;
    private EditText heightEditText;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_calculator);

        weightEditText = findViewById(R.id.weight_input);
        heightEditText = findViewById(R.id.height_input);
        resultTextView = findViewById(R.id.result_text_view);
        Button calculateButton = findViewById(R.id.calculate_button);

        calculateButton.setOnClickListener(v -> calculateBMI());
    }

    private void calculateBMI() {
        String weightStr = weightEditText.getText().toString();
        String heightStr = heightEditText.getText().toString();

        if (!weightStr.isEmpty() && !heightStr.isEmpty()) {
            float weight = Float.parseFloat(weightStr);
            float height = Float.parseFloat(heightStr) / 100;

            float bmi = weight / (height * height);
            resultTextView.setText(String.format("Your BMI: %.2f", bmi));
        } else {
            resultTextView.setText("Please enter valid weight and height");
        }
    }
}
