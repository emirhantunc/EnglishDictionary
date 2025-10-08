
plugins {
    alias(libs.plugins.convention.android.library)
    alias(libs.plugins.google.hilt)
    alias(libs.plugins.google.room)
}

android {
    namespace = "com.example.network"

}

dependencies {

    //retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

}