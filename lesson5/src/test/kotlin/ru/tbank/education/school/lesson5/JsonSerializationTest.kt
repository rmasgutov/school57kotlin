package ru.tbank.education.school.lesson5
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import ru.tbank.education.school.ru.tbank.education.school.lesson5.Person5
import ru.tbank.education.school.ru.tbank.education.school.lesson5.Person6
class JsonSerializationTest {
    private val objectMapper = ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)
    @Test
    fun `Не должны сериализовываться свойства с null значениями Настройка через аннотацию`() {
        val client = Person5()
        val data = objectMapper.writeValueAsString(client)
        assertEquals("{}", data)
    }
    @Test
    fun `Не должны сериализовываться свойства с null значениями Настройка через ObjectMapper`() {
        val client = Person6()
        val data = objectMapper.writeValueAsString(client)
        assertEquals("{}", data)
    }
}
///