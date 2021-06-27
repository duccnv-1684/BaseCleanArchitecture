plugins {
    id(GradlePlugins.androidApplication)
    id(GradlePlugins.kotlinAndroid)
    kotlin(GradlePlugins.kotlinKapt)
    id(GradlePlugins.navigationSafeArgs)
    id(GradlePlugins.hilt)
    id(GradlePlugins.parcelize)
}

apply {

}

android {
    compileSdkVersion(Android.compileSdkVersion)

    defaultConfig {
        applicationId = Android.applicationId
        minSdkVersion(Android.minSdkVersion)
        targetSdkVersion(Android.targetSdkVersion)
        versionCode = Android.versionCode
        versionName = Android.versionName

        testInstrumentationRunner = TestRunner.runner
    }

    buildTypes {
        allBuildType.forEach {
            getByName(it.name) {
                isMinifyEnabled = it.isMinifyEnable
                isTestCoverageEnabled = it.isTestCoverageEnabled
                isDebuggable = it.isDebuggable
            }
        }
    }

    flavorDimensions(allFlavorDimension.joinToString { it.name })

    productFlavors {
        ProductFlavor.Develop.let {
            create(it.name) {
                setMatchingFallbacks(it.matchingFallbacks.map { it.name })
                flavorDimensions(it.flavorDimensions)
            }
        }
        ProductFlavor.Production.let {
            create(it.name) {
                setMatchingFallbacks(it.matchingFallbacks.map { it.name })
                flavorDimensions(it.flavorDimensions)
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    // Core KTX
    implementation(Libs.kotlinStdLib)
    implementation(Libs.coreKtx)
    implementation(Libs.activityKtx)
    implementation(Libs.fragmentKtx)

    // Multidex
    implementation(Libs.multidex)

    // Hilt
    implementation(Libs.hiltDaggerAndroid)
    kapt(Libs.hiltDaggerAnnotation)
    implementation(Libs.hiltNavigation)

    // Navigation
    implementation(Libs.navigationFragment)
    implementation(Libs.navigationUi)

    // UI
    implementation(Libs.annotation)
    implementation(Libs.appCompat)
    implementation(Libs.material)
    implementation(Libs.constraintLayout)
    implementation(Libs.recyclerView)
    implementation(Libs.cardView)
    implementation(Libs.viewPager2)
    implementation(Libs.swipeToRefreshLayout)

    // Test
    testImplementation(Libs.jUnit)
    androidTestImplementation(Libs.androidJUnit)
    androidTestImplementation(Libs.androidEspresso)

    // Modules
    Module.Domain.let {
        implementation(project(it.path)) {
            exclude(group = "com.ducnguyen.baseclean", module = it.name)
        }
    }
    Module.Data.let {
        implementation(project(it.path)) {
            exclude(group = "com.ducnguyen.baseclean", module = it.name)
        }
    }

    // Lifecycle
    implementation(Libs.viewModel)
    implementation(Libs.liveData)
    implementation(Libs.saveState)
    kapt(Libs.lifecycleAnnotation)
    implementation(Libs.lifecycleService)
    implementation(Libs.lifecycleJV8)

    // Gson
    implementation(Libs.gson)

    // OkHttp
    implementation(Libs.okhttpLogging)

    // Retrofit
    implementation(Libs.retrofit)
    implementation(Libs.retrofitGson)

    // Room Database
    implementation(Libs.room)
    kapt(Libs.roomAnnotation)
    implementation(Libs.roomCoroutine)

    // Coroutine
    implementation(Libs.coroutine)
    implementation(Libs.coroutineAndroid)

    // Glide
    implementation(Libs.glide)
    kapt(Libs.glideAnnotation)
}