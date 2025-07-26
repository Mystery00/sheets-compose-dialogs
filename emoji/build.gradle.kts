
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
            url = uri(Repo.URI)
            credentials {
                username = System.getenv(Repo.ENV_USERNAME)
                    ?: project.property(Repo.PROPS_USERNAME) as String? ?: ""
                password = System.getenv(Repo.ENV_PASSWORD)
                    ?: project.property(Repo.PROPS_PASSWORD) as String? ?: ""
            }
        }
    }
}
