plugins {
    id("buildlogic.kotlin-library-conventions")
    kotlin("jvm") version "2.2.0"
}
dependencies {
    testImplementation(kotlin("test"))
    implementation(kotlin("stdlib-jdk8"))
}
repositories {
    mavenCentral()
}
kotlin {
    jvmToolchain(17)
}