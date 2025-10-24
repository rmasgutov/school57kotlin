plugins {
    buildlogic.`kotlin-common-conventions-no-detekt`
    kotlin("plugin.spring") version "1.9.23"
    id("org.springframework.boot") version "3.3.5"
    id("io.spring.dependency-management") version "1.1.6"
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
}