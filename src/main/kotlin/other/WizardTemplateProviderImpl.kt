package other

import com.android.tools.idea.wizard.template.Template
import com.android.tools.idea.wizard.template.WizardTemplateProvider
import other.setup.commonTemplate
import other.setup.databaseTemplate
import other.setup.repositoryTemplate
import other.setup.webServiceTemplate

class WizardTemplateProviderImpl : WizardTemplateProvider() {

    override fun getTemplates(): List<Template> = listOf(
            commonTemplate,
            databaseTemplate,
            webServiceTemplate,
            repositoryTemplate,
    )
}