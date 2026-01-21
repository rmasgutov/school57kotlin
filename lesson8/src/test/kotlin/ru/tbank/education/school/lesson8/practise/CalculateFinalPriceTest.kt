package ru.tbank.education.school.lesson8.practise

import org.junit.jupiter.api.DisplayName
import kotlin.test.Test
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
class CalculateFinalPriceTest{
    @Test
    @DisplayName("Обычный случай: basePrice = 1000, discount = 10%, tax = 20% → проверить корректность формулы.")
    fun happypathTest(){
        val basePrice:Double = 1000.0
        val discount = 10
        val tax = 20
        val finalPrice=calculateFinalPrice(basePrice,discount,tax)
        assertEquals(1080.0,finalPrice)
    }
}
