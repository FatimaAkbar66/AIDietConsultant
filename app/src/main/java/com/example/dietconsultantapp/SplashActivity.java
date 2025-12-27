package com.example.dietconsultantapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import com.google.firebase.auth.FirebaseAuth; // Firebase import karein

public class SplashActivity extends AppCompatActivity {

    private FirebaseAuth mAuth; // Firebase ka object

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Firebase initialize karein
        mAuth = FirebaseAuth.getInstance();

        AppCompatButton btnGetStarted = findViewById(R.id.btnGetStarted);

        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check karein kya user login hai?
                if (mAuth.getCurrentUser() != null) {
                    // Agar user login hai, toh direct SetupActivity (Fragments) par bhej dein
                    Intent intent = new Intent(SplashActivity.this, SetupActivity.class);
                    startActivity(intent);
                } else {
                    // Agar login nahi hai, toh pehle LoginActivity par bhej dein
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                finish(); // Splash activity ko khatam kar dein
            }
        });
    }
}