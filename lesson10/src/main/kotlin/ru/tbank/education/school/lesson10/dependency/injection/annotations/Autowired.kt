package ru.tbank.education.school.lesson10.dependency.injection.annotations

/**
 * Аннотация @Autowired помечает поля для автоматического внедрения зависимостей.
 *
 * Аналог @Autowired из Spring Framework.
 * InjectionFactory автоматически найдёт подходящий бин и внедрит его в поле,
 * помеченное этой аннотацией, используя Reflection API.
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
annotation class Autowired
