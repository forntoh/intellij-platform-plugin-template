package com.forntoh.mvvmtemplates.recipes.common

import com.android.tools.idea.npw.module.recipes.generateManifest
import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.forntoh.mvvmtemplates.listeners.MyProjectManagerListener.Companion.projectInstance
import com.forntoh.mvvmtemplates.recipes.common.src.event
import com.forntoh.mvvmtemplates.recipes.common.src.eventBus
import com.forntoh.mvvmtemplates.recipes.common.src.preferenceRepository
import com.forntoh.mvvmtemplates.recipes.createDirInSrc
import com.forntoh.mvvmtemplates.recipes.packageName
import com.forntoh.mvvmtemplates.recipes.save
import com.intellij.openapi.vfs.VfsUtil
import com.intellij.psi.PsiManager

fun RecipeExecutor.commonFileStructure(moduleData: ModuleTemplateData) {

    val project = projectInstance ?: return

    // Delete res dir
    VfsUtil.findFileByIoFile(moduleData.resDir, false)?.delete(this)

    // Create entities dir
    createDirInSrc(moduleData, "entities")

    // Create event dir
    with(PsiManager.getInstance(project).findDirectory(createDirInSrc(moduleData, "event"))!!) {
        val eventPackage = packageName(moduleData)
        event(eventPackage).save(this, "Event.kt")
        eventBus(eventPackage).save(this, "EventBus.kt")
    }

    // Create pref dir
    with(PsiManager.getInstance(project).findDirectory(createDirInSrc(moduleData, "pref"))!!) {
        preferenceRepository(packageName(moduleData)).save(this, "PreferenceRepository.kt")
    }

    // Update Gradle
//    VfsUtil.saveText(
//        VfsUtil.findFileByIoFile(moduleData.rootDir, false)?.findChild("build.gradle")!!,
//        gradleBuildCommon()
//    )
}