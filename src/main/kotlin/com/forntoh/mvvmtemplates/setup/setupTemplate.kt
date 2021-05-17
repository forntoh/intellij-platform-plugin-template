package com.forntoh.mvvmtemplates.setup

import com.android.tools.idea.wizard.template.*

const val commonModuleName = "common"

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

        recipe = { data: TemplateData ->
            commonModuleSetup(data as ModuleTemplateData)
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

        val databaseName = stringParameter {
            name = "Database Name"
            default = "database"
            help = "The name of the database file to be created on runtime"
            constraints = listOf(Constraint.NONEMPTY)
        }

        widgets(
            TextFieldWidget(databaseName),
        )

        recipe = { data: TemplateData ->
            databaseModuleSetup(
                data as ModuleTemplateData,
                databaseName.value,
                commonModuleName,
            )
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

        val domain = stringParameter {
            name = "Domain Name"
            default = "www.example.com"
            help = "The domain name of the API"
            constraints = listOf(Constraint.NONEMPTY)
        }

        val port = stringParameter {
            name = "Port"
            default = ""
            help = "The port of the API"
        }

        val useHttps = booleanParameter {
            name = "Use HTTPS"
            default = true
        }

        widgets(
            TextFieldWidget(domain),
            TextFieldWidget(port),
            CheckBoxWidget(useHttps),
        )

        recipe = { data: TemplateData ->
            webServiceModuleSetup(
                data as ModuleTemplateData,
                commonModuleName,
                useHttps.value,
                domain.value,
                port.value
            )
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

        val databaseModuleName = stringParameter {
            name = "Database Module Name"
            default = "database"
            help = "The name of the Database module"
            constraints = listOf(Constraint.MODULE, Constraint.NONEMPTY)
        }

        val webServiceModuleName = stringParameter {
            name = "Web-service Module Name"
            default = "web-service"
            help = "The name of the Web-service module"
            constraints = listOf(Constraint.MODULE, Constraint.NONEMPTY)
        }

        widgets(
            TextFieldWidget(databaseModuleName),
            TextFieldWidget(webServiceModuleName),
        )

        recipe = { data: TemplateData ->
            repositoryModuleSetup(
                data as ModuleTemplateData,
                commonModuleName,
                webServiceModuleName.value.replace('-', '_'),
            )
        }
    }

val mCategory get() = Category.Other

val mScreens get() = listOf(WizardUiContext.NewModule)
