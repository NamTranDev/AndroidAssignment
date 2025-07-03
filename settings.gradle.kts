pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "JetpackComposeHomeAssignment"
include(":app")
include(":domain")
include(":data")
include(":di")

include(":core:utils")
include(":core:ui_state")
include(":core:components")
include(":core:resource")
include(":core:navigation")

include(":feature:onboarding")
include(":feature:home")

include(":shared:bookmark")
include(":feature:product_detail")
include(":core:test")
