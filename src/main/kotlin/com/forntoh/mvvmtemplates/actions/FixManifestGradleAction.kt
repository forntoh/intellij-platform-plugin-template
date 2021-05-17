package com.forntoh.mvvmtemplates.actions

import com.forntoh.mvvmtemplates.actions.ui.button
import com.forntoh.mvvmtemplates.actions.ui.frame
import com.forntoh.mvvmtemplates.actions.ui.textField
import com.forntoh.mvvmtemplates.recipes.child
import com.forntoh.mvvmtemplates.recipes.common.gradleBuildCommon
import com.forntoh.mvvmtemplates.recipes.database.gradleBuildDatabase
import com.forntoh.mvvmtemplates.recipes.repository.gradleBuildRepo
import com.forntoh.mvvmtemplates.recipes.webservice.gradleBuildWebService
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.roots.ProjectRootManager
import com.intellij.openapi.vfs.VfsUtil
import com.intellij.openapi.vfs.VirtualFile

class FixManifestGradleAction : RequireAppAction() {

    override fun actionPerformed(e: AnActionEvent) {
        super.actionPerformed(e)

        val virtualFiles = ProjectRootManager.getInstance(project).contentRoots

        frame("") {

            val db = it.textField("Database Module Name", "database")
            val repo = it.textField("Repository Module Name", "repository")
            val web = it.textField("Web-service Module Name", "web-service")

            it.button("Refactor") {
                for (folder in virtualFiles) {
                    when (folder.name) {
                        "common" -> {
                            val packageName = "$projectPackage.common"
                            folder.createManifest(packageName)
                            folder.generateGradleScript(gradleBuildCommon())
                        }
                        db.text -> {
                            val packageName = "$projectPackage.${db.text}".replace('-', '_')
                            folder.createManifest(packageName)
                            folder.generateGradleScript(gradleBuildDatabase("common"))
                        }
                        repo.text -> {
                            val packageName = "$projectPackage.${repo.text}".replace('-', '_')
                            folder.createManifest(packageName)
                            folder.generateGradleScript(gradleBuildRepo("common", db.text, web.text))
                        }
                        web.text -> {
                            val packageName = "$projectPackage.${web.text}".replace('-', '_')
                            folder.createManifest(packageName)
                            folder.generateGradleScript(gradleBuildWebService("common"))
                        }
                        else -> continue
                    }
                }
                it.dispose()
            }
        }
    }

    private fun VirtualFile.generateGradleScript(scriptContent: String) {
        VfsUtil.saveText(
            findChild("build.gradle")!!,
            scriptContent
        )
    }

    private fun VirtualFile.createManifest(packageName: String) {
        VfsUtil.saveText(
            child("src/main/AndroidManifest.xml")!!,
            """<?xml version="1.0" encoding="utf-8"?>
<manifest package="$packageName" />
  """
        )
    }
}