# Организация проекта

## Структура проекта
Каждый модуль проекта - отдельный урок согласно плану занятий.

Описание занятия и его задания находятся в соответствующей директории.

## Сборка проекта
Проект организован как multi project build с использованием buildSrc

[Подробнее в документации Gradle](https://docs.gradle.org/current/userguide/intro_multi_project_builds.html#1_multi_project_builds_using_buildsrc)

[Пример официальной документации](https://docs.gradle.org/current/samples/sample_convention_plugins.html)

[Пример инициализации проекта через gradle init](https://docs.gradle.org/current/samples/sample_building_kotlin_applications_multi_project.html)

## Соглашения по проекту
* Проверка форматирования - detekt
* Написание юнит тестов - junit5
* Мокирование зависимостей и проверка вызовов - mockk
* Проверка структуры проекта - archunit
* Проверка структуры классов и наименований - konsist