package com.example.dietconsultantapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class FragmentGoals extends Fragment {

    private Spinner spinnerGoal, spinnerTimeline;
    private Button btnFinish;
    private UserViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_goals, container, false);

        viewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);

        spinnerGoal = v.findViewById(R.id.spinnerGoal);
        spinnerTimeline = v.findViewById(R.id.spinnerTimeline);
        btnFinish = v.findViewById(R.id.btnFinish);

        setupSpinner(spinnerGoal, R.array.goal_array);
        // Agar timeline_array red ho toh strings.xml mein add karein
        setupSpinner(spinnerTimeline, R.array.timeline_array);

        btnFinish.setOnClickListener(view -> {
            // Button dabate hi pehle ViewModel mein data save karein
            saveData();

            // Phir AI screen par chalein
            Intent intent = new Intent(getActivity(), AIResultActivity.class);
            startActivity(intent);
        });

        return v;
    }

    private void saveData() {
        if (viewModel != null) {
            viewModel.goal = spinnerGoal.getSelectedItem().toString();
            viewModel.timeline = spinnerTimeline.getSelectedItem().toString();
        }
    }

    private void setupSpinner(Spinner spinner, int arrayId) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                arrayId, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}