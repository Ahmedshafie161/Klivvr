import org.gradle.kotlin.dsl.android

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.klivvr"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.klivvr"
        minSdk = 24
        targetSdk = 34
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

    implementation(project(":core"))
    implementation(project(":features:search"))
    implementation(project(":components:city"))

    implementation(libs.core.splashscreen)

    // Hilt
    api(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    api(libs.androidx.hilt.work)
    kapt(libs.androidx.hilt.compiler)
    api(libs.androidx.hilt.navigation.compose)
    api(libs.work.manager)
    kapt(libs.hilt.android.compiler)



}