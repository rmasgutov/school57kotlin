package ru.tbank.education.school.lesson5

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import ru.tbank.education.school.ru.tbank.education.school.lesson5.Person5
import ru.tbank.education.school.ru.tbank.education.school.lesson5.Person6

class JsonSerializationTest {

    @Test
    fun `Не должны сериализовываться свойства с null значениям Настройка через аннотацию`() {
        // given
        val client = Person5()
        val objectMapper = ObjectMapper()

        // when
        val data = objectMapper.writeValueAsString(client)

        // then
        assertEquals("{}", data)
    }

    @Test
    fun `Не должны сериализовываться свойства с null значениям Настройка через ObjectMapper`() {
        // given
        val client = Person6()
        val objectMapper = ObjectMapper()

        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)
        // when
        val data = objectMapper.writeValueAsString(client)

        // then
        assertEquals("{}", data)
    }
}

