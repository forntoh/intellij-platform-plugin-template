package com.forntoh.mvvmtemplates.recipes

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.intellij.lang.xml.XMLLanguage
import com.intellij.openapi.fileTypes.PlainTextLanguage
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VfsUtil
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiDirectory
import com.intellij.psi.PsiFileFactory
import com.intellij.psi.PsiManager
import org.jetbrains.kotlin.idea.KotlinLanguage

fun PsiDirectory.packageName(moduleData: ModuleTemplateData, isRoot: Boolean = false) =
    "${moduleData.packageName}${if (!isRoot) ".${this.name}" else ""}"

fun createDirInSrc(moduleData: ModuleTemplateData, dir: String): VirtualFile =
    VfsUtil.createDirectories("${srcPath(moduleData)}${if (dir.isEmpty()) "" else "/$dir"}")

fun mainPath(moduleData: ModuleTemplateData) =
    "${moduleData.rootDir.path}/src/main/"

fun srcPath(moduleData: ModuleTemplateData) =
    "${moduleData.rootDir.path}/src/main/java/${moduleData.packageName.replace('.', '/')}"

fun VirtualFile?.child(path: String): VirtualFile? {
    var file = this
    path.split("/", ".").forEach { file = file?.findChild(it) }
    return file
}

fun VirtualFile?.packageName(): String {
    var packageName = ""
    var child = this

    while (run { child = child?.children?.firstOrNull(); child } != null)
        packageName += "${child?.name}."

    return packageName.substring(0, packageName.length - 1)
}

fun String.save(destDir: PsiDirectory, fileName: String) {
    try {
        val psiFile = PsiFileFactory
            .getInstance(destDir.project)
            .createFileFromText(
                fileName, when (fileName.split(".").last()) {
                    "kt" -> KotlinLanguage.INSTANCE
                    "xml" -> XMLLanguage.INSTANCE
                    else -> PlainTextLanguage.INSTANCE
                }, this
            )
        destDir.add(psiFile)
    } catch (exc: Exception) {
        exc.printStackTrace()
    }
}

fun VirtualFile.asDir(project: Project) = PsiManager.getInstance(project).findDirectory(this)
