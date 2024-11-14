package ru.tbank.education.school.lesson5

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class JsonSerializationTest {
    private val objectMapper = ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)

    @Test
    fun `Не должны сериализовываться свойства с null значениями Настройка через аннотацию`() {
        // given
        val client = Person5()
        // when
        val data = objectMapper.writeValueAsString(client)
        // then
        assertEquals("{}", data)
    }

    @Test
    fun `Не должны сериализовываться свойства с null значениями Настройка через ObjectMapper`() {
        // given
        val client = Person6()
        // when
        val data = objectMapper.writeValueAsString(client)
        // then
        assertEquals("{}", data)
    }
}
