plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.example.cryptoinfoapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.cryptoinfoapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "com.example.cryptoinfoapp.HiltTestRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "META-INF/LICENSE.md"
            excludes += "META-INF/LICENSE.txt"
            excludes += "META-INF/LICENSE-notice.md"
            excludes += "META-INF/NOTICE.md"
            excludes += "META-INF/NOTICE.txt"
        }
    }
}

// Define versions
val okhttpVersion = "4.11.0"
val retrofitVersion = "2.9.0"
val hiltVersion = "2.48" // Use 2.48 which is more stable with tests
val coroutinesVersion = "1.7.3"
val composeBomVersion = "2023.08.00" // Use a stable Compose BOM version

dependencies {
    // Core dependencies
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.2")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")

    // Compose dependencies with BOM for version consistency
    implementation(platform("androidx.compose:compose-bom:$composeBomVersion"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // Navigation
    implementation("androidx.navigation:navigation-compose:2.7.5")
    implementation("com.google.accompanist:accompanist-flowlayout:0.20.0") // Use a version compatible with your Compose version

    // Retrofit & OkHttp with consistent versions
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")
    implementation("com.squareup.okhttp3:okhttp:$okhttpVersion")
    implementation("com.squareup.okhttp3:logging-interceptor:$okhttpVersion")
    implementation("com.google.code.gson:gson:2.10.1")

    // Dagger-Hilt
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    kapt("com.google.dagger:hilt-android-compiler:$hiltVersion")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")

    // Unit Tests
    testImplementation("junit:junit:4.13.2")
    testImplementation("androidx.test:core:1.5.0")
    testImplementation("androidx.arch.core:core-testing:2.2.0")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesVersion")
    testImplementation("com.google.truth:truth:1.1.3")
    testImplementation("com.squareup.okhttp3:mockwebserver:$okhttpVersion")
    testImplementation("io.mockk:mockk:1.13.5")

    // Instrumentation Tests
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:$composeBomVersion"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    androidTestImplementation("androidx.test:runner:1.5.2")

    // Coroutines for testing
    androidTestImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    androidTestImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesVersion")
    androidTestImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")

    // Hilt Testing
    androidTestImplementation("com.google.dagger:hilt-android-testing:$hiltVersion")
    kaptAndroidTest("com.google.dagger:hilt-android-compiler:$hiltVersion")

    // MockWebServer for Android Tests
    androidTestImplementation("com.squareup.okhttp3:mockwebserver:$okhttpVersion")

    // Other testing dependencies
    androidTestImplementation("androidx.arch.core:core-testing:2.2.0")
    androidTestImplementation("com.google.truth:truth:1.1.3")
    androidTestImplementation("io.mockk:mockk-android:1.13.5")
}

// Force consistent OkHttp versions across all dependencies
configurations.all {
    resolutionStrategy {
        force("com.squareup.okhttp3:okhttp:$okhttpVersion")
        force("com.squareup.okhttp3:logging-interceptor:$okhttpVersion")
        force("com.squareup.okhttp3:mockwebserver:$okhttpVersion")

        // Force consistent Coroutines versions
        force("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
        force("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")
        force("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesVersion")
    }
}

kapt {
    correctErrorTypes = true
}