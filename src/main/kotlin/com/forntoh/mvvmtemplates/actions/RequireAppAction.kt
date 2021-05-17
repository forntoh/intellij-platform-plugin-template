package com.forntoh.mvvmtemplates.actions

import com.forntoh.mvvmtemplates.recipes.child
import com.forntoh.mvvmtemplates.recipes.packageName
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import javax.swing.JOptionPane

abstract class RequireAppAction : AnAction() {

    protected lateinit var project: Project
    protected lateinit var projectPackage: String
    protected lateinit var javaRoot: VirtualFile
    protected lateinit var resRoot: VirtualFile

    override fun actionPerformed(e: AnActionEvent) {
        project = e.project ?: return

        var targetDirectory = CommonDataKeys.VIRTUAL_FILE.getData(e.dataContext)

        if (targetDirectory != null && targetDirectory.isDirectory.not()) {
            // If the user selected a simulated folder entry (eg "Manifests"), there will be no target directory
            targetDirectory = targetDirectory.parent
        }

        if (targetDirectory == null || targetDirectory.name != "app")
            JOptionPane.showMessageDialog(
                null,
                "Please select the app directory, then retry",
                "Incorrect directory",
                JOptionPane.ERROR_MESSAGE
            ).also { return }

        javaRoot = targetDirectory.child("src/main/java") ?: return
        resRoot = targetDirectory.child("src/main/res") ?: return

        projectPackage = javaRoot.packageName()
        projectPackage = with(projectPackage.split(".")) { subList(0, size - 1).joinToString(".") }

    }
}