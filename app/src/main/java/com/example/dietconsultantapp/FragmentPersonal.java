package com.example.dietconsultantapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter; // Spinner ke liye lazmi hai
import android.widget.EditText;
import android.widget.Spinner;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class FragmentPersonal extends Fragment {

    private EditText etName, etAge, etHeight, etWeight;
    private Spinner spinnerGender; // Spinner declare kiya
    private UserViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_personal, container, false);

        viewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);

        // Views assign kiye
        etName = v.findViewById(R.id.etName);
        etAge = v.findViewById(R.id.etAge);
        etHeight = v.findViewById(R.id.etHeight);
        etWeight = v.findViewById(R.id.etWeight);
        spinnerGender = v.findViewById(R.id.spinnerGender); // Spinner connect kiya

        // --- SPINNER ADAPTER START ---
        // Ye code strings.xml se gender ki list uthaye ga
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(requireContext(),
                R.array.gender_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGender.setAdapter(adapter);
        // --- SPINNER ADAPTER END ---

        return v;
    }

    @Override
    public void onPause() {
        super.onPause();
        if (viewModel != null) {
            viewModel.name = etName.getText().toString();
            viewModel.age = etAge.getText().toString();
            viewModel.height = etHeight.getText().toString();
            viewModel.weight = etWeight.getText().toString();

            // Gender ko ViewModel mein save karne ke liye
            if (spinnerGender.getSelectedItem() != null) {
                viewModel.gender = spinnerGender.getSelectedItem().toString();
            }
        }
    }
}