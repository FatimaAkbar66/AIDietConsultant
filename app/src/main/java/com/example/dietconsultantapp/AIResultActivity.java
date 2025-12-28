package com.example.dietconsultantapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View; // Added
import android.widget.Button;
import android.widget.ImageView; // Added
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.ai.client.generativeai.GenerativeModel;
import com.google.ai.client.generativeai.java.GenerativeModelFutures;
import com.google.ai.client.generativeai.type.Content;
import com.google.ai.client.generativeai.type.GenerateContentResponse;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.firebase.auth.FirebaseAuth;

public class AIResultActivity extends AppCompatActivity {

    private TextView tvAIDietPlan, tvUserGoalDisplay, tvUserInfoSummary;
    private ProgressBar calorieProgress;
    private Button btnBackHome;
    private ImageView ivLogout; // New Logout Icon

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ai_result);

        // Initialize UI Elements
        tvAIDietPlan = findViewById(R.id.tvAIDietPlan);
        tvUserGoalDisplay = findViewById(R.id.tvUserGoalDisplay);
        tvUserInfoSummary = findViewById(R.id.tvUserInfoSummary);
        calorieProgress = findViewById(R.id.calorieProgress);
        btnBackHome = findViewById(R.id.btnBackHome);
        ivLogout = findViewById(R.id.ivLogout); // Connecting the XML Logout icon

        // Get Data from Intent
        String name = getIntent().getStringExtra("NAME");
        String goal = getIntent().getStringExtra("GOAL");
        String age = getIntent().getStringExtra("AGE");
        String weight = getIntent().getStringExtra("WEIGHT");

        // Display User Info
        tvUserGoalDisplay.setText(goal);
        tvUserInfoSummary.setText(name + " • " + age + " yrs • " + weight + "kg");

        // Start AI Process
        startGeminiAI(name, goal, age, weight);

        // Logout Logic for the NEW Icon in XML
        ivLogout.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(AIResultActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });

        btnBackHome.setOnClickListener(v -> finish());
    }

    // Note: onCreateOptionsMenu and onOptionsItemSelected are removed
    // because we are now using the custom ImageView for Logout.

    private void startGeminiAI(String name, String goal, String age, String weight) {
        tvAIDietPlan.setText("AI is preparing your diet plan...");

        // Initialize Gemini Model
        GenerativeModel gm = new GenerativeModel("gemini-flash-latest", "your gemnini API");
        GenerativeModelFutures model = GenerativeModelFutures.from(gm);

        // Prepare Prompt
        String prompt = "Give a 3-meal healthy diet plan for " + name +
                " (Goal: " + goal + ", Age: " + age + ", Weight: " + weight + "kg).";

        Content content = new Content.Builder().addText(prompt).build();
        ListenableFuture<GenerateContentResponse> response = model.generateContent(content);

        Futures.addCallback(response, new FutureCallback<GenerateContentResponse>() {
            @Override
            public void onSuccess(GenerateContentResponse result) {
                String originalText = result.getText();

                // Cleaning symbols
                String cleanText = originalText
                        .replace("**", "")
                        .replace("*", "•")
                        .replace("#", "")
                        .trim();

                runOnUiThread(() -> {
                    tvAIDietPlan.setText(cleanText);
                    calorieProgress.setProgress(100);
                });
            }

            @Override
            public void onFailure(@NonNull Throwable t) {
                Log.e("AI_ERROR", t.getMessage());
                runOnUiThread(() -> {
                    tvAIDietPlan.setText("AI Error: " + t.getMessage());
                });
            }
        }, this.getMainExecutor());
    }
}