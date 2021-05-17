package com.forntoh.mvvmtemplates.actions

import com.forntoh.mvvmtemplates.recipes.app.*
import com.forntoh.mvvmtemplates.recipes.child
import com.forntoh.mvvmtemplates.recipes.packageName
import com.forntoh.mvvmtemplates.recipes.save
import com.forntoh.mvvmtemplates.recipes.saveText
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.vfs.VfsUtil
import com.intellij.psi.PsiManager

class CreateAppStructure : RequireAppAction() {

    override fun actionPerformed(e: AnActionEvent) {
        super.actionPerformed(e)

        val rootPackage = javaRoot.packageName()
        val commonPackage = "$project.common"

        val appRoot = javaRoot.child(rootPackage) ?: return

        // root
        PsiManager.getInstance(project).findDirectory(appRoot)?.let {
            app(rootPackage).save(it, "App.kt")
        }

        // di
        PsiManager.getInstance(project).findDirectory(VfsUtil.createDirectories("${appRoot.path}/di"))?.let {
            appModule("$rootPackage.di", commonPackage).save(it, "AppModule.kt")
        }

        // internal
        VfsUtil.createDirectories("${appRoot.path}/internal/data")
        PsiManager.getInstance(project).findDirectory(VfsUtil.createDirectories("${appRoot.path}/internal/view"))?.let {
            insetDecoration("$rootPackage.internal.view").save(it, "InsetDecoration.kt")
        }

        // utils
        PsiManager.getInstance(project).findDirectory(VfsUtil.createDirectories("${appRoot.path}/utils"))?.let {
            val pkg = "$rootPackage.utils"
            coilHelper(pkg).save(it, "CoilHelper.kt")
            contextExtensions(pkg).save(it, "ContextExtensions.kt")
            customViewBinding(pkg).save(it, "CustomViewBinding.kt")
            displayMetricHelper(pkg).save(it, "DisplayMetricHelper.kt")
            viewHelper(pkg).save(it, "ViewHelper.kt")
            windowHelper(pkg).save(it, "WindowHelper.kt")
        }

        // ui
        PsiManager.getInstance(project).findDirectory(VfsUtil.createDirectories("${appRoot.path}/ui"))?.let {
            val pkg = "$rootPackage.ui"
            mainActivity(pkg).save(it, "MainActivity.kt")
            mainViewModel(pkg).save(it, "MainViewModel.kt")
        }
        PsiManager.getInstance(project).findDirectory(VfsUtil.createDirectories("${appRoot.path}/ui/base"))?.let {
            val pkg = "$rootPackage.ui.base"
            baseActivity(pkg).save(it, "BaseActivity.kt")
            baseViewModel(pkg).save(it, "BaseViewModel.kt")
            scopedFragment(pkg).save(it, "ScopedFragment.kt")
        }

        // layout
        PsiManager.getInstance(project).findDirectory(VfsUtil.createDirectories("${resRoot.path}/layout"))?.let {
            activityMain(rootPackage).save(it, "activity_main.xml")
            contentMain(rootPackage).save(it, "content_main.xml")
        }

        // navigation
        PsiManager.getInstance(project).findDirectory(VfsUtil.createDirectories("${resRoot.path}/navigation"))?.let {
            mobileNavigation.save(it, "mobile_navigation.xml")
        }

        // values
        PsiManager.getInstance(project).findDirectory(VfsUtil.createDirectories("${resRoot.path}/values"))?.let {
            dimens.save(it, "dimens.xml")
            shape.save(it, "shape.xml")
            type.save(it, "type.xml")
            styles(project.name).save(it, "styles.xml")
        }

        VfsUtil.createDirectories("${resRoot.path}/values")?.let {
            it.saveText("colors.xml", colors)
            it.saveText("themes.xml", themes(project.name))
        }
        VfsUtil.createDirectories("${resRoot.path}/values-night")?.saveText("themes.xml", themesNight(project.name))
    }

}