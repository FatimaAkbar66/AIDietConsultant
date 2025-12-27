package com.example.dietconsultantapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
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

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AIResultActivity extends AppCompatActivity {

    private TextView tvAIDietPlan, tvUserGoalDisplay, tvUserInfoSummary;
    private ProgressBar calorieProgress;
    private Button btnBackHome;

    // TODO: AI Studio se "Copy Key" karke yahan paste karein
    private final String GEMINI_API_KEY = "YOUR_NEW_KEY_HERE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ai_result);

        tvAIDietPlan = findViewById(R.id.tvAIDietPlan);
        tvUserGoalDisplay = findViewById(R.id.tvUserGoalDisplay);
        tvUserInfoSummary = findViewById(R.id.tvUserInfoSummary);
        calorieProgress = findViewById(R.id.calorieProgress);
        btnBackHome = findViewById(R.id.btnBackHome);

        String name = getIntent().getStringExtra("NAME");
        String goal = getIntent().getStringExtra("GOAL");
        String age = getIntent().getStringExtra("AGE");
        String weight = getIntent().getStringExtra("WEIGHT");

        tvUserGoalDisplay.setText(goal);
        tvUserInfoSummary.setText(name + " • " + age + " yrs • " + weight + "kg");

        startGeminiAI(name, goal, age, weight);

        btnBackHome.setOnClickListener(v -> finish());
    }

    private void startGeminiAI(String name, String goal, String age, String weight) {
        tvAIDietPlan.setText("AI is preparing your diet plan...");

        // Aapki list ke mutabiq sab se stable model name
        GenerativeModel gm = new GenerativeModel("gemini-flash-latest", "AIzaSyDlLagklEVPsg6ljlcEFpkKUp2puvhLY9I");
        GenerativeModelFutures model = GenerativeModelFutures.from(gm);

        String prompt = "Give a 3-meal healthy diet plan for " + name +
                " (Goal: " + goal + ", Age: " + age + ", Weight: " + weight + "kg).";

        Content content = new Content.Builder().addText(prompt).build();
        ListenableFuture<GenerateContentResponse> response = model.generateContent(content);

        Futures.addCallback(response, new FutureCallback<GenerateContentResponse>() {
            @Override
            public void onSuccess(GenerateContentResponse result) {
                // 1. AI se text lena
                String originalText = result.getText();

                // 2. Cleaning: Saare faltu symbols (*, #, _) ko khatam karna
                // Taake diet plan bilkul saaf list ki tarah dikhe
                String cleanText = originalText
                        .replace("**", "")  // Bold symbols hatana
                        .replace("*", "•")   // Dots ki jagah bullet point lagana
                        .replace("#", "")    // Heading symbols hatana
                        .trim();             // Faltu spaces khatam karna

                // 3. UI update karna
                runOnUiThread(() -> {
                    tvAIDietPlan.setText(cleanText);
                    calorieProgress.setProgress(100); // Progress bar full kar dena
                });
            }

            @Override
            public void onFailure(@NonNull Throwable t) {
                Log.e("AI_ERROR", t.getMessage());
                runOnUiThread(() -> {
                    // Agar error aaye to user ko bataein
                    tvAIDietPlan.setText("AI Error: " + t.getMessage());
                });
            }
        }, this.getMainExecutor());
    }
}