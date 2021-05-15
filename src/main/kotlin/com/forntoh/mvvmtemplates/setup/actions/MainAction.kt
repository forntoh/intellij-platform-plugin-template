package com.forntoh.mvvmtemplates.setup.actions

import com.android.tools.idea.npw.module.recipes.generateManifest
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.roots.ProjectRootManager
import com.intellij.openapi.vfs.VfsUtil
import com.intellij.openapi.vfs.VirtualFile
import java.awt.Dimension
import javax.swing.*
import javax.swing.event.DocumentEvent
import javax.swing.event.DocumentListener

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
                            createManifest(folder, packageName)
                        }
                        db.text -> {
                            val packageName = "${base.text}.${db.text}".replace('-', '_')
                            createManifest(folder, packageName)
                        }
                        repo.text -> {
                            val packageName = "${base.text}.${repo.text}".replace('-', '_')
                            createManifest(folder, packageName)
                        }
                        web.text -> {
                            val packageName = "${base.text}.${web.text}".replace('-', '_')
                            createManifest(folder, packageName)
                        }
                        else -> continue
                    }
                }
            }
        }
    }

    private fun createManifest(folder: VirtualFile, packageName: String) {
        VfsUtil.saveText(
            folder.child("src/main/AndroidManifest.xml")!!,
            generateManifest(packageName)
        )
    }
}

fun frame(title: String, body: (JFrame) -> Unit) = with(JFrame(title)) {

    size = Dimension(512, 512)

    contentPane = JPanel().apply { border = BorderFactory.createEmptyBorder(32, 32, 32, 32) }
    contentPane.layout = BoxLayout(contentPane, BoxLayout.Y_AXIS)

    body(this)

    pack()
    setLocationRelativeTo(null)

    isVisible = true
}

fun JFrame.button(text: String, action: () -> Unit) = JButton(text).apply {
    addActionListener {
        action()
    }
    isEnabled = true
    contentPane.add(this)
}

fun JFrame.textField(title: String, defaultValue: String = "") = JTextField(defaultValue, 54).apply {
    alignmentX = 0f
    val label = JLabel(title)
    label.alignmentX = 0f
    label.labelFor = this
    label.border = BorderFactory.createEmptyBorder(0, 4, 6, 0)
    contentPane.add(label)
    contentPane.add(this)
    contentPane.add(Box.createVerticalStrut(20))
}

fun VirtualFile?.child(path: String): VirtualFile? {
    var file = this
    path.split("/").forEach { file = file?.findChild(it) }
    return file
}

//        var targetDirectory = CommonDataKeys.VIRTUAL_FILE.getData(dataContext)
//        if (targetDirectory != null && targetDirectory.isDirectory.not()) {
//            // If the user selected a simulated folder entry (eg "Manifests"), there will be no target directory
//            targetDirectory = targetDirectory.parent
//        }
//        targetDirectory!!