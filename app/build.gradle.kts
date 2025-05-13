import java.util.Properties

val localPropsFile = rootProject.file("local.properties")
val localProps = Properties().apply {
    if (localPropsFile.exists()) {
        load(localPropsFile.inputStream())
    }
}


plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.serialization)
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")


}

android {
    namespace = "com.example.mapsapp"
    compileSdk = 35
    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        applicationId = "com.example.mapsapp"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField(
            type = "String",
            name = "SUPABASE_URL",
            value = "\"${localProps.getProperty("https://nqcvfzxjrkqynmhykmvv.supabase.co") ?: ""}\""
        )
        buildConfigField(
            type = "String",
            name = "SUPABASE_KEY",
            value = "\"${localProps.getProperty("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Im5xY3ZmenhqcmtxeW5taHlrbXZ2Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDU4Mjc4NTUsImV4cCI6MjA2MTQwMzg1NX0.m0JobXeoahlMYAr2GyveDbta-blW_RLbFe44fsv3fsg") ?: ""}\""
        )

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

    // Navegación
    implementation(libs.navigation.compose)
    implementation(libs.kotlinx.serialization.json)
    // MVVM y LiveData
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.9.0")
    implementation("androidx.compose.runtime:runtime-livedata:1.7.8")
    // Constraint Layout
    implementation ("androidx.constraintlayout:constraintlayout:2.2.1")
    implementation ("androidx.constraintlayout:constraintlayout-compose:1.1.1")
    // Iconos Google
    implementation("androidx.compose.material:material-icons-extended:1.6.0")

    // Google Maps

    implementation("com.google.maps.android:maps-compose:2.11.4")
    implementation("com.google.android.gms:play-services-maps:19.2.0")
    implementation("com.google.android.gms:play-services-location:21.3.0")

    // Supabase database

    implementation(platform("io.github.jan-tennert.supabase:bom:3.1.4"))
    implementation("io.github.jan-tennert.supabase:postgrest-kt")
    implementation("io.ktor:ktor-client-android:3.1.2")


}