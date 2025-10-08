import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidComposeConventionPlugin : Plugin<Project> {

    override fun apply(target: Project): Unit = with(target) {
        val libs = getLibs()
        with(pluginManager) {
            apply(libs.findPlugin("kotlin.compose").get().get().pluginId)
        }

        dependencies {
            add("implementation", libs.findLibrary("androidx.ui").get())
            add("implementation", libs.findLibrary("androidx.ui.graphics").get())
            add("implementation", libs.findLibrary("androidx.ui.tooling.preview").get())
            add("implementation", libs.findLibrary("androidx.material3").get())
            add("androidTestImplementation", libs.findLibrary("androidx.ui.test.junit4").get())
            add("androidTestImplementation", libs.findLibrary("androidx.compose.bom").get())
            add("implementation", platform(libs.findLibrary("androidx.compose.bom").get()))
            add("debugImplementation", libs.findLibrary("androidx.ui.tooling").get())
            add("debugImplementation", libs.findLibrary("androidx.ui.test.manifest").get())

        }
    }

}