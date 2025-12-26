package com.example.dietconsultantapp;

import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.ai.client.generativeai.GenerativeModel;
import com.google.ai.client.generativeai.java.GenerativeModelFutures;
import com.google.ai.client.generativeai.type.Content;
import com.google.ai.client.generativeai.type.GenerateContentResponse;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.concurrent.Executor;

public class AIResultActivity extends AppCompatActivity {

    private TextView tvAIDietPlan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ai_result);

        tvAIDietPlan = findViewById(R.id.tvAIDietPlan);

        // Intent se data lein
        String name = getIntent().getStringExtra("NAME");
        String goal = getIntent().getStringExtra("GOAL");
        String age = getIntent().getStringExtra("AGE");
        String weight = getIntent().getStringExtra("WEIGHT");

        startAIPlanGeneration(name, goal, age, weight);
    }

    private void startAIPlanGeneration(String name, String goal, String age, String weight) {
        // API Key yahan paste karein
        GenerativeModel gm = new GenerativeModel("gemini-1.5-flash", "AIzaSyBK9f9OdAJPu4aceRj052rrEe-PO2n1QRQ");
        GenerativeModelFutures model = GenerativeModelFutures.from(gm);

        String prompt = "Create a healthy " + goal + " diet plan for " + name +
                ". Age: " + age + ", Weight: " + weight + "kg. Give meal names only.";

        Content content = new Content.Builder().addText(prompt).build();
        ListenableFuture<GenerateContentResponse> response = model.generateContent(content);

        // "this.getMainExecutor()" ka error fix karne ke liye ContextCompat use karein
        Executor executor = ContextCompat.getMainExecutor(this);

        Futures.addCallback(response, new FutureCallback<GenerateContentResponse>() {
            @Override
            public void onSuccess(GenerateContentResponse result) {
                String resultText = result.getText();
                // "runOnUiThread" ka error fix
                AIResultActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvAIDietPlan.setText(resultText);
                    }
                });
            }

            @Override
            public void onFailure(@NonNull Throwable t) {
                AIResultActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvAIDietPlan.setText("AI Error: " + t.getMessage());
                    }
                });
            }
        }, executor);
    }
}