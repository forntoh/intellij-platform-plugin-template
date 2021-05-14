package com.forntoh.mvvmtemplates.setup

import com.android.tools.idea.npw.module.recipes.generateManifest
import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.impl.activities.common.addAllKotlinDependencies
import com.forntoh.mvvmtemplates.listeners.MyProjectManagerListener.Companion.projectInstance
import com.forntoh.mvvmtemplates.recipes.common.commonFileStructure
import com.forntoh.mvvmtemplates.recipes.common.src.event
import com.forntoh.mvvmtemplates.recipes.common.src.eventBus
import com.forntoh.mvvmtemplates.recipes.createDirInSrc
import com.forntoh.mvvmtemplates.recipes.database.databaseFileStructure
import com.forntoh.mvvmtemplates.recipes.mainPath
import com.forntoh.mvvmtemplates.recipes.packageName
import com.forntoh.mvvmtemplates.recipes.repository.repositoryFileStructure
import com.forntoh.mvvmtemplates.recipes.save
import com.forntoh.mvvmtemplates.recipes.webservice.webServiceFileStructure
import com.intellij.openapi.vfs.VfsUtil
import com.intellij.psi.PsiManager

fun RecipeExecutor.commonModuleSetup(
    moduleData: ModuleTemplateData,
) {
    baseModuleSetup(moduleData)

    addDependency("androidx.room:room-common:\$room_version")

    commonFileStructure(moduleData)

    commonDependencies()
    hiltDependencies()
}

fun RecipeExecutor.databaseModuleSetup(
    moduleData: ModuleTemplateData,
    databaseName: String,
    commonModuleName: String,
) {
    baseModuleSetup(moduleData)

    addModuleDependency("implementation", commonModuleName, moduleData.rootDir)

    databaseFileStructure(moduleData, databaseName, commonModuleName)

    roomDependencies()
    hiltDependencies()
}

fun RecipeExecutor.webServiceModuleSetup(
    moduleData: ModuleTemplateData,
    commonModuleName: String,
    useHttps: Boolean,
    domain: String,
    port: String,
) {
    baseModuleSetup(moduleData)

    addModuleDependency("implementation", commonModuleName, moduleData.rootDir)

    webServiceFileStructure(moduleData, commonModuleName, useHttps, domain, port)

    retrofitDependencies()
    okHttpDependencies()
    hiltDependencies()
}

fun RecipeExecutor.repositoryModuleSetup(
    moduleData: ModuleTemplateData,
    commonModuleName: String,
    databaseModuleName: String,
    webServiceModuleName: String,
) {
    baseModuleSetup(moduleData)

    addModuleDependency("api", commonModuleName, moduleData.rootDir)
    addModuleDependency("api", databaseModuleName, moduleData.rootDir)
    addModuleDependency("api", webServiceModuleName, moduleData.rootDir)

    repositoryFileStructure(moduleData, commonModuleName, webServiceModuleName)

    hiltDependencies()
}

private fun RecipeExecutor.baseModuleSetup(moduleData: ModuleTemplateData) {

    addIncludeToSettings(moduleData.name)

    applyPlugin("com.android.library")
    applyPlugin("kotlin-android")
    applyPlugin("kotlin-kapt")
    applyPlugin("dagger.hilt.android.plugin")

    addAllKotlinDependencies(moduleData)
}


