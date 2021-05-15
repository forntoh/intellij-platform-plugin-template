package com.forntoh.mvvmtemplates.recipes.database

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.forntoh.mvvmtemplates.listeners.MyProjectManagerListener.Companion.projectInstance
import com.forntoh.mvvmtemplates.recipes.createDirInSrc
import com.forntoh.mvvmtemplates.recipes.database.src.baseDao
import com.forntoh.mvvmtemplates.recipes.database.src.converters
import com.forntoh.mvvmtemplates.recipes.database.src.database
import com.forntoh.mvvmtemplates.recipes.database.src.module
import com.forntoh.mvvmtemplates.recipes.packageName
import com.forntoh.mvvmtemplates.recipes.save
import com.intellij.openapi.vfs.VfsUtil
import com.intellij.psi.PsiManager

fun RecipeExecutor.databaseFileStructure(
    moduleData: ModuleTemplateData,
    databaseName: String,
    commonModuleName: String
) {

    val project = projectInstance ?: return

    // Delete res dir
    VfsUtil.findFileByIoFile(moduleData.resDir, false)?.delete(this)

    // Create helpers dir
    createDirInSrc(moduleData, "helpers")

    // Create views dir
    createDirInSrc(moduleData, "views")

    // Create root file
    with(PsiManager.getInstance(project).findDirectory(createDirInSrc(moduleData, ""))!!) {
        database(packageName(moduleData, true), databaseName).save(this, "AppDatabase.kt")
        module(packageName(moduleData), "${moduleData.packageName}.$commonModuleName").save(this, "DatabaseModule.kt")
    }

    // Create converters dir
    with(PsiManager.getInstance(project).findDirectory(createDirInSrc(moduleData, "converters"))!!) {
        converters(packageName(moduleData)).save(this, "Converters.kt")
    }

    // Create DAOs dir
    with(PsiManager.getInstance(project).findDirectory(createDirInSrc(moduleData, "daos"))!!) {
        baseDao(packageName(moduleData)).save(this, "BaseDao.kt")
    }
}