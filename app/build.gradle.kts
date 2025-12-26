plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.dietconsultantapp"
    compileSdk = 36 // Tip: SDK 36 abhi preview mein ho sakta hai, 35 stable hai.

    defaultConfig {
        applicationId = "com.example.dietconsultantapp"
        minSdk = 24
        targetSdk = 35
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

    // Gemini SDK ke liye ye buildFeatures zaroori ho sakta hai
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    // Standard Libraries from Version Catalog
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)


    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")


    // Guava is required for ListenableFuture
    implementation("com.google.guava:guava:31.1-android")
    // Callback support
    implementation("androidx.concurrent:concurrent-futures:1.1.0")

    // --- UI LIBRARIES ---
    implementation("com.google.android.material:material:1.11.0")
    implementation("de.hdodenhof:circleimageview:3.1.0")
    implementation("com.github.bumptech.glide:glide:4.16.0")
    implementation(libs.viewpager2)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation("androidx.lifecycle:lifecycle-viewmodel:2.6.1")
}