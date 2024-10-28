pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositories {
        google() // Assurez-vous que ce dépôt est présent
        mavenCentral()
    }
}

rootProject.name = "LeClubParvechin"
include(":app")
