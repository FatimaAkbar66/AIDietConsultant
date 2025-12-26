package com.example.dietconsultantapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText; // Ye import lazmi ho
import android.widget.Spinner;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class FragmentPersonal extends Fragment {

    // 1. Inhe yahan (Class Level) declare karein, taake ye pure fragment mein kaam karein
    private EditText etName, etAge, etHeight, etWeight;
    private UserViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_personal, container, false);

        // 2. ViewModel connect karein
        viewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);

        // 3. Views ko assign karein (Yahan 'EditText' dobara mat likhen, sirf assign karein)
        etName = v.findViewById(R.id.etName);
        etAge = v.findViewById(R.id.etAge);
        etHeight = v.findViewById(R.id.etHeight);
        etWeight = v.findViewById(R.id.etWeight);

        return v;
    }

    // 4. Jab user swipe karega, ye data save ho jayega
    @Override
    public void onPause() {
        super.onPause();
        if (viewModel != null) {
            viewModel.name = etName.getText().toString();
            viewModel.age = etAge.getText().toString();
            viewModel.height = etHeight.getText().toString();
            viewModel.weight = etWeight.getText().toString();
        }
    }
}