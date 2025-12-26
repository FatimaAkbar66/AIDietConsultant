package com.example.dietconsultantapp; // Apna sahi package name check kar lein

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class SetupAdapter extends FragmentStateAdapter {

    public SetupAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // Position ke hisab se sahi fragment return karna
        switch (position) {
            case 0:
                return new FragmentPersonal();   // Pehli screen: Name, Age, etc.
            case 1:
                return new FragmentLifestyle();  // Dusri screen: Activity, Sleep, Stress
            case 2:
                return new FragmentGoals();      // Teesri screen: Weight Goal & Timeline
            default:
                return new FragmentPersonal();
        }
    }

    @Override
    public int getItemCount() {
        // Hamari total 3 slides hain
        return 3;
    }
}