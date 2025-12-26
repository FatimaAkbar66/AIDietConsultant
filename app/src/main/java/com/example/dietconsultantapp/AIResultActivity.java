package com.example.dietconsultantapp;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;

public class AIResultActivity extends AppCompatActivity {

    private TextView tvAIDietPlan, tvUserGoalDisplay, tvUserInfoSummary;
    private ProgressBar calorieProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ai_result);

        // Initialize Views
        tvAIDietPlan = findViewById(R.id.tvAIDietPlan);
        tvUserGoalDisplay = findViewById(R.id.tvUserGoalDisplay);
        tvUserInfoSummary = findViewById(R.id.tvUserInfoSummary);
        calorieProgress = findViewById(R.id.calorieProgress);

        // Get Data from Intent (Jo hum Goals Fragment se bhejenge)
        String name = getIntent().getStringExtra("NAME");
        String goal = getIntent().getStringExtra("GOAL");
        String age = getIntent().getStringExtra("AGE");
        String weight = getIntent().getStringExtra("WEIGHT");

        // Display User Info
        if (name != null) {
            tvUserGoalDisplay.setText(goal);
            tvUserInfoSummary.setText(name + ", " + age + " years • " + weight + "kg");
        }

        // AI Magic logic
        startAIPlanGeneration(name, goal, age, weight);
    }

    private void startAIPlanGeneration(String name, String goal, String age, String weight) {
        // Abhi hum sirf text set kar rahe hain
        // Next step mein yahan Gemini API ka real code aayega
        String prompt = "Generating a " + goal + " plan for " + name + "...";
        tvAIDietPlan.setText("Hello " + name + "!\n\nBased on your goal to " + goal +
                ", our AI recommends a daily intake of 1800-2000 calories.\n\n" +
                "Breakfast: Oats with milk\nLunch: Grilled Chicken Salad\nDinner: Boiled Fish with Veggies");
    }
}