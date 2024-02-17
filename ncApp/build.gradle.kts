import org.jetbrains.compose.ExperimentalComposeLibrary

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.googleServices)
    alias(libs.plugins.firebaseCrashlytics)
    kotlin("plugin.serialization").version("1.9.20")
    id("app.cash.sqldelight").version("2.0.0")
}

sqldelight {
    databases {
        create("AppDatabase") {
            packageName = "digital.thon.nc"
        }
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
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "NcApp"
            isStatic = true
        }
    }

    configure(targets) {
        if (this is org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget && konanTarget.family.isAppleFamily) {
            compilations.getByName("main").cinterops.create("kvo") {
                packageName("digital.thon.nc")
            }
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

            // exoplayer
            implementation(libs.exoplayer)
            implementation(libs.exoplayer.ui)
            implementation(libs.exoplayer.dash)
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
            implementation(libs.firebase.config)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.kotlinx.datetime)

            // ktor
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.cio)
            implementation(libs.ktor.client.serialization)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.logging)

            // sqlDelight
            implementation(libs.sqlDelight)

            // kmule
            implementation(libs.kmule.core)
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
        versionCode = 2
        versionName = "1.1"
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
dependencies {
    implementation(libs.androidx.work.runtime.ktx)
}
