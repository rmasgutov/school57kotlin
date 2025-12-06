package ru.tbank.education.school.lesson10.dependency.injection.annotations

/**
 * Аннотация @Component помечает классы как компоненты для Dependency Injection.
 *
 * Аналог @Component из Spring Framework.
 * Классы, помеченные этой аннотацией, будут автоматически найдены
 * при сканировании пакетов и зарегистрированы как бины (singleton).
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class Component
