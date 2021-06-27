object BuildPlugins {
    const val androidPlugin = "com.android.tools.build:gradle:${Versions.androidPlugin}"
    const val kotlinPlugin = "gradle-plugin"
    const val navigationSafeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
    const val hilt = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hiltDagger}"
}

object GradlePlugins {
    const val androidApplication = "com.android.application"
    const val kotlinAndroid = "kotlin-android"
    const val androidLib = "com.android.library"
    const val javaLib = "java-library"
    const val kotlin = "kotlin"
    const val kotlinKapt = "kapt"
    const val navigationSafeArgs = "androidx.navigation.safeargs"
    const val hilt = "dagger.hilt.android.plugin"
    const val parcelize = "kotlin-parcelize"
}

object Android {
    const val minSdkVersion = 21
    const val targetSdkVersion = 30
    const val compileSdkVersion = 30
    const val applicationId = "com.ducnguyen2102.baseclean"
    const val versionCode = 1
    const val versionName = "0.0.1"
}

object TestRunner {
    const val runner = "androidx.test.runner.AndroidJUnitRunner"
}

object Libs {
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val activityKtx = "androidx.activity:activity-ktx:${Versions.activityKtx}"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragmentKtx}"
    const val multidex = "androidx.multidex:multidex:${Versions.multidex}"
    const val hiltDaggerAndroid = "com.google.dagger:hilt-android:${Versions.hiltDagger}"
    const val hiltDaggerAnnotation = "com.google.dagger:hilt-compiler:${Versions.hiltDagger}"
    const val hiltDaggerCore = "com.google.dagger:hilt-core:${Versions.hiltDagger}"
    const val hiltNavigation = "androidx.hilt:hilt-navigation-fragment:${Versions.hilt}"
    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val annotation = "androidx.annotation:annotation:${Versions.annotation}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
    const val viewPager2 = "androidx.viewpager2:viewpager2:${Versions.viewPager2}"
    const val swipeToRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeToRefreshLayout}"
    const val cardView = "androidx.cardview:cardview:${Versions.cardView}"
    const val jUnit = "junit:junit:${Versions.jUnit}"
    const val androidJUnit = "androidx.test.ext:junit:${Versions.androidJUnit}"
    const val androidEspresso = "androidx.test.espresso:espresso-core:${Versions.androidEspresso}"
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    const val saveState = "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.lifecycle}"
    const val lifecycleAnnotation = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"
    const val lifecycleService = "androidx.lifecycle:lifecycle-service:${Versions.lifecycle}"
    const val lifecycleJV8 = "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycle}"
    const val coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutine}"
    const val coroutineAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutine}"
    const val gson = "com.google.code.gson:gson:${Versions.gson}"
    const val okhttpLogging = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val room = "androidx.room:room-runtime:${Versions.room}"
    const val roomAnnotation = "androidx.room:room-compiler:${Versions.room}"
    const val roomCoroutine = "androidx.room:room-ktx:${Versions.room}"
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glideAnnotation = "com.github.bumptech.glide:compiler:${Versions.glide}"
}

object Versions {
    const val androidPlugin = "4.2.1"
    const val kotlin = "1.5.10"
    const val coreKtx = "1.5.0"
    const val activityKtx = "1.2.3"
    const val fragmentKtx = "1.3.5"
    const val multidex = "2.0.1"
    const val hiltDagger = "2.35"
    const val hilt = "1.0.0"
    const val navigation = "2.3.5"
    const val annotation = "1.2.0"
    const val appCompat = "1.3.0"
    const val material = "1.3.0"
    const val constraintLayout = "2.0.4"
    const val recyclerView = "1.2.1"
    const val viewPager2 = "1.0.0"
    const val swipeToRefreshLayout = "1.1.0"
    const val cardView = "1.0.0"
    const val jUnit = "4.+"
    const val androidJUnit = "1.1.2"
    const val androidEspresso = "3.3.0"
    const val lifecycle = "2.3.1"
    const val coroutine = "1.3.9"
    const val gson = "2.8.7"
    const val okhttp = "4.9.0"
    const val retrofit = "2.9.0"
    const val room = "2.3.0"
    const val glide = "4.12.0"
}

