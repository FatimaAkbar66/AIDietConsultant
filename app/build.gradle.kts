plugins {
    alias(libs.plugins.android.application)
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

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // Google AI (Gemini) SDK - The brain of your app
    implementation("com.google.ai.client.generativeai:generativeai:0.7.0")

    // Material Design (For rounded buttons and cards)
    implementation("com.google.android.material:material:1.11.0")

    // Circular Image View (For profile pics)
    implementation("de.hdodenhof:circleimageview:3.1.0")

    // Glide (To load food images from URLs later)
    implementation("com.github.bumptech.glide:glide:4.16.0")

}