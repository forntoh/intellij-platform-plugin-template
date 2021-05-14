package com.forntoh.mvvmtemplates.recipes.webservice

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.forntoh.mvvmtemplates.listeners.MyProjectManagerListener.Companion.projectInstance
import com.forntoh.mvvmtemplates.recipes.createDirInSrc
import com.forntoh.mvvmtemplates.recipes.packageName
import com.forntoh.mvvmtemplates.recipes.save
import com.forntoh.mvvmtemplates.recipes.webservice.src.*
import com.intellij.openapi.vfs.VfsUtil
import com.intellij.psi.PsiManager

fun RecipeExecutor.webServiceFileStructure(
    moduleData: ModuleTemplateData,
    commonPackageName: String,
    useHttps: Boolean,
    domain: String,
    port: String,
) {

    val project = projectInstance ?: return

    // Delete res dir
    VfsUtil.findFileByIoFile(moduleData.resDir, false)?.delete(this)

    // Create api dir
    with(PsiManager.getInstance(project).findDirectory(createDirInSrc(moduleData, "api"))!!) {
        val eventPackage = packageName(moduleData)
        apiManager(eventPackage).save(this, "ApiManager.kt")
        baseApiService(eventPackage).save(this, "BaseApiService.kt")
    }
    // Create base dir
    with(PsiManager.getInstance(project).findDirectory(createDirInSrc(moduleData, "base"))!!) {
        val eventPackage = packageName(moduleData)
        baseUrl(eventPackage, useHttps, domain, port).save(this, "BaseUrl.kt")
        endPoints(eventPackage).save(this, "EndPoints.kt")
    }
    // Create di dir
    with(PsiManager.getInstance(project).findDirectory(createDirInSrc(moduleData, "di"))!!) {
        val eventPackage = packageName(moduleData)
        networkModule(eventPackage).save(this, "NetworkModule.kt")
        sslModule(eventPackage).save(this, "SslModule.kt")
    }
    // Create ds dir
    with(PsiManager.getInstance(project).findDirectory(createDirInSrc(moduleData, "ds"))!!) {
        val eventPackage = packageName(moduleData)
        baseNetworkDataSource(eventPackage).save(this, "BaseNetworkDataSource.kt")
    }
    // Create dto dir
    with(PsiManager.getInstance(project).findDirectory(createDirInSrc(moduleData, "dto"))!!) {
        val eventPackage = packageName(moduleData)
        resource(eventPackage).save(this, "Resource.kt")
        errorDto(eventPackage).save(this, "ErrorDto.kt")
    }
    // Create interceptors dir
    with(PsiManager.getInstance(project).findDirectory(createDirInSrc(moduleData, "interceptors"))!!) {
        val eventPackage = packageName(moduleData)
        baseInterceptor(eventPackage).save(this, "BaseInterceptor.kt")
        tokenInterceptor(eventPackage, commonPackageName).save(this, "TokenInterceptor.kt")
    }
}