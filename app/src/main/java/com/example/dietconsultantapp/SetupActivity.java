package com.example.dietconsultantapp;

import android.os.Bundle;
import android.view.View; // Added for Dots
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

public class SetupActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private SetupAdapter adapter;

    // Dots declare kiye
    private View dot1, dot2, dot3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        // Views ko initialize kiya
        viewPager = findViewById(R.id.viewPagerSetup);
        dot1 = findViewById(R.id.dot1);
        dot2 = findViewById(R.id.dot2);
        dot3 = findViewById(R.id.dot3);

        adapter = new SetupAdapter(this);
        viewPager.setAdapter(adapter);

        // --- DOTS LOGIC START ---
        // Ye listener check karega ke user kaunse page par hai
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                updateDots(position);
            }
        });
        // --- DOTS LOGIC END ---
    }

    // Method jo dots ka color change karega
    private void updateDots(int position) {
        // Sab ko pehle inactive (light) kar do
        dot1.setBackgroundResource(R.drawable.dot_inactive);
        dot2.setBackgroundResource(R.drawable.dot_inactive);
        dot3.setBackgroundResource(R.drawable.dot_inactive);

        // Jo page active hai, uske dot ko dark kar do
        switch (position) {
            case 0:
                dot1.setBackgroundResource(R.drawable.dot_active);
                break;
            case 1:
                dot2.setBackgroundResource(R.drawable.dot_active);
                break;
            case 2:
                dot3.setBackgroundResource(R.drawable.dot_active);
                break;
        }
    }
}