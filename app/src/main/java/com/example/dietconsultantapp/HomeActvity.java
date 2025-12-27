package com.example.dietconsultantapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button btnStartDiet = findViewById(R.id.btnStartDiet);
        Button btnLogout = findViewById(R.id.btnLogout);

        // Naya diet plan shuru karne ke liye SetupActivity (Fragments) par bhej dega
        btnStartDiet.setOnClickListener(v -> {
            startActivity(new Intent(HomeActivity.this, SetupActivity.class));
        });

        // Logout Logic
        btnLogout.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut(); // Firebase se logout
            Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }
}