plugins {
    buildlogic.`kotlin-common-conventions-no-detekt`
}


tasks.test {
    doFirst {
        project.projectDir.resolve("src/test/resources").listFiles().forEach {
            it.deleteRecursively()
        }
    }
}