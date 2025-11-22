package ru.tbank.education.school.lesson8.practise.smartcast

data class Product(
    val id: Int,
    val name: String,
    val price: Double?,
    val category: String?
)

/**
 * 1. Напишите функцию, которая находит самый дорогой товар и возвращает его цену.
 *     - Если цена null, товар не должен учитываться.
 *     - Если все цены null, вернуть null.
 *
 * 2. Напишите функцию, которая группирует товары по категориям:
 *     - Для товаров с category = null использовать категорию "Без категории"
 *     - Вернуть Map<String, List<Product>>
 *
 * 3. Используйте filterNotNull(), safeCall, Elvis operator и groupBy
 */

fun getMostExpensivePrice(products: List<Product>): Double? {
    TODO()
}

fun groupProductsByCategory(products: List<Product>): Map<String, List<Product>> {
    TODO()
}
