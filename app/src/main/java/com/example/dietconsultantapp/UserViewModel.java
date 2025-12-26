package com.example.dietconsultantapp;

import androidx.lifecycle.ViewModel;

public class UserViewModel extends ViewModel {

    // Personal Info
    public String name = "";
    public String age = "";
    public String height = "";
    public String weight = "";
    public String gender = "";

    // Lifestyle Info
    public String activity = "";
    public String sleep = "";
    public String stress = "";

    // Goals Info
    public String goal = "";
    public String timeline = "";
}