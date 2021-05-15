package com.forntoh.mvvmtemplates

import com.android.tools.idea.wizard.template.Template
import com.android.tools.idea.wizard.template.WizardTemplateProvider
import com.forntoh.mvvmtemplates.setup.*

class WizardTemplateProviderImpl : WizardTemplateProvider() {

    override fun getTemplates(): List<Template> = listOf(
        commonTemplate,
        databaseTemplate,
        webServiceTemplate,
        repositoryTemplate,
    )
}