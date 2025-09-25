plugins {
    buildlogic.`kotlin-common-conventions-no-detekt`
    kotlin("plugin.serialization") version "1.9.23"
}
dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0") // Or the latest version
}