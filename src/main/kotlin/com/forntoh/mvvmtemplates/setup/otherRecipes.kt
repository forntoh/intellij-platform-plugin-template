package com.forntoh.mvvmtemplates.setup

import com.android.tools.idea.wizard.template.*
import com.forntoh.mvvmtemplates.listeners.MyProjectManagerListener.Companion.projectInstance
import com.forntoh.mvvmtemplates.util.Logger
import com.intellij.openapi.roots.ProjectRootManager

val fixManifests
    get() = template {
        revision = 1
        name = "Fix manifests"

        category = Category.Other
        formFactor = FormFactor.Mobile
        screens = listOf(WizardUiContext.MenuEntry, WizardUiContext.FragmentGallery, WizardUiContext.ActivityGallery)

        recipe = { data: TemplateData ->
            fixManifests(data as ProjectTemplateData)
        }
    }

fun RecipeExecutor.fixManifests(
    moduleData: ProjectTemplateData,
) {
    val project = projectInstance ?: return

    val virtualFiles = ProjectRootManager.getInstance(project).contentSourceRoots

    virtualFiles.forEach {
        Logger.log(it.path)
    }
//
//    val virtSrc = virtualFiles.first { it.path.contains("src") }
//    val virtRes = virtualFiles.first { it.path.contains("res") }
//    val directorySrc = PsiManager.getInstance(project).findDirectory(virtSrc)!!
//    val directoryRes = PsiManager.getInstance(project).findDirectory(virtRes)!!
}