package com.example.dietconsultantapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(() -> {
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            SharedPreferences prefs = getSharedPreferences("VioFitPrefs", MODE_PRIVATE);
            boolean hasPlan = prefs.contains("saved_diet_plan");

            if (mAuth.getCurrentUser() != null) {
                if (hasPlan) {
                    // Seedha Dashboard agar plan pehle se hai
                    startActivity(new Intent(SplashActivity.this, DashboardActivity.class));
                } else {
                    // Main Form agar login hai magar plan nahi banaya
                    startActivity(new Intent(SplashActivity.this, SetupActivity.class));
                }
            } else {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            }
            finish();
        }, 2500);
    }
}