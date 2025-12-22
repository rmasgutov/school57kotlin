package ru.tbank.education.school.lesson10.basics.validation

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.PROPERTY)
annotation class ShouldBeCapitalized

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.PROPERTY)
annotation class ShouldBeEmail

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.PROPERTY)
annotation class Min(val value: Int)

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.PROPERTY)
annotation class ShouldContainSpecialSymbols

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class Validated