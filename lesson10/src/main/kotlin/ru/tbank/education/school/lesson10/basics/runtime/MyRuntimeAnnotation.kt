package ru.tbank.education.school.lesson10.basics.runtime

/**
 * Пример пользовательской аннотации с retention policy RUNTIME.
 * Такие аннотации доступны через Reflection API во время выполнения программы.
 *
 * @property metainfo метаинформация, которая будет сохранена в аннотации
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class MyRuntimeAnnotation(val metainfo: String)