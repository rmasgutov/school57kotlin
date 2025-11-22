package ru.tbank.education.school.lesson7.practise.task1// 6) GROUPBY — топ категорий по расходам

/**
 * Задание: Найди топ-3 категорий по расходам.
 *
 * Дано: список переводов с категорией и суммой денежных средств.
 *
 * Нужно:
 *  1) Сгруппировать по категории.
 *  2) Посчитать сумму по каждой.
 *  3) Вернуть список из трёх пар (category to total) в порядке убывания total.
 *
 */
data class Transfer(val category: String, val amount: Double)

fun top3Categories(transfers: List<Transfer>): List<Pair<String, Double>> {
    TODO()
}