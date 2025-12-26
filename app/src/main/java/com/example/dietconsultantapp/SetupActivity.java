package com.example.dietconsultantapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

public class SetupActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private SetupAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup); // Isme ViewPager2 hona chahiye

        viewPager = findViewById(R.id.viewPagerSetup);
        adapter = new SetupAdapter(this);
        viewPager.setAdapter(adapter);

        // Agar aap chahen ke user swipe na kar sakay balkay sirf button se agy jaye:
        // viewPager.setUserInputEnabled(false);
    }
}