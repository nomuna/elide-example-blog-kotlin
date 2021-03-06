package com.dennismcwherter.elide.app

import com.dennismcwherter.elide.app.filters.UserAuthFilter
import com.dennismcwherter.elide.app.security.checks.AccountChecks
import com.dennismcwherter.elide.app.security.checks.OwnedEntityChecks
import com.dennismcwherter.elide.app.security.checks.UserChecks
import com.yahoo.elide.security.checks.prefab.Common
import com.yahoo.elide.security.checks.prefab.Role
import com.yahoo.elide.standalone.config.ElideStandaloneSettings

/**
 * Configuration for Service
 */
class ServiceSettings : ElideStandaloneSettings {
    override fun getCheckMappings() = mapOf(
        "any user" to Role.ALL::class.java,
        "deny all" to Role.NONE::class.java,
        "user is logged in" to UserChecks.IsLoggedIn::class.java,
        "user is accessing self" to AccountChecks.AccessingSelf::class.java,
        "is accessed by owner at commit" to OwnedEntityChecks.IsAccessedByOwner.AtCommit::class.java,
        "is accessed by owner at operation" to OwnedEntityChecks.IsAccessedByOwner.AtOperation::class.java,
        "entity is newly created" to Common.UpdateOnCreate::class.java
    )

    override fun getFilters() = listOf(
        UserAuthFilter::class.java
    )

    override fun getPort() = 5050

    override fun getModelPackageName() = "com.dennismcwherter.elide.app.models"
}
