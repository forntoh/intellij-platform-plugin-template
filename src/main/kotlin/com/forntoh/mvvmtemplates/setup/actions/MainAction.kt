package com.forntoh.mvvmtemplates.setup.actions

import com.forntoh.mvvmtemplates.recipes.child
import com.forntoh.mvvmtemplates.recipes.common.gradleBuildCommon
import com.forntoh.mvvmtemplates.recipes.database.gradleBuildDatabase
import com.forntoh.mvvmtemplates.recipes.repository.gradleBuildRepo
import com.forntoh.mvvmtemplates.recipes.webservice.gradleBuildWebService
import com.forntoh.mvvmtemplates.setup.ui.button
import com.forntoh.mvvmtemplates.setup.ui.frame
import com.forntoh.mvvmtemplates.setup.ui.textField
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.roots.ProjectRootManager
import com.intellij.openapi.vfs.VfsUtil
import com.intellij.openapi.vfs.VirtualFile
import javax.swing.*

class MainAction : AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return

        val virtualFiles = ProjectRootManager.getInstance(project).contentRoots

        frame("") {

            val base = it.textField("Domain", "com.example")

            it.contentPane.add(JSeparator(SwingConstants.HORIZONTAL))
            it.contentPane.add(Box.createVerticalStrut(20))

            val common = it.textField("Common Module Name", "common")
            val db = it.textField("Database Module Name", "database")
            val repo = it.textField("Repository Module Name", "repository")
            val web = it.textField("Web-service Module Name", "web-service")

            it.button("Refactor") {
                for (folder in virtualFiles) {
                    when (folder.name) {
                        common.text -> {
                            val packageName = "${base.text}.${common.text}".replace('-', '_')
                            folder.createManifest(packageName)
                            folder.generateGradleScript(gradleBuildCommon())
                        }
                        db.text -> {
                            val packageName = "${base.text}.${db.text}".replace('-', '_')
                            folder.createManifest(packageName)
                            folder.generateGradleScript(gradleBuildDatabase(common.text))
                        }
                        repo.text -> {
                            val packageName = "${base.text}.${repo.text}".replace('-', '_')
                            folder.createManifest(packageName)
                            folder.generateGradleScript(gradleBuildRepo(common.text, db.text, web.text))
                        }
                        web.text -> {
                            val packageName = "${base.text}.${web.text}".replace('-', '_')
                            folder.createManifest(packageName)
                            folder.generateGradleScript(gradleBuildWebService(common.text))
                        }
                        else -> continue
                    }
                }
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
            generateManifest(packageName)
        )
    }

    private fun generateManifest(packageName: String): String = """
<manifest xmlns:android="http://schemas.android.com/apk/res/android" package="$packageName">

</manifest>
  """
}

//        var targetDirectory = CommonDataKeys.VIRTUAL_FILE.getData(dataContext)
//        if (targetDirectory != null && targetDirectory.isDirectory.not()) {
//            // If the user selected a simulated folder entry (eg "Manifests"), there will be no target directory
//            targetDirectory = targetDirectory.parent
//        }
//        targetDirectory!!