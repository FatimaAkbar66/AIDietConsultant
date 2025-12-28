plugins {
    alias(libs.plugins.android.application)
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.dietconsultantapp"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.dietconsultantapp"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    }

}

dependencies {
    // Firebase Setup
    implementation(platform("com.google.firebase:firebase-bom:33.1.2")) // Latest BOM use karein
    implementation("com.google.firebase:firebase-auth")

    // Gemini AI
    implementation("com.google.ai.client.generativeai:generativeai:0.9.0")
    implementation("com.google.guava:guava:31.1-android")

    // UI & Others
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation("androidx.concurrent:concurrent-futures:1.1.0")
    implementation("de.hdodenhof:circleimageview:3.1.0")
    implementation(libs.activity)

    implementation("com.google.android.material:material:1.11.0")
}