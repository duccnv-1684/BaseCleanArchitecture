plugins {
    id(GradlePlugins.androidLib)
    id(GradlePlugins.kotlinAndroid)
    kotlin(GradlePlugins.kotlinKapt)
    id(GradlePlugins.hilt)
}

android {
    compileSdkVersion(Android.compileSdkVersion)

    defaultConfig {
        minSdkVersion(Android.minSdkVersion)
        targetSdkVersion(Android.targetSdkVersion)
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
}

dependencies {
    // KTX
    implementation(Libs.kotlinStdLib)
    implementation(Libs.coreKtx)

    // Hilt
    implementation(Libs.hiltDaggerAndroid)
    kapt(Libs.hiltDaggerAnnotation)

    // Test
    testImplementation(Libs.jUnit)
    androidTestImplementation(Libs.androidJUnit)

    // Modules
    Module.Domain.let {
        implementation(project(it.path)) {
            exclude(group = "com.ducnguyen.baseclean", module = it.name)
        }
    }

    // Coroutine
    implementation(Libs.coroutine)
    implementation(Libs.coroutineAndroid)

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
}