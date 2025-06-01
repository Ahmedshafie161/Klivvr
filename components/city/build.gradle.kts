import org.gradle.kotlin.dsl.android

plugins {
    id ("com.android.library")
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.klivvr.city"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(project(":core"))


    // Hilt
    api(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    api(libs.androidx.hilt.work)
    kapt(libs.androidx.hilt.compiler)
    api(libs.androidx.hilt.navigation.compose)
    api(libs.work.manager)
    kapt(libs.hilt.android.compiler)

    implementation("com.google.code.gson:gson:2.10.1")

    // Kotlin Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

}