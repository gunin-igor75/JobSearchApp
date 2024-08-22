plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.github.gunin_igor75.jobsearchapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.github.gunin_igor75.jobsearchapp"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    //viewbinding-delegate
    implementation(libs.viewbinding.delegate.kirich)

    //module
    implementation(project(":bridge_di_module"))
    implementation(project(":core:local"))
    implementation(project(":core:network"))
    implementation(project(":core:common"))
    implementation(project(":feature:home:data"))
    implementation(project(":feature:home:domain"))
    implementation(project(":feature:home:presentation"))
    implementation(project(":feature:favorites:data"))
    implementation(project(":feature:favorites:domain"))
    implementation(project(":feature:favorites:presentation"))
    implementation(project(":feature:response"))
    implementation(project(":feature:message"))
    implementation(project(":feature:profile"))

    //navigation
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    //koin
    implementation(libs.koin.android)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}