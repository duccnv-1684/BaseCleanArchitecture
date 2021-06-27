sealed class Module(val name: String, val path: String) {
    object Domain : Module("domain", ":domain")
    object Data : Module("data", ":data")
    object Presentation : Module("presentation", ":presentation")
}