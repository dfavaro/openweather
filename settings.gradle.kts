pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
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
rootProject.name = "OpenWeather"

include(":app")
include(":core:data")
include(":core:database")
include(":core:model")
include(":core:navigation")
include(":core:network")
include(":core:ui")
include(":core:testing")
include(":feature:main")
include(":feature:weather")
