import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidRoomConventionPlugin : Plugin<Project>{
    override fun apply(target: Project): Unit = with(target) {
        val libs = getLibs()
        pluginManager.apply("com.google.devtools.ksp")
        dependencies.apply {
            add("ksp", libs.findLibrary("androidx.room.compiler").get())
            add("testImplementation", libs.findLibrary("androidx.room.testing").get())
            add("implementation", libs.findLibrary("androidx.room.runtime").get())
            add("implementation", libs.findLibrary("androidx.room.ktx").get())
        }
    }
}