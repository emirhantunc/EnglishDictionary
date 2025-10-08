import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidHiltConventionPlugin : Plugin<Project> {

    override fun apply(target: Project): Unit = with(target) {
        val libs = getLibs()
        with(pluginManager) {
            apply(libs.findPlugin("google.dagger.hilt").get().get().pluginId)
            apply("com.google.devtools.ksp")
        }
        dependencies.apply {
            add("ksp", libs.findLibrary("hilt.compiler").get())
            add("implementation", libs.findLibrary("hilt.android").get())
            add("implementation", libs.findLibrary("androidx.hilt.navigation.compose").get())
        }
    }
}