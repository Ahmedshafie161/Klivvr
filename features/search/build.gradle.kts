import org.gradle.kotlin.dsl.android

plugins {
    id ("com.android.library")

    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.klivvr.search"
    compileSdk = 34

    defaultConfig {
        minSdk = 21

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
    implementation(project(":components:city"))

    // Hilt
    api(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    api(libs.androidx.hilt.work)
    kapt(libs.androidx.hilt.compiler)
    api(libs.androidx.hilt.navigation.compose)
    api(libs.work.manager)
    kapt(libs.hilt.android.compiler)

    // Unit test dependencies
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    testImplementation (libs.kotlinx.coroutines.test)
    testImplementation (libs.turbine)
    testImplementation (libs.mockk)
    testImplementation (libs.junit)
    // Mockito
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.kotlin)
}