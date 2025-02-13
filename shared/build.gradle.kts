import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.apollo)
    alias(libs.plugins.kotlinx.ksp)
    alias(libs.plugins.observable.native.coroutines)
}

kotlin {
    androidTarget {
        compilations.all {
            compileTaskProvider.configure {
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_17)
                }
            }
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "16.0"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        all {
            languageSettings.optIn("kotlinx.cinterop.ExperimentalForeignApi")
        }
        androidMain.dependencies {
            implementation(libs.koin.androidx.compose)
            implementation(libs.koin.android)
        }
        commonMain.dependencies {
            implementation(libs.kotlinx.coroutines)

            // koin
            implementation(libs.koin.core)

            // lifecycle
            implementation(libs.androidx.lifecycle.runtime.compose)
            implementation(libs.androidx.lifecycle.viewmodel)

            // Paging
            implementation(libs.paging.common)

            // KMP Observable ViewModel
            api(libs.kmp.observableviewmodel.core)

            // apollo
            api(libs.apollo.runtime)
            implementation(libs.apollo.normalized.cache)
            implementation(libs.apollo.normalized.cache.sqlite)
        }
        iosMain.dependencies {
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "dev.himanshu.graphqlcountryapp"
    compileSdk = 35
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

kotlin.sourceSets.all {
    languageSettings.optIn("kotlin.experimental.ExperimentalObjCName")
}
