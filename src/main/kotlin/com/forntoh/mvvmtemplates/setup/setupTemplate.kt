package com.forntoh.mvvmtemplates.setup

import com.android.tools.idea.wizard.template.*

val commonTemplate
    get() = template {
        revision = 2
        name = "New common module"
        description = "Create a new common module"

        minApi = 21
        minBuildApi = 21
        category = Category.Other
        formFactor = FormFactor.Mobile
        screens = listOf(
            WizardUiContext.FragmentGallery,
            WizardUiContext.MenuEntry,
            WizardUiContext.NewProject,
            WizardUiContext.NewModule,
        )

        name = "common"

        recipe = { data: TemplateData ->
            commonModuleSetup(data as ModuleTemplateData)
        }
    }

val databaseTemplate
    get() = template {
        revision = 2
        name = "New database module"
        description = "Create a new database module"

        minApi = 21
        minBuildApi = 21
        category = Category.Application
        formFactor = FormFactor.Mobile
        screens = listOf(WizardUiContext.NewModule)

        name = "database"

        recipe = { data: TemplateData ->
            databaseModuleSetup(data as ModuleTemplateData)
        }
    }


val webServiceTemplate
    get() = template {
        revision = 2
        name = "New web-service module"
        description = "Create a new web-service module"

        minApi = 21
        minBuildApi = 21
        category = Category.Application
        formFactor = FormFactor.Mobile
        screens = listOf(WizardUiContext.NewModule)

        name = "database"

        recipe = { data: TemplateData ->
            webServiceModuleSetup(data as ModuleTemplateData)
        }
    }



val repositoryTemplate
    get() = template {
        revision = 2
        name = "New repository module"
        description = "Create a new repository module"

        minApi = 21
        minBuildApi = 21
        category = Category.Application
        formFactor = FormFactor.Mobile
        screens = listOf(WizardUiContext.NewModule)

        name = "database"

        recipe = { data: TemplateData ->
            repositoryModuleSetup(data as ModuleTemplateData)
        }
    }

