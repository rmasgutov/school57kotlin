plugins {
    id("buildlogic.kotlin-common-conventions-no-detekt")
    id("jacoco")
}
dependencies {
    testImplementation(kotlin("test"))
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        html.required.set(true)
        xml.required.set(true)
    }
}
