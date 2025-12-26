package com.example.dietconsultantapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Button ko find karein (Check karein XML mein ID 'btnGetStarted' hi hai na?)
        AppCompatButton btnGetStarted = findViewById(R.id.btnGetStarted);

        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Splash se SetupActivity (ViewPager wali screen) par jana
                Intent intent = new Intent(SplashActivity.this, SetupActivity.class);
                startActivity(intent);
                finish(); // Taake back dabane par wapis splash na aaye
            }
        });
    }
}