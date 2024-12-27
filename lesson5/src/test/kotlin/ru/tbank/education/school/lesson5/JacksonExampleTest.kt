package ru.tbank.education.school.lesson5

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.readValue
import org.junit.jupiter.api.Test

class JacksonExampleTest {

    @Test
    fun `Пример использования Jackson`() {
        val objectMapper = ObjectMapper()
            .registerModule(KotlinModule.Builder().build())
            .enable(SerializationFeature.INDENT_OUTPUT)
        val initialObject = JacksonExample("firstValue", "secondValue")
        val serializedData = objectMapper.writeValueAsString(initialObject)
        println("Сериализованные данные: $serializedData")
        val deserializedObject = objectMapper.readValue<JacksonExample>(serializedData)
        println("Исходный и десериализованный объекты равны: ${initialObject == deserializedObject}")
    }
}

data class JacksonExample(
    val first: String,
    val second: String?,
)