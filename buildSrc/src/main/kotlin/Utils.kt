import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler

internal fun DependencyHandler.implementation(depName: Dependency) {
    add("implementation", depName)
}
internal fun DependencyHandler.implementation(depName: String) {
    add("implementation", depName)
}
internal fun DependencyHandler.testImplementation(depName: String) {
    add("testImplementation", depName)
}
internal fun DependencyHandler.androidTestImplementation(depName: String) {
    add("androidTestImplementation", depName)
}
internal fun DependencyHandler.androidTestImplementation(depName: Dependency) {
    add("androidTestImplementation", depName)
}
internal fun DependencyHandler.debugImplementation(depName: String) {
    add("debugImplementation", depName)
}
internal fun DependencyHandler.kapt(depName: String) {
    add("kapt", depName)
}

internal fun DependencyHandler.ksp(depName: String) {
    add("ksp", depName)
}
internal fun DependencyHandler.compileOnly(depName: String) {
    add("compileOnly", depName)
}
internal fun DependencyHandler.api(depName: String) {
    add("api", depName)
}
internal fun DependencyHandler.desugaring(depName: String) {
    add("coreLibraryDesugaring", depName)
}