package ru.tbank.education.school.lesson5

import com.fasterxml.jackson.annotation.JsonInclude.Include
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import ru.tbank.education.school.ru.tbank.education.school.lesson5.Person5
import ru.tbank.education.school.ru.tbank.education.school.lesson5.Person6

class JsonSerializationTest {
    //Прощай, от всех вокзалов поезда уходят в дальние края.
    //Прощай, мы расстаемся навсегда под белым небом января.
    //Прощай, и ничего не обещай, ничего не говори,
    //А чтоб понять мою печаль в пустое небо посмотри.
    //Ты помнишь, плыли в вышине, и вдруг погасли две звезды,
    //Но лишь теперь понятно мне, что это были я и ты.
    //
    //Прощай, среди снегов, среди зимы никто нам лета не вернет.
    //Прощай, вернуть назад не сможем мы в июльских звездах небосвод.
    //Прощай, и ничего не обещай, и ничего не говори,
    //А чтоб понять мою печаль, в пустое небо посмотри.
    //Ты помнишь, плыли в вышине, и вдруг погасли две звезды,
    //Но лишь теперь понятно мне, что это были я и ты.
    //
    //Прощай, уже вдали встает заря, и день приходит в города.
    //Прощай, мы расстаемся навсегда под белым небом января.
    //Прощай, и ничего не обещай, ничего не говори,
    //А чтоб понять мою печаль в пустое небо посмотри.
    //Ты помнишь, плыли в вышине, и вдруг погасли две звезды,
    //Но лишь теперь понятно мне, что это были я и ты.
    @Test
    fun `Не должны сериализовываться свойства с null значениям Настройка через аннотацию`() {
        // given
        val client = Person5()
        val objectMapper = ObjectMapper().registerModule(
            KotlinModule.Builder().build()
        ).registerModule(JavaTimeModule())

        // when
        val data = objectMapper.writeValueAsString(client)

        // then
        assertEquals("{}", data)
    }

    @Test
    fun `Не должны сериализовываться свойства с null значениям Настройка через ObjectMapper`() {
        // given
        val client = Person6()
        val objectMapper = ObjectMapper().registerModule(
            KotlinModule.Builder().build()
        ).registerModule(JavaTimeModule()).setSerializationInclusion(Include.NON_NULL)

        // when
        val data = objectMapper.writeValueAsString(client)
        // then
        assertEquals("{}", data)
    }
}
