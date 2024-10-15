package ru.tbank.education.school.lesson5

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jsonMapper
import com.fasterxml.jackson.module.kotlin.kotlinModule
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import ru.tbank.education.school.ru.tbank.education.school.lesson5.Person5
import ru.tbank.education.school.ru.tbank.education.school.lesson5.Person6

class JsonSerializationTest {

    @Test
    fun `Не должны сериализовываться свойства с null значениям Настройка через аннотацию`() {
        // given
        val client = Person5()
        val objectMapper = jsonMapper {
            addModule(kotlinModule())
            addModule(Jdk8Module())
            addModule(JavaTimeModule())
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
        val objectMapper = jsonMapper {
            addModule(kotlinModule())
            addModule(Jdk8Module())
            addModule(JavaTimeModule())
        }.setSerializationInclusion(JsonInclude.Include.NON_NULL)

        // when
        val data = objectMapper.writeValueAsString(client)

        // then
        assertEquals("{}", data)
    }
}
