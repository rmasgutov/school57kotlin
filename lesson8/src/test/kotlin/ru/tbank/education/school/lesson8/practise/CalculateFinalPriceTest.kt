package ru.tbank.education.school.lesson8.practise

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.test.assertEquals

/**
 *
 * Сценарии для тестирования:
 *
 * 1. Позитивные сценарии (happy path):
 *    - Обычный случай: basePrice = 1000, discount = 10%, tax = 20% → проверить корректность формулы.
 *    - Без скидки: discountPercent = 0 → итог = basePrice + налог.
 *    - Без налога: taxPercent = 0 → итог = basePrice минус скидка.
 *    - Без скидки и без налога: итог = basePrice.
 *
 * 2. Негативные сценарии (исключения):
 *    - Отрицательная цена: basePrice < 0 → IllegalArgumentException.
 *    - Скидка вне диапазона: discountPercent < 0 или > 100 → IllegalArgumentException.
 *    - Налог вне диапазона: taxPercent < 0 или > 30 → IllegalArgumentException.
 */
class CalculateFinalPriceTest {
    @ParameterizedTest(name = "basePrice = {0}, discount = {1}, tax = {2}")
    @CsvSource(
        "1000.0, 10, 20, 1080.0",
        "1000.0, 0, 20, 1200.0",
        "1000.0, 10, 0, 900.0",
        "1000.0, 0, 0, 1000.0"
    )
    fun happyPathTest(basePrice: Double, discount: Int, tax: Int, expectedValue: Double) {

        val finalPrice = calculateFinalPrice(basePrice, discount, tax)

        assertEquals(expectedValue, finalPrice)
    }
}