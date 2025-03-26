plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.ksp)
}

android {
    namespace = "com.idea.billdor"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.idea.billdor"
        minSdk = 30
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(project(":core"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Coroutines
    implementation(libs.org.jetbrains.kotlinx.coroutines.core)
    implementation(libs.org.jetbrains.android.coroutines.android)

    // TIMBER FOR LOGGING
    implementation(libs.timber.logging)

    // RETROFIT, G-SON AND RETROFIT-MOSHI CONVERTER
    implementation(libs.squareup.retrofit)
    implementation(libs.squareup.retrofit.gson)
    implementation(libs.squareup.retrofit.moshi)

    // MOSHI for parsing the JSON format
    implementation(libs.squareup.moshi)

    // OK-HTTP CLIENT
    implementation(libs.ok.http.client)

    // ViewModel and LiveData (arch components)
    implementation(libs.viewmodel.saved.state)
    implementation(libs.viewmodel.ktx)
    implementation(libs.viewmodel.compose)
    implementation(libs.runtime.compose)
    implementation(libs.livedata.ktx)
    ksp(libs.lifecycle.compiler)

    // Room database
    implementation(libs.room.runtime)
    ksp(libs.room.compiler)

    // Kotlin Extensions and Coroutines support for Room
    implementation(libs.room.ktx)
    implementation(libs.room.paging)

    // Dagger Hilt
    implementation(libs.dagger.hilt)
    ksp(libs.dagger.hilt.compiler)

    // Compose Navigation (NavHost)
    implementation(libs.navigation.compose)

    // Accompanist
    implementation(libs.accompanist.insets)
    implementation(libs.accompanist.insets.ui)

    // Coil Compose
    implementation(libs.glide.compose)
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)
}