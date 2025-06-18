plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    id("kotlin-kapt") // For annotation processing, needed by Hilt
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "dev.korryr.farmbrige"
    compileSdk = 35

    defaultConfig {
        applicationId = "dev.korryr.farmbrige"
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
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.runtime.android)
    implementation(libs.androidx.navigation.compose.android)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Hilt
    implementation("com.google.dagger:hilt-android:2.56.2") // Use latest version
    kapt("com.google.dagger:hilt-android-compiler:2.56.2") // Annotation processor
    kapt("androidx.hilt:hilt-compiler:1.2.0") // Optional: for Hilt Compose integration

    implementation ("androidx.hilt:hilt-navigation-compose:1.2.0")
    // Firebase (if using)
    implementation(platform("com.google.firebase:firebase-bom:33.15.0")) // Use latest BOM
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation("com.google.firebase:firebase-firestore-ktx")

    // Coroutines for Firebase Tasks (important for suspending Firebase calls)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.10.1")
}