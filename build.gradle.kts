buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(BuildPlugins.androidPlugin)
        classpath(kotlin(BuildPlugins.kotlinPlugin, version = Versions.kotlin))
        classpath(BuildPlugins.navigationSafeArgs)
        classpath(BuildPlugins.hilt)

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven(url = "https://jitpack.io")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}