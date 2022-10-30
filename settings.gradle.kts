pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "list-resources-demo"
include(":androidApp")
include(":iosApp")
include(":desktopApp")
include(":webApp")
include(":shared")


