package ru.tbank.education.school.lesson8.practise

/**
 * Рассчитывает итоговую стоимость заказа.
 *
 * @param basePrice — базовая цена (в рублях)
 * @param discountPercent — скидка в процентах (0–100)
 * @param taxPercent — налог в процентах (может быть 0–30)
 * @return итоговая стоимость (минимум 0)
 */
fun calculateFinalPrice(
    basePrice: Double,
    discountPercent: Int,
    taxPercent: Int
): Double {
    require(basePrice >= 0) { "Base price must be >= 0" }
    require(discountPercent in 0..100) { "Discount must be 0..100" }
    require(taxPercent in 0..30) { "Tax must be 0..30" }

    val priceAfterDiscount = basePrice * (1 - discountPercent / 100.0)
    val taxAmount = priceAfterDiscount * (taxPercent / 100.0)

    val finalPrice = priceAfterDiscount + taxAmount

    return finalPrice.coerceAtLeast(0.0)
}
