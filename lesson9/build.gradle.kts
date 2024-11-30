plugins {
    id("org.springframework.boot") version "3.3.5"
    kotlin("jvm")
    kotlin("plugin.spring") version "1.9.23"
    id("com.bmuschko.docker-spring-boot-application") version "9.4.0"
}


repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("org.springframework.boot:spring-boot-dependencies:3.3.5"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks{
    test{
        useJUnitPlatform()
    }
}