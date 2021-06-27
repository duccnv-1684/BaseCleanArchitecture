plugins {
    id(GradlePlugins.javaLib)
    id(GradlePlugins.kotlin)
    kotlin(GradlePlugins.kotlinKapt)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_7
    targetCompatibility = JavaVersion.VERSION_1_7
}

dependencies {
    // KTX
    implementation(Libs.kotlinStdLib)

    // Coroutine
    implementation(Libs.coroutine)

    // Hilt
    implementation(Libs.hiltDaggerCore)
    kapt(Libs.hiltDaggerAnnotation)
}