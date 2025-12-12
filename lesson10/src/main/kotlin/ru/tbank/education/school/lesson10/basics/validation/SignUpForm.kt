package ru.tbank.education.school.lesson10.basics.validation

import kotlin.reflect.KClass
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.hasAnnotation
import kotlin.reflect.full.memberProperties

@Validated
data class SignUpForm(
    @ShouldBeCapitalized
    val firstName: String,
    @ShouldBeCapitalized
    val lastName: String,
    @ShouldBeEmail
    val email: String,
    @Min(8)
    @ShouldContainSpecialSymbols
    val password: String
)

inline fun <reified T> T.validate(): List<String> {
    val errors = mutableListOf<String>()
    val objectClass = T::class as KClass<*>

    if (objectClass.hasAnnotation<Validated>()) {
        objectClass.memberProperties.forEach { property ->
            if (property.hasAnnotation<Min>()) {
                val value = property.getter.call(this) as? String

                if (value != null) {
                    val minValue = property.findAnnotation<Min>()!!.value
                    if (value.length < minValue) {
                        errors.add("[${objectClass.simpleName}.${property.name}] Длина строки должна быть больше или равно $minValue")
                    }
                }
            }
            if (property.hasAnnotation<ShouldBeCapitalized>()) {
                val value = property.getter.call(this) as? String

                if (value != null) {
                    if (value.firstOrNull()?.isLowerCase() ?: true) {
                        errors.add("[${objectClass.simpleName}.${property.name}] Первая буква в строке должна быть заглавная")
                    }
                }
            }

            if (property.hasAnnotation<ShouldBeEmail>()) {
                val value = property.getter.call(this) as? String

                if (value != null) {
                    if (!Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\$").matches(value)) {
                        errors.add("[${objectClass.simpleName}.${property.name}] Невалидный адрес почты: $value")
                    }
                }
            }

            if (property.hasAnnotation<ShouldContainSpecialSymbols>()) {
                val value = property.getter.call(this) as? String
                val specialSymbols = setOf('$', '!', '_')
                if (value != null) {
                    if (value.none { it in specialSymbols }) {
                        errors.add("[${objectClass.simpleName}.${property.name}] В строке должны быть переданы специальные символы $specialSymbols")
                    }
                }
            }
        }
    }

    return errors
}

fun main() {
    println("Ошибки валидации:")
    SignUpForm(
        firstName = "john",
        lastName = "doe",
        email = "johndoe@example.com",
        password = "0000"
    ).validate().forEach {
        println(it)
    }
}

