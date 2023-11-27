import org.jetbrains.compose.ExperimentalComposeLibrary

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.googleServices)
    alias(libs.plugins.firebaseCrashlytics)
    kotlin("plugin.serialization").version("1.9.20")
    id("com.squareup.sqldelight").version("1.5.5")
}

sqldelight {
    database("AppDatabase") {
        packageName = "com.thondigital.nc"
        sourceFolders = listOf("sqldelight")
    }
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    sourceSets {

        iosMain.dependencies {
            implementation(libs.ktor.client.ios)
            implementation(libs.sqlDelight.native.driver)
        }

        androidMain.dependencies {
            implementation(libs.compose.ui)
            implementation(libs.compose.ui.tooling.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.voyager.androidx)
            implementation(libs.voyager.koin)
            implementation(libs.voyager.bottomSheetNavigator)
            implementation(libs.voyager.navigator)
            implementation(libs.koin.compose)
            implementation(libs.voyager.transitions)
            implementation(project.dependencies.platform(libs.android.firebase.bom))
            implementation(libs.android.firebase.analytics)
            implementation(libs.android.firebase.crashlytics)

            // ktor
            implementation(libs.ktor.client.android)

            // sqlDelight
            implementation(libs.sqlDelight.android.driver)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.material3)
            @OptIn(ExperimentalComposeLibrary::class)
            implementation(compose.components.resources)
            implementation(libs.koin.core)
            implementation(libs.voyager.koin)
            implementation(libs.voyager.bottomSheetNavigator)
            implementation(libs.voyager.navigator)
            implementation(libs.voyager.transitions)
            implementation(libs.qdsfdhvh.image.loader)

            implementation(libs.firestore)
            implementation(libs.firebase.common)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.kotlinx.datetime)

            // ktor
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.serialization)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.logging)

            // sqlDelight
            implementation(libs.sqlDelight)
        }
    }
}

android {
    namespace = "com.thondigital.nc"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "com.thondigital.nc"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    dependencies {
        debugImplementation(libs.compose.ui.tooling)
        implementation(libs.firebase.common)
    }
}
