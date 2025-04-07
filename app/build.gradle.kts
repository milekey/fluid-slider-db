plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt") // for data binding in Kotlin
}

android {
    namespace = "com.scaredeer.fluidslider"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.scaredeer.fluidslider"
        minSdk = 22
        targetSdk = 35
        versionCode = 1
        versionName = "1.0.20250407"
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
        dataBinding = true
    }
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("androidx.constraintlayout:constraintlayout:2.2.1")

    implementation(project(":fluid-slider"))
}