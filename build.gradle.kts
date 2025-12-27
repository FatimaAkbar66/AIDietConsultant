// Top-level build file
plugins {
    alias(libs.plugins.android.application) apply false
    // YE LINE ADD KAREIN:
    id("com.google.gms.google-services") version "4.4.0" apply false
}