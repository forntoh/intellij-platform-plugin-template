package com.forntoh.mvvmtemplates.setup

import com.android.tools.idea.wizard.template.*
import java.io.File


val commonTemplate
    get() = template {
        revision = 2
        name = "Common"
        description = "Create a new common module"

        minApi = 21
        minBuildApi = 21
        category = mCategory
        formFactor = FormFactor.Mobile
        screens = mScreens

        thumb {
            File("")
        }

        recipe = { data: TemplateData ->
            commonModuleSetup(data as ModuleTemplateData, "common")
        }
    }

val databaseTemplate
    get() = template {
        revision = 2
        name = "Database"
        description = "Create a new database module"

        minApi = 21
        minBuildApi = 21
        category = mCategory
        formFactor = FormFactor.Mobile
        screens = mScreens

        thumb {
            File("")
        }

        recipe = { data: TemplateData ->
            databaseModuleSetup(data as ModuleTemplateData, "database")
        }
    }


val webServiceTemplate
    get() = template {
        revision = 2
        name = "Web-service"
        description = "Create a new web-service module"

        minApi = 21
        minBuildApi = 21
        category = mCategory
        formFactor = FormFactor.Mobile
        screens = mScreens

        thumb {
            File("")
        }

        recipe = { data: TemplateData ->
            webServiceModuleSetup(data as ModuleTemplateData, "web-service")
        }
    }


val repositoryTemplate
    get() = template {
        revision = 2
        name = "Repository"
        description = "Create a new repository module"

        minApi = 21
        minBuildApi = 21
        category = mCategory
        formFactor = FormFactor.Mobile
        screens = mScreens

        thumb {
            File("")
        }

        recipe = { data: TemplateData ->
            repositoryModuleSetup(data as ModuleTemplateData, "repository")
        }
    }

val mCategory = Category.Application
val mScreens = listOf(
    WizardUiContext.FragmentGallery,
    WizardUiContext.MenuEntry,
    WizardUiContext.NewModule
)
