plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)

    alias(libs.plugins.android.legacy.kapt) // for data binding in Kotlin
}

android {
    namespace = "com.scaredeer.fluidslider"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.scaredeer.fluidslider"
        minSdk = 23
        targetSdk = 37
        versionCode = 1
        versionName = "20260830"
    }

    buildTypes {
        release {
            optimization {
                enable = true // Enables code and resource optimizations.
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
        dataBinding = true
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)

    implementation(project(":fluid-slider"))
}