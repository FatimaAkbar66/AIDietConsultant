package com.example.dietconsultantapp;

import android.content.Intent;
import android.content.SharedPreferences;
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

public class AIResultActivity extends AppCompatActivity {

    private TextView tvAIDietPlan, tvUserGoalDisplay, tvUserInfoSummary;
    private ProgressBar calorieProgress;
    private Button btnBackHome;

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
        String weight = getIntent().getStringExtra("WEIGHT");
        String age = getIntent().getStringExtra("AGE");
        String timeline = getIntent().getStringExtra("TIMELINE");

        tvUserGoalDisplay.setText(goal);
        tvUserInfoSummary.setText(name + " • " + weight + "kg • " + age + " yrs");

        startGeminiAI(name, goal, age, weight,
                getIntent().getStringExtra("HEIGHT"),
                getIntent().getStringExtra("GENDER"),
                getIntent().getStringExtra("ACTIVITY_LEVEL"),
                getIntent().getStringExtra("STRESS_LEVEL"),
                getIntent().getStringExtra("SLEEP_HOURS"),
                timeline);

        btnBackHome.setOnClickListener(v -> {
            // Save data for Dashboard before leaving
            SharedPreferences prefs = getSharedPreferences("VioFitPrefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("saved_diet_plan", tvAIDietPlan.getText().toString());
            editor.putString("USER_NAME", name);
            editor.putString("USER_GOAL", goal);
            editor.putString("USER_TIMELINE", timeline);
            editor.putLong("plan_start_date", System.currentTimeMillis());
            editor.apply();

            startActivity(new Intent(AIResultActivity.this, DashboardActivity.class));
            finish();
        });
    }

    private void startGeminiAI(String name, String goal, String age, String weight, String height,
                               String gender, String activity, String stress, String sleep, String timeline) {

        tvAIDietPlan.setText("VioFit AI is crafting your holistic wellness plan...");

        GenerativeModel gm = new GenerativeModel("gemini-flash-latest", "gemini api key");
        GenerativeModelFutures model = GenerativeModelFutures.from(gm);

        String hyperPrompt = "Act as a professional health coach. Create a structured wellness plan for " + name + ".\n\n" +
                "User Profile: " + gender + ", " + age + "y/o, " + weight + "kg, " + height + "cm. " +
                "Goal: " + goal + " in " + timeline + ". Lifestyle: " + activity + " activity, " + stress + " stress, " + sleep + "h sleep.\n\n" +
                "Please provide the response in these EXACT sections for readability:\n" +
                "1. RECOMMENDED DURATION\n2. DAILY 3-MEAL PLAN\n3. HYDRATION GOAL\n4. TARGETED EXERCISES\n5. LIFESTYLE TIP\n\n" +
                "IMPORTANT: Do NOT use markdown symbols like * or #.";

        Content content = new Content.Builder().addText(hyperPrompt).build();
        ListenableFuture<GenerateContentResponse> response = model.generateContent(content);

        Futures.addCallback(response, new FutureCallback<GenerateContentResponse>() {
            @Override
            public void onSuccess(GenerateContentResponse result) {
                String cleanText = result.getText().replace("**", "").replace("###", "").replace("*", "•").trim();
                runOnUiThread(() -> {
                    tvAIDietPlan.setText(cleanText);
                    calorieProgress.setProgress(100);
                });
            }

            @Override
            public void onFailure(@NonNull Throwable t) {
                runOnUiThread(() -> tvAIDietPlan.setText("Error: Check Internet."));
            }
        }, this.getMainExecutor());
    }
}