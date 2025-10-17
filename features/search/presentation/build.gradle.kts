
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

    listOf(
        ":features:search:domain", ":core:utils",  ":core:data",":core:ui"
    ).forEach {
        implementation(project(it))
    }
    implementation(libs.androidx.media3.exoplayer)
    implementation(libs.androidx.media3.exoplayer.hls)
}