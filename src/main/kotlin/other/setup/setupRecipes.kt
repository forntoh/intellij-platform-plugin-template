package other.setup

import com.android.tools.idea.npw.module.recipes.androidModule.generateAndroidModule
import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.impl.activities.common.addAllKotlinDependencies
import com.forntoh.androidprojecttemplates.listeners.MyProjectManagerListener.Companion.projectInstance

private fun RecipeExecutor.baseModuleSetup(moduleData: ModuleTemplateData) {

    val project = projectInstance ?: return

    addIncludeToSettings(moduleData.name)

    generateAndroidModule(moduleData, project.name, false, "")

    applyPlugin("com.android.library")
    applyPlugin("kotlin-android")
    applyPlugin("kotlin-kapt")
    applyPlugin("dagger.hilt.android.plugin")

    addAllKotlinDependencies(moduleData)
}

fun RecipeExecutor.commonModuleSetup(
        moduleData: ModuleTemplateData,
        useRoom: Boolean = true,
) {
    baseModuleSetup(moduleData)

    if (useRoom) addDependency("androidx.room:room-common:\$room_version")

    commonDependencies()
    hiltDependencies()
}

fun RecipeExecutor.databaseModuleSetup(
        moduleData: ModuleTemplateData,
        useRoom: Boolean = true,
) {
    baseModuleSetup(moduleData)

    addModuleDependency("implementation", "common", moduleData.rootDir)

    if (useRoom) roomDependencies()
    hiltDependencies()
}

fun RecipeExecutor.webServiceModuleSetup(
        moduleData: ModuleTemplateData,
) {
    baseModuleSetup(moduleData)

    addModuleDependency("implementation", "common", moduleData.rootDir)

    retrofitDependencies()
    okHttpDependencies()
    hiltDependencies()
}

fun RecipeExecutor.repositoryModuleSetup(
        moduleData: ModuleTemplateData,
) {
    baseModuleSetup(moduleData)

    addModuleDependency("api", "common", moduleData.rootDir)
    addModuleDependency("api", "database", moduleData.rootDir)
    addModuleDependency("api", "web-service", moduleData.rootDir)

    hiltDependencies()
}



