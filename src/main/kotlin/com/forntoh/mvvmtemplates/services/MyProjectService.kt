package com.forntoh.mvvmtemplates.services

import com.intellij.openapi.project.Project
import com.forntoh.mvvmtemplates.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
