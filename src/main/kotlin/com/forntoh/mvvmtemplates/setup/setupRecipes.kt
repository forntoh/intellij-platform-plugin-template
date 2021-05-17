package com.forntoh.mvvmtemplates.setup

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.impl.activities.common.addAllKotlinDependencies
import com.forntoh.mvvmtemplates.recipes.common.commonFileStructure
import com.forntoh.mvvmtemplates.recipes.database.databaseFileStructure
import com.forntoh.mvvmtemplates.recipes.repository.repositoryFileStructure
import com.forntoh.mvvmtemplates.recipes.webservice.webServiceFileStructure

fun RecipeExecutor.commonModuleSetup(
    moduleData: ModuleTemplateData,
) {
    baseModuleSetup(moduleData)
    commonFileStructure(moduleData)
}

fun RecipeExecutor.databaseModuleSetup(
    moduleData: ModuleTemplateData,
    databaseName: String,
    commonModuleName: String,
) {
    baseModuleSetup(moduleData)
    databaseFileStructure(moduleData, databaseName, commonModuleName)
}

fun RecipeExecutor.webServiceModuleSetup(
    moduleData: ModuleTemplateData,
    commonModuleName: String,
    useHttps: Boolean,
    domain: String,
    port: String,
) {
    baseModuleSetup(moduleData)
    webServiceFileStructure(moduleData, commonModuleName, useHttps, domain, port)
}

fun RecipeExecutor.repositoryModuleSetup(
    moduleData: ModuleTemplateData,
    commonModuleName: String,
    webServiceModuleName: String,
) {
    baseModuleSetup(moduleData)
    repositoryFileStructure(moduleData, commonModuleName, webServiceModuleName)
}

private fun RecipeExecutor.baseModuleSetup(moduleData: ModuleTemplateData) {
    addIncludeToSettings(moduleData.name)
    addAllKotlinDependencies(moduleData)
}


