package com.example.dietconsultantapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.firebase.auth.FirebaseAuth;

public class DashboardActivity extends AppCompatActivity {

    private TextView tvWelcome, tvDayCounter, tvSavedPlanDetails;
    private ProgressBar pbPlanProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Toolbar setup for 3 dots menu
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tvWelcome = findViewById(R.id.tvWelcome);
        tvDayCounter = findViewById(R.id.tvDayCounter);
        tvSavedPlanDetails = findViewById(R.id.tvSavedPlanDetails);
        pbPlanProgress = findViewById(R.id.pbPlanProgress);

        loadDashboardData();
    }

    private void loadDashboardData() {
        SharedPreferences prefs = getSharedPreferences("VioFitPrefs", MODE_PRIVATE);
        String name = prefs.getString("USER_NAME", "User");
        String savedPlan = prefs.getString("saved_diet_plan", "No plan found.");
        long startDate = prefs.getLong("plan_start_date", System.currentTimeMillis());

        // Day Counter Logic
        long diff = System.currentTimeMillis() - startDate;
        long dayCount = (diff / (1000 * 60 * 60 * 24)) + 1;

        tvWelcome.setText("Welcome Back, " + name + "!");
        tvDayCounter.setText("Day " + dayCount + " of 14");
        pbPlanProgress.setMax(14);
        pbPlanProgress.setProgress((int) dayCount);
        tvSavedPlanDetails.setText(savedPlan);
    }

    // --- MENU HANDLING (3 DOTS) ---
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dashboard_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_new_plan) {
            // Redirect to SetupActivity (Fragments)
            startActivity(new Intent(DashboardActivity.this, SetupActivity.class));
            return true;
        } else if (id == R.id.menu_logout) {
            logoutUser();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logoutUser() {
        FirebaseAuth.getInstance().signOut();
        // Clear saved plan so splash redirects to Login next time
        getSharedPreferences("VioFitPrefs", MODE_PRIVATE).edit().clear().apply();

        Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}