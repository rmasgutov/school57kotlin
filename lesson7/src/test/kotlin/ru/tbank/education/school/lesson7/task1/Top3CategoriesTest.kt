package ru.tbank.education.school.lesson7.task1

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import ru.tbank.education.school.lesson7.practise.task1.Transfer
import ru.tbank.education.school.lesson7.practise.task1.top3Categories

class Top3CategoriesTest {

    @Test
    fun `finds top 3 categories by total amount`() {
        val transfers = listOf(
            Transfer("Food", 100.0),
            Transfer("Travel", 200.0),
            Transfer("Entertainment", 50.0),
            Transfer("Food", 150.0),
            Transfer("Bills", 300.0),
            Transfer("Travel", 50.0),
            Transfer("Education", 400.0)
        )

        val result = top3Categories(transfers)

        // Expected totals:
        // Food = 250, Travel = 250, Bills = 300, Education = 400
        // Sorted descending: Education(400), Bills(300), Food(250) (Travel тоже 250, но после по порядку)
        assertEquals(3, result.size)
        assertEquals("Education", result[0].first)
        assertEquals(400.0, result[0].second, 0.001)
        assertEquals("Bills", result[1].first)
        assertEquals("Food", result[2].first)
    }

    @Test
    fun `handles ties and less than 3 categories`() {
        val transfers = listOf(
            Transfer("A", 100.0),
            Transfer("B", 100.0)
        )

        val result = top3Categories(transfers)
        assertEquals(2, result.size)
        assertTrue(result.any { it.first == "A" })
        assertTrue(result.any { it.first == "B" })
    }

    @Test
    fun `returns empty list when no transfers`() {
        val result = top3Categories(emptyList())
        assertTrue(result.isEmpty())
    }

    @Test
    fun `sums multiple transfers per category correctly`() {
        val transfers = listOf(
            Transfer("Groceries", 10.0),
            Transfer("Groceries", 15.0),
            Transfer("Fuel", 50.0),
            Transfer("Fuel", 5.0),
            Transfer("Games", 30.0)
        )

        val result = top3Categories(transfers)

        val groceries = result.find { it.first == "Groceries" }!!.second
        val fuel = result.find { it.first == "Fuel" }!!.second
        val games = result.find { it.first == "Games" }!!.second

        assertEquals(25.0, groceries, 0.001)
        assertEquals(55.0, fuel, 0.001)
        assertEquals(30.0, games, 0.001)
    }
}
