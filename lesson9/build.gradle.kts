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
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0")
    implementation("org.springdoc:springdoc-openapi-starter-common:2.1.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jre8:1.2.71")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.2.71")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.9.9")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}


