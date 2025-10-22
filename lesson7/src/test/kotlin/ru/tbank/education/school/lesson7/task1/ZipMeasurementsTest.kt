package ru.tbank.education.school.lesson7.task1

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import ru.tbank.education.school.lesson7.practise.task1.Measurement
import ru.tbank.education.school.lesson7.practise.task1.zipMeasurements
import java.time.LocalDate

class ZipMeasurementsTest {

    @Test
    fun `zips equal-length lists into measurements`() {
        val dates = listOf(
            LocalDate.of(2024, 1, 1),
            LocalDate.of(2024, 1, 2),
            LocalDate.of(2024, 1, 3)
        )
        val values = listOf(10.0, 20.5, 30.2)

        val result = zipMeasurements(dates, values)

        assertEquals(3, result.size)
        assertEquals(Measurement(dates[0], 10.0), result[0])
        assertEquals(Measurement(dates[1], 20.5), result[1])
        assertEquals(Measurement(dates[2], 30.2), result[2])
    }

    @Test
    fun `zips up to the shorter list length`() {
        val dates = listOf(
            LocalDate.of(2024, 1, 1),
            LocalDate.of(2024, 1, 2)
        )
        val values = listOf(10.0)

        val result = zipMeasurements(dates, values)

        assertEquals(1, result.size)
        assertEquals(Measurement(dates[0], 10.0), result[0])
    }

    @Test
    fun `handles empty lists`() {
        val result = zipMeasurements(emptyList(), emptyList())
        assertTrue(result.isEmpty())
    }

    @Test
    fun `handles non-empty dates with empty values`() {
        val dates = listOf(LocalDate.of(2024, 1, 1))
        val result = zipMeasurements(dates, emptyList())
        assertTrue(result.isEmpty())
    }
}
