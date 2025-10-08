plugins {
    `kotlin-dsl`
}


dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}
gradlePlugin {
    plugins {
        register("room") {
            id = libs.plugins.google.room.get().pluginId
            implementationClass = "AndroidRoomConventionPlugin"
        }
        register("hilt") {
            id = libs.plugins.google.hilt.get().pluginId
            implementationClass = "AndroidHiltConventionPlugin"
        }
        register("android") {
            id = libs.plugins.convention.android.library.get().pluginId
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("compose") {
            id = libs.plugins.convention.compose.get().pluginId
            implementationClass = "AndroidComposeConventionPlugin"
        }
    }
}