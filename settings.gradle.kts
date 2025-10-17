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
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

rootProject.name = "EnglishDictionary"

includeBuild("build-logic")

include(":app")
include(":features:search:data")
include(":features:search:domain")
include(":features:search:presentation")
include(":core:utils")
include(":core:data")
include(":features:explore:data")
include(":features:explore:domain")
include(":features:explore:presentation")
include(":features:quiz:data")
include(":features:quiz:domain")
include(":features:quiz:presentation")
include(":core:ui")
include(":features:createfolder:data")
include(":features:createfolder:domain")
include(":features:createfolder:presentation")
