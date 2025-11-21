plugins {
    id("buildlogic.kotlin-library-conventions")
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
