plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.safeags)
    alias(libs.plugins.parcelize)
}

android {
    namespace = "com.github.gunin_igor75.presentation"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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

    //module
    implementation(project(":feature:home:domain"))
    implementation(project(":core:common"))

    //lifecycle
    implementation(libs.androidx.lifecycle.core)
    implementation(libs.androidx.lifecycle.viewmodel)

    //navigation
    implementation(libs.androidx.navigation.fragment.ktx)

    //viewbinding-delegate
    implementation(libs.viewbinding.delegate.kirich)

    //adapterDelegates
    implementation(libs.adapter.delegates.core)
    implementation(libs.adapter.delegates.viewbinding)

    //koin
    implementation(libs.koin.android)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}