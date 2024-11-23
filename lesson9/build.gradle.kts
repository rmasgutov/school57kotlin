plugins {
    java
    id("org.springframework.boot") version "2.7.18"
    kotlin("jvm")
    kotlin("plugin.spring") version "1.9.23"
    id("com.bmuschko.docker-spring-boot-application") version "9.4.0"
}


repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("org.springframework.cloud:spring-cloud-dependencies:2021.0.9"))
    implementation(platform("org.springframework.boot:spring-boot-dependencies:2.7.18"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")


    testImplementation("org.springframework.boot:spring-boot-starter-test")
}
