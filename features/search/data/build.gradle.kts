
plugins {
    alias(libs.plugins.convention.android.library)
    alias(libs.plugins.google.hilt)
}

android {
    namespace = "com.example.data"

}

dependencies {
    listOf(
        ":features:search:domain",":core:data"
    ).forEach {
        implementation(project(it))
    }

    //retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
}