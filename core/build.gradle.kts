
/*
 *  Copyright (C) 2022-2024. Maximilian Keppeler (https://www.maxkeppeler.com)
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
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
    namespace = Modules.CORE.namespace
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

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    macosX64()
    macosArm64()

    js(IR) {
        outputModuleName = Modules.CORE.moduleName
        browser()
        binaries.executable()
    }

    applyDefaultHierarchyTemplate()

    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.materialIconsExtended)
            implementation(compose.material3)
            implementation(compose.components.resources)

            implementation(libs.datetime)
            implementation(libs.serialization)
        }

        androidMain.orNull?.dependencies {
            implementation(libs.androidx.window)
        }

        val nonMacosMain by creating {
            dependsOn(commonMain.get())

            androidMain.orNull?.dependsOn(this)
            jvmMain.orNull?.dependsOn(this)
            iosMain.orNull?.dependsOn(this)
            jsMain.orNull?.dependsOn(this)

            dependencies {
                implementation(libs.window.size)
            }
        }

        val nonJvmMain by creating {
            dependsOn(commonMain.get())

            nativeMain.orNull?.dependsOn(this)
            jsMain.orNull?.dependsOn(this)
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
