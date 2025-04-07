plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt") // for LiveData
}

android {
    namespace = "com.ramotion.fluidslider"
    compileSdk = 35

    defaultConfig {
        minSdk = 16
    }

    /*
    If your library module is a part of a multi-module build that compiles into an APK and
    doesn't generate an AAR, run code shrinking on only the app module that consumes the library.
    cf. https://developer.android.com/studio/projects/android-library#Considerations

    Starting with Android Gradle Plugin 8.4, if an Android library project is minified,
    shrunk program classes will be published for inter-project publishing.
    cf. https://developer.android.com/build/releases/past-releases/agp-8-4-0-release-notes#library-classes-shrunk
     */

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        dataBinding = true
    }
}

dependencies {
}