package ru.tbank.education.school.lesson7.task1

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import ru.tbank.education.school.lesson7.practise.task1.Person
import ru.tbank.education.school.lesson7.practise.task1.Transaction
import ru.tbank.education.school.lesson7.practise.task1.areAllPositive
import ru.tbank.education.school.lesson7.practise.task1.averageAge
import ru.tbank.education.school.lesson7.practise.task1.countLongWords
import ru.tbank.education.school.lesson7.practise.task1.filterEvenNumbers
import ru.tbank.education.school.lesson7.practise.task1.groupWordsByFirstLetter
import ru.tbank.education.school.lesson7.practise.task1.lowerCaseNames
import ru.tbank.education.school.lesson7.practise.task1.mergeLists
import ru.tbank.education.school.lesson7.practise.task1.squaredEvenNumbers
import ru.tbank.education.school.lesson7.practise.task1.sumPositive
import ru.tbank.education.school.lesson7.practise.task1.sumPrices
import ru.tbank.education.school.lesson7.practise.task1.totalFoodExpenses
import ru.tbank.education.school.lesson7.practise.task1.uniqueElements

class Lesson7PractiseTask1Test {

    @Test
    fun `filterEvenNumbers returns only even numbers`() {
        val result = filterEvenNumbers(listOf(1, 2, 3, 4, 5, 6))
        assertEquals(listOf(2, 4, 6), result)
    }

    @Test
    fun `filterEvenNumbers with no even numbers returns empty list`() {
        val result = filterEvenNumbers(listOf(1, 3, 5))
        assertTrue(result.isEmpty())
    }

    @Test
    fun `lowerCaseNames converts all names to lowercase`() {
        val result = lowerCaseNames(listOf("Anna", "IVAN", "OlGa"))
        assertEquals(listOf("anna", "ivan", "olga"), result)
    }

    @Test
    fun `sumPrices sums all elements`() {
        val result = sumPrices(listOf(1, 2, 3, 4, 5))
        assertEquals(15, result)
    }

    @Test
    fun `averageAge calculates average age`() {
        val people = listOf(
            Person("Anna", 20),
            Person("Ivan", 30),
            Person("Olga", 40)
        )
        val result = averageAge(people)
        assertEquals(30.0, result)
    }

    @Test
    fun `sumPositive sums only positive numbers`() {
        val result = sumPositive(listOf(-5, 10, -2, 3))
        assertEquals(13, result)
    }

    @Test
    fun `sumPositive returns 0 when no positives`() {
        val result = sumPositive(listOf(-1, -2, -3))
        assertEquals(0, result)
    }

    @Test
    fun `groupWordsByFirstLetter groups words correctly`() {
        val words = listOf("apple", "banana", "apricot", "blueberry")
        val result = groupWordsByFirstLetter(words)

        assertEquals(listOf("apple", "apricot"), result['a'])
        assertEquals(listOf("banana", "blueberry"), result['b'])
    }

    @Test
    fun `areAllPositive returns true when all positive`() {
        val result = areAllPositive(listOf(1, 2, 3, 4))
        assertTrue(result)
    }

    @Test
    fun `areAllPositive returns false when any negative`() {
        val result = areAllPositive(listOf(1, -2, 3))
        assertFalse(result)
    }

    @Test
    fun `countLongWords counts words longer than 3`() {
        val result = countLongWords(listOf("a", "word", "two", "three", "ok"))
        assertEquals(2, result)
    }

    @Test
    fun `countLongWords returns 0 for short words only`() {
        val result = countLongWords(listOf("a", "bb", "ccc"))
        assertEquals(0, result)
    }

    @Test
    fun `squaredEvenNumbers returns squares of even numbers`() {
        val result = squaredEvenNumbers(listOf(1, 2, 3, 4, 5))
        assertEquals(listOf(4, 16), result)
    }

    @Test
    fun `uniqueElements removes duplicates`() {
        val result = uniqueElements(listOf(1, 2, 2, 3, 1, 4))
        assertEquals(listOf(1, 2, 3, 4), result)
    }

    @Test
    fun `mergeLists merges two lists correctly`() {
        val result = mergeLists(listOf(1, 2), listOf(3, 4))
        assertEquals(listOf(1, 2, 3, 4), result)
    }

    @Test
    fun `mergeLists works with empty lists`() {
        val result = mergeLists(emptyList(), listOf(1, 2))
        assertEquals(listOf(1, 2), result)
    }

    @Test
    fun `totalFoodExpenses sums only FOOD category`() {
        val txs = listOf(
            Transaction(1, 100, "FOOD"),
            Transaction(2, 200, "TRANSPORT"),
            Transaction(3, 300, "FOOD")
        )
        val result = totalFoodExpenses(txs)
        assertEquals(400, result)
    }

    @Test
    fun `totalFoodExpenses returns 0 when no FOOD transactions`() {
        val txs = listOf(
            Transaction(1, 100, "OTHER"),
            Transaction(2, 50, "CAR")
        )
        val result = totalFoodExpenses(txs)
        assertEquals(0, result)
    }

    @Test
    fun `filterEvenNumbers handles empty list`() {
        assertTrue(filterEvenNumbers(emptyList()).isEmpty())
    }

    @Test
    fun `lowerCaseNames handles empty list`() {
        assertTrue(lowerCaseNames(emptyList()).isEmpty())
    }

    @Test
    fun `averageAge handles one person`() {
        val people = listOf(Person("Solo", 50))
        assertEquals(50.0, averageAge(people))
    }

    @Test
    fun `groupWordsByFirstLetter handles single word`() {
        val result = groupWordsByFirstLetter(listOf("hello"))
        assertEquals(listOf("hello"), result['h'])
    }
}
