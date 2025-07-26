
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.serialization)
    alias(libs.plugins.publish)
    `maven-publish`
}

android {
    namespace = Modules.EMOJI.namespace
    compileSdk = App.COMPILE_SDK

    defaultConfig {
        minSdk = App.MIN_SDK
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    lint {
        checkGeneratedSources = false
        checkReleaseBuilds = false
        abortOnError = false
    }
}

kotlin {
    androidTarget {
        publishLibraryVariants()
    }
    jvm()

    applyDefaultHierarchyTemplate()

    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)

            implementation(libs.emoji.facebook)
            implementation(libs.emoji.google)
            implementation(libs.emoji.ios)
            implementation(libs.emoji.twitter)
            implementation(libs.serialization)

            api(project(":core"))
        }
    }
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/Mystery00/sheets-compose-dialogs")
            credentials {
                username = System.getenv("GITHUB_USERNAME") ?: project.property("gpr.user") as String? ?: ""
                password = System.getenv("GITHUB_PASSWORD") ?: project.property("gpr.key") as String? ?: ""
            }
        }
    }
}
