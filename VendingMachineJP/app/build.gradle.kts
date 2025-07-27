plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.example.vendingmachinejp"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.vendingmachinejp"
        minSdk = 24
        targetSdk = 36
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

    // Jetpack Compose
    implementation ("androidx.compose.ui:ui:1.6.0")
    implementation ("androidx.navigation:navigation-compose:2.7.5")
    implementation("androidx.compose.foundation:foundation:1.6.1")

    // Lifecycle ViewModel + LiveData
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    // Jetpack Compose
    implementation("androidx.compose.runtime:runtime-livedata:1.8.3")


    // Hilt
    implementation("com.google.dagger:hilt-android:2.56.2")
    kapt("com.google.dagger:hilt-compiler:2.56.2")

    // Hilt with Compose
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")


// Retrofit + OkHttp
    implementation ("com.squareup.retrofit2:retrofit:3.0.0")
    implementation ("com.squareup.retrofit2:converter-gson:3.0.0")
    implementation ("com.squareup.okhttp3:okhttp:5.0.0-alpha.2")
    implementation (libs.logging.interceptor)
    implementation(libs.retrofit.converter.scalars)

// Coil (image loading)
    implementation("io.coil-kt:coil-compose:2.7.0")

// Coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.10.2")

    //datastore
    implementation("androidx.datastore:datastore-preferences:1.1.7")

    // room
    implementation (libs.androidx.room.runtime)
    implementation (libs.androidx.room.ktx)
    kapt (libs.androidx.room.compiler)


// Core ExoPlayer
    implementation("androidx.media3:media3-exoplayer:1.7.1")

    // UI components for ExoPlayer
    implementation("androidx.media3:media3-ui:1.7.1")

    // Optional: HLS support
    implementation("androidx.media3:media3-exoplayer-hls:1.3.0")

    // Optional: Common utilities
    implementation("androidx.media3:media3-common:1.7.1")

    implementation("androidx.compose.foundation:foundation:1.8.3")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.9.0")


}
apply(plugin = "dagger.hilt.android.plugin")