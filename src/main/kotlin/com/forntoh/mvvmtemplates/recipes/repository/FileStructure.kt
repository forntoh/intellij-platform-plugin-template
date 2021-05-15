package com.forntoh.mvvmtemplates.recipes.repository

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.forntoh.mvvmtemplates.listeners.MyProjectManagerListener.Companion.projectInstance
import com.forntoh.mvvmtemplates.recipes.createDirInSrc
import com.forntoh.mvvmtemplates.recipes.packageName
import com.forntoh.mvvmtemplates.recipes.repository.src.appRepoModule
import com.forntoh.mvvmtemplates.recipes.repository.src.baseRepository
import com.forntoh.mvvmtemplates.recipes.repository.src.repoModule
import com.forntoh.mvvmtemplates.recipes.save
import com.intellij.openapi.vfs.VfsUtil
import com.intellij.psi.PsiManager

fun RecipeExecutor.repositoryFileStructure(
    moduleData: ModuleTemplateData,
    commonModuleName: String,
    webServiceModuleName: String,
) {

    val project = projectInstance ?: return

    // Delete res dir
    VfsUtil.findFileByIoFile(moduleData.resDir, false)?.delete(this)

    // Create root dir
    with(PsiManager.getInstance(project).findDirectory(createDirInSrc(moduleData, ""))!!) {
        val eventPackage = packageName(moduleData)
        baseRepository(
            eventPackage,
            "${moduleData.packageName}.$commonModuleName",
            "${moduleData.packageName}.$webServiceModuleName"
        ).save(this, "BaseRepository.kt")
    }
    // Create di dir
    with(PsiManager.getInstance(project).findDirectory(createDirInSrc(moduleData, "di"))!!) {
        val eventPackage = packageName(moduleData)
        appRepoModule(eventPackage).save(this, "AppRepoModule.kt")
        repoModule(eventPackage).save(this, "RepoModule.kt")
    }
}