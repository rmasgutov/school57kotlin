```kotlin
// Плагины — включают функциональность Gradle для проекта
plugins {
    // Kotlin/JVM: компиляция kotlin-кода, задачи compileKotlin/test
    kotlin("jvm") version "1.9.24"

    // java-library: вводит конфигурации api/implementation для библиотек
    `java-library`

    // application: добавляет задачи run/distZip; полезно, если есть точка входа (main)
    application

    // jacoco: сбор покрытия тестами
    jacoco
}

// Координаты артефакта (группа и версия) — нужны для публикации и идентификации
group = "com.example"
version = "1.0.0"

// Опционально: сбор исходников в источники JAR (полезно для публикации)
java {
    withSourcesJar()
}

// Настройка JDK-инструментария (toolchain), чтобы все собирались на единой версии
kotlin {
    jvmToolchain(17)
}

// Репозитории — где Gradle ищет зависимости
repositories {
    mavenCentral()

    // Пример приватного репозитория (Nexus/Artifactory). Данные лучше хранить в gradle.properties или ENV.
    // maven {
    //     url = uri("https://repo.my-company.com/maven-public")
    //     credentials {
    //         username = findProperty("repoUser") as String? ?: System.getenv("REPO_USER")
    //         password = findProperty("repoPass") as String? ?: System.getenv("REPO_PASS")
    //     }
    // }
}

// Зависимости — по конфигурациям (api/implementation/testImplementation/…)
dependencies {
    // Библиотечный API: зависимость "протекает" к потребителям этого модуля
    api(kotlin("stdlib"))

    // Внутренние зависимости (не попадают в экспортируемый API)
    implementation("org.slf4j:slf4j-api:2.0.12")

    // Только в рантайме (логгер-реализация), компиляция не требует
    runtimeOnly("ch.qos.logback:logback-classic:1.4.14")

    // Тестовые зависимости (компиляция тестов)
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.2")
    testImplementation("io.kotest:kotest-assertions-core:5.8.1")
    testImplementation("io.mockk:mockk:1.13.10")

    // Раннер Kotest на JUnit Platform — только для выполнения тестов
    testRuntimeOnly("io.kotest:kotest-runner-junit5:5.8.1")

    // "Безопасность" цепочки поставок: принудительно безопасная версия log4j-core
    constraints {
        implementation("org.apache.logging.log4j:log4j-core:2.17.2") {
            because("Fix CVE-2021-44228 (Log4Shell)")
        }
    }
}

// Конфигурация задач тестирования — JUnit Platform и логирование
tasks.test {
    // Запуск JUnit 5 и совместимых фреймворков (Kotest)
    useJUnitPlatform()

    // Настройки логирования результатов тестов в консоли
    testLogging {
        events("passed", "skipped", "failed")
        showExceptions = true
        exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
        showStandardStreams = false
    }
}

// Отчёты покрытия кода (JaCoCo)
tasks.jacocoTestReport {
    dependsOn(tasks.test) // сначала тесты, затем отчёт
    reports {
        html.required.set(true) // HTML отчёт (удобно смотреть в браузере)
        xml.required.set(true)  // XML (полезно для CI/аналитики)
        csv.required.set(false)
    }
}

// Дополнительный набор исходников для интеграционных тестов (отдельный sourceSet)
sourceSets {
    // Создаём sourceSet "integrationTest" (путь: src/integrationTest/kotlin)
    val integrationTest by creating {
        kotlin.srcDir("src/integrationTest/kotlin")
        resources.srcDir("src/integrationTest/resources")

        // Класспат для компиляции/запуска интеграционных тестов:
        compileClasspath += sourceSets.main.get().output + configurations.testRuntimeClasspath.get()
        runtimeClasspath += output + compileClasspath
    }
}

// Конфигурации зависимостей для integrationTest — расширяют существующие
configurations {
    create("integrationTestImplementation") {
        extendsFrom(implementation.get(), testImplementation.get())
    }
    create("integrationTestRuntimeOnly") {
        extendsFrom(runtimeOnly.get(), testRuntimeOnly.get())
    }
}

// Зависимости integrationTest (например, JUnit 5)
dependencies {
    "integrationTestImplementation"(kotlin("stdlib"))
    "integrationTestImplementation"("org.junit.jupiter:junit-jupiter:5.10.2")
}

// Задача выполнения интеграционных тестов — отдельный «ран»
tasks.register<Test>("integrationTest") {
    description = "Runs integration tests."
    group = "verification"
    testClassesDirs = sourceSets["integrationTest"].output.classesDirs
    classpath = sourceSets["integrationTest"].runtimeClasspath
    useJUnitPlatform()
    testLogging {
        events("passed", "failed")
    }
}

// Общий «щит качества»: при gradle check запустить и unit-, и integration-тесты, и покрытие
tasks.named("check") {
    dependsOn("integrationTest", "jacocoTestReport")
}

// Приложение (если вы делаете runnable app): указываем главный класс для задачи run
application {
    // Для Kotlin "main" в файле Main.kt — указываем MainKt
    mainClass.set("com.example.MainKt")
}

// Глобальная стратегия разрешения зависимостей (пример: форс безопасной версии log4j-core)
configurations.all {
    resolutionStrategy.eachDependency {
        if (requested.group == "org.apache.logging.log4j" && requested.name == "log4j-core") {
            useVersion("2.17.2")
            because("Fix CVE-2021-44228 (Log4Shell)")
        }
    }
}

// Примеры дополнительных настроек задач (необязательные):
// tasks.jar {
//     manifest {
//         attributes["Main-Class"] = "com.example.MainKt"
//     }
// }

// Полезные команды (вспомогательные задачи Gradle):
// - ./gradlew clean       — удалить build/
// - ./gradlew build       — полная сборка (assemble + check)
// - ./gradlew test        — юнит-тесты
// - ./gradlew integrationTest  — интеграционные тесты
// - ./gradlew jacocoTestReport — отчёты покрытия
// - ./gradlew dependencies --configuration runtimeClasspath  — граф зависимостей
// - ./gradlew dependencyInsight --dependency log4j-core     — детали разрешения версии
```