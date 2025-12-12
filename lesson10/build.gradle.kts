plugins {
    id("buildlogic.kotlin-common-conventions-no-detekt")
}
dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    testImplementation(kotlin("test"))
}