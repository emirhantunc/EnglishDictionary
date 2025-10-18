plugins {
    alias(libs.plugins.convention.android.library)
    alias(libs.plugins.convention.compose)
    alias(libs.plugins.google.hilt)
}

android {
    namespace = "com.example.presentation"
    buildFeatures {
        compose = true
    }
}

dependencies {
    listOf(":features:createfolder:domain",":core:ui").forEach {
        implementation(project(it))
    }
}