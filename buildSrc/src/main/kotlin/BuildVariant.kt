sealed class BuildType(val name: String, val isMinifyEnable: Boolean, val isTestCoverageEnabled: Boolean, val isDebuggable: Boolean) {
    object Debug : BuildType("debug", false, true, true)

    object Release : BuildType("release", true, false, false)
}

val allBuildType = listOf(BuildType.Debug, BuildType.Release)

sealed class FlavorDimension(val name: String) {
    object Default : FlavorDimension("default")
}

val allFlavorDimension = listOf(FlavorDimension.Default)

sealed class ProductFlavor(val name: String, dimensions: List<FlavorDimension>, val matchingFallbacks: List<BuildType>) {

    val flavorDimensions = dimensions.joinToString { it.name }

    object Develop : ProductFlavor("develop", listOf(FlavorDimension.Default), listOf(BuildType.Debug))

    object Production : ProductFlavor("production", listOf(FlavorDimension.Default), listOf(BuildType.Release))
}
