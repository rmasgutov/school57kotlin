package ru.tbank.education.school.lesson5
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.readValue
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import ru.tbank.education.school.ru.tbank.education.school.lesson5.Person1
import ru.tbank.education.school.ru.tbank.education.school.lesson5.Person2
import ru.tbank.education.school.ru.tbank.education.school.lesson5.Person3
import ru.tbank.education.school.ru.tbank.education.school.lesson5.Person4
import java.time.LocalDate

class JsonDeserializationTest {
    //second edition
    @Test
    fun `Имена свойств совпадают`() {
        // given
        val data =
            """{"firstName": "Иван", "lastName": "Иванов", "middleName": "Иванович", "passportNumber": "123456", "passportSerial": "1234", "birthDate": "1990-01-01"}"""
        val objectMapper = ObjectMapper().registerModule(
            KotlinModule.Builder().build()
        ).registerModule(JavaTimeModule())
        // when
        val client = objectMapper.readValue<Person1>(data)
        // then
        assertEquals("Иван", client.firstName)
        assertEquals("Иванов", client.lastName)
        assertEquals("Иванович", client.middleName)
        assertEquals("123456", client.passportNumber)
        assertEquals("1234", client.passportSerial)
        assertEquals(LocalDate.of(1990, 1, 1), client.birthDate)
    }
    @Test
    fun `В JSON есть лишние свойства Настроить ObjectMapper`() {
        // given
        val data =
            """{"city": "Москва", "firstName": "Иван", "lastName": "Иванов", "middleName": "Иванович", "passportNumber": "123456", "passportSerial": "1234", "birthDate": "1990-01-01"}"""
        val objectMapper = ObjectMapper().registerModule(
            KotlinModule.Builder().build()
        ).registerModule(JavaTimeModule()).configure(
            DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
            false
        )
        // when
        val client = objectMapper.readValue<Person1>(data)
        // then
        assertEquals("Иван", client.firstName)
        assertEquals("Иванов", client.lastName)
        assertEquals("Иванович", client.middleName)
        assertEquals("123456", client.passportNumber)
        assertEquals("1234", client.passportSerial)
        assertEquals(LocalDate.of(1990, 1, 1), client.birthDate)
    }
    @Test
    fun `В JSON есть лишние свойства Настроить через аннотацию`() {
        // given
        val data =
            """{"city": "Москва", "firstName": "Иван", "lastName": "Иванов", "middleName": "Иванович", "passportNumber": "123456", "passportSerial": "1234", "birthDate": "1990-01-01"}"""
        val objectMapper = ObjectMapper().registerModule(
            KotlinModule.Builder().build()
        ).registerModule(JavaTimeModule())
        // when
        val client = objectMapper.readValue<Person1>(data)
        // then
        assertEquals("Иван", client.firstName)
        assertEquals("Иванов", client.lastName)
        assertEquals("Иванович", client.middleName)
        assertEquals("123456", client.passportNumber)
        assertEquals("1234", client.passportSerial)
        assertEquals(LocalDate.of(1990, 1, 1), client.birthDate)
    }
    @Test
    fun `Имена свойств различаются`() {
        // given
        val data =
            """{"name": "Иван", "lastName": "Иванов", "middleName": "Иванович", "passportNumber": "123456", "passportSerial": "1234", "birthDate": "1990-01-01"}"""
        val objectMapper = ObjectMapper().findAndRegisterModules()
        // when
        val client = objectMapper.readValue<Person2>(data)
        // then
        assertEquals("Иван", client.firstName)
        assertEquals("Иванов", client.lastName)
        assertEquals("Иванович", client.middleName)
        assertEquals("123456", client.passportNumber)
        assertEquals("1234", client.passportSerial)
        assertEquals(LocalDate.of(1990, 1, 1), client.birthDate)
    }
    @Test
    fun `Кастомный формат даты`() {
        // given
        val data =
            """{"firstName": "Иван", "lastName": "Иванов", "middleName": "Иванович", "passportNumber": "123456", "passportSerial": "1234", "birthDate": "01-01-1990"}"""
        val objectMapper = ObjectMapper().findAndRegisterModules()
        // when
        val client = objectMapper.readValue<Person3>(data)
        // then
        assertEquals("Иван", client.firstName)
        assertEquals("Иванов", client.lastName)
        assertEquals("Иванович", client.middleName)
        assertEquals("123456", client.passportNumber)
        assertEquals("1234", client.passportSerial)
        assertEquals(LocalDate.of(1990, 1, 1), client.birthDate)
    }
    @Test
    fun `Поддержка optional типа`() {
        // given
        val data1 =
            """{"firstName": "Иван", "lastName": "Иванов", "middleName": "Иванович", "passportNumber": "123456", "passportSerial": "1234", "birthDate": "1990-01-01"}"""
        val objectMapper = ObjectMapper().findAndRegisterModules()
        // when
        val client1 = objectMapper.readValue<Person4>(data1)
        // then
        assertEquals("Иванович", client1.middleName.get())
        // given
        val data2 =
            """{"firstName": "Иван", "lastName": "Иванов", "passportNumber": "123456", "passportSerial": "1234", "birthDate": "1990-01-01"}"""
        // when
        val client2 = objectMapper.readValue<Person4>(data2)
        // then
        assertNotNull(client2.middleName)
        assertFalse(client2.middleName.isPresent)
    }
}