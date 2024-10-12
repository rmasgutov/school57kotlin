package ru.tbank.education.school.lesson5

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.readValue
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.util.*

class JacksonModulesExampleTest {

    @Test
    fun `Пример использования модулей`() {
        val objectMapper = ObjectMapper()
            .registerModules(KotlinModule.Builder().build(), JavaTimeModule(), Jdk8Module())
            .enable(SerializationFeature.INDENT_OUTPUT)
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        val initialObject = UserJdk8("Василий", Optional.of("Васильев"), LocalDate.of(1990, 1, 1))
        val serializedData = objectMapper.writeValueAsString(initialObject)
        println("Сериализованные данные:")
        println(serializedData)
        val deserializedObject = objectMapper.readValue<UserJdk8>(serializedData)
        println("Исходный и десериализованный объекты равны: ${initialObject == deserializedObject}")
    }
}

data class UserJdk8(
    val firstName: String,
    val lastName: Optional<String>,
    @JsonFormat(pattern = "dd-MM-yyyy")
    val birthDate: LocalDate,
)
