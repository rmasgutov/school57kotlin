plugins {
    id("org.springframework.boot") version "3.3.5"
    kotlin("jvm")
    kotlin("plugin.spring") version "1.9.23"
    jacoco
    id("com.bmuschko.docker-spring-boot-application") version "9.4.0"
}


repositories {
    mavenCentral()
}


dependencies {
    implementation(platform("org.springframework.boot:spring-boot-dependencies:3.3.6"))
    implementation(platform("org.springframework.cloud:spring-cloud-dependencies:2023.0.4"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

// Apply a specific Java toolchain to ease working on different environments.
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

tasks {
    test {
        useJUnitPlatform()
    }
}

docker{
	springBootApplication{
		baseImage.set("openjdk:17-alpine")
	}
}
