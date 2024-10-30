// build.gradle.kts (fichier à la racine du projet)
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.6.1") // Plugin Android Gradle
        classpath("com.google.gms:google-services:4.4.2") // Plugin Google Services
    }
}

plugins {
    id("com.android.application") version "8.2.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.25" apply false
    id("com.google.gms.google-services") version "4.4.2" apply false
    id ("com.github.ben-manes.versions") version "0.46.0"
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}
