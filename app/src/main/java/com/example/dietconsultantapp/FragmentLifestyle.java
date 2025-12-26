package com.example.dietconsultantapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class FragmentLifestyle extends Fragment {

    // 1. Class level declaration
    private Spinner spinnerActivity, spinnerSleep, spinnerStress;
    private UserViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_lifestyle, container, false);

        // 2. ViewModel aur Views connect karein
        viewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);

        spinnerActivity = v.findViewById(R.id.spinnerActivity);
        spinnerSleep = v.findViewById(R.id.spinnerSleep);
        spinnerStress = v.findViewById(R.id.spinnerStress);

        // Spinner Setup (Jo humne pehle kiya tha)
        setupSpinner(spinnerActivity, R.array.activity_array);
        setupSpinner(spinnerSleep, R.array.sleep_array);
        setupSpinner(spinnerStress, R.array.stress_array);

        return v;
    }

    // 3. Swipe karte waqt data ViewModel mein save karein
    @Override
    public void onPause() {
        super.onPause();
        if (viewModel != null) {
            viewModel.activity = spinnerActivity.getSelectedItem().toString();
            viewModel.sleep = spinnerSleep.getSelectedItem().toString();
            viewModel.stress = spinnerStress.getSelectedItem().toString();
        }
    }

    private void setupSpinner(Spinner spinner, int arrayId) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                arrayId, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}