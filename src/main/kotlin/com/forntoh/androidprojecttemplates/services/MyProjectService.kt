package com.forntoh.androidprojecttemplates.services

import com.intellij.openapi.project.Project
import com.forntoh.androidprojecttemplates.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
