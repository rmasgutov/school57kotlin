package ru.tbank.education.school.lesson5

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import ru.tbank.education.school.ru.tbank.education.school.lesson5.Person5
import ru.tbank.education.school.ru.tbank.education.school.lesson5.Person6
import com.fasterxml.jackson.annotation.JsonInclude

class JsonSerializationTest {

    @Test
    fun `Не должны сериализовываться свойства с null значениям Настройка через аннотацию`() {
        // given
        val client = Person5()

        val objectMapper = ObjectMapper().apply {
            registerModule(KotlinModule.Builder().build()).registerModule(JavaTimeModule())
        }

        // when
        val data = objectMapper.writeValueAsString(client)

        // then
        assertEquals("{}", data)
    }

    @Test
    fun `Не должны сериализовываться свойства с null значениям Настройка через ObjectMapper`() {
        // given
        val client = Person6()

        val objectMapper = ObjectMapper().apply {
            registerModule(KotlinModule.Builder().build()).registerModule(JavaTimeModule()).setSerializationInclusion(JsonInclude.Include.NON_NULL)
        }

        // when
        val data = objectMapper.writeValueAsString(client)

        // then
        assertEquals("{}", data)
    }
}
