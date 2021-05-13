package com.forntoh.mvvmtemplates.setup

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.impl.activities.common.addAllKotlinDependencies

private fun RecipeExecutor.baseModuleSetup(moduleData: ModuleTemplateData, moduleName: String) {

    val mModule = moduleData.copy(name = moduleName)

    addIncludeToSettings(moduleName)

    applyPlugin("com.android.library")
    applyPlugin("kotlin-android")
    applyPlugin("kotlin-kapt")
    applyPlugin("dagger.hilt.android.plugin")

    addAllKotlinDependencies(mModule)
}

fun RecipeExecutor.commonModuleSetup(
        moduleData: ModuleTemplateData,
        moduleName: String,
        useRoom: Boolean = true,
) {
    baseModuleSetup(moduleData, moduleName)

    if (useRoom) addDependency("androidx.room:room-common:\$room_version")

    commonDependencies()
    hiltDependencies()
}

fun RecipeExecutor.databaseModuleSetup(
        moduleData: ModuleTemplateData,
        moduleName: String,
        useRoom: Boolean = true,
) {
    baseModuleSetup(moduleData, moduleName)

    addModuleDependency("implementation", "common", moduleData.rootDir)

    if (useRoom) roomDependencies()
    hiltDependencies()
}

fun RecipeExecutor.webServiceModuleSetup(
        moduleData: ModuleTemplateData,
        moduleName: String,
) {
    baseModuleSetup(moduleData, moduleName)

    addModuleDependency("implementation", "common", moduleData.rootDir)

    retrofitDependencies()
    okHttpDependencies()
    hiltDependencies()
}

fun RecipeExecutor.repositoryModuleSetup(
        moduleData: ModuleTemplateData,
        moduleName: String,
) {
    baseModuleSetup(moduleData, moduleName)

    addModuleDependency("api", "common", moduleData.rootDir)
    addModuleDependency("api", "database", moduleData.rootDir)
    addModuleDependency("api", "web-service", moduleData.rootDir)

    hiltDependencies()
}



