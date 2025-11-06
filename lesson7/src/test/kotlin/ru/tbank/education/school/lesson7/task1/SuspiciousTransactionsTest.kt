package ru.tbank.education.school.lesson7.task1

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import ru.tbank.education.school.lesson7.practise.task1.Tx
import ru.tbank.education.school.lesson7.practise.task1.TxCategory
import ru.tbank.education.school.lesson7.practise.task1.suspiciousTransactions

class SuspiciousTransactionsTest {

    private val now = System.currentTimeMillis()

    @Test
    fun `должен находить операции превышающие порог threshold`() {
        val txs = listOf(
            Tx(2000.0, TxCategory.SHOPPING, now, "RU"),
            Tx(500.0, TxCategory.FOOD, now, "RU"),
            Tx(10000.0, TxCategory.OTHER, now, "RU")
        )

        val result = suspiciousTransactions(txs, threshold = 1500.0, foreignThreshold = 3000.0)

        Assertions.assertEquals(2, result.size)
        Assertions.assertTrue(result.any { it.amount == 2000.0 })
        Assertions.assertTrue(result.any { it.amount == 10000.0 })
    }

    @Test
    fun `должен находить операции с категориями CRYPTO и GAMBLING`() {
        val txs = listOf(
            Tx(100.0, TxCategory.CRYPTO, now, "RU"),
            Tx(50.0, TxCategory.GAMBLING, now, "RU"),
            Tx(400.0, TxCategory.FOOD, now, "RU")
        )

        val result = suspiciousTransactions(txs, threshold = 1000.0, foreignThreshold = 3000.0)

        Assertions.assertEquals(2, result.size)
        Assertions.assertTrue(result.all { it.category == TxCategory.CRYPTO || it.category == TxCategory.GAMBLING })
    }

    @Test
    fun `должен находить операции из другой страны с суммой выше foreignThreshold`() {
        val txs = listOf(
            Tx(5000.0, TxCategory.SHOPPING, now, "US"),
            Tx(3000.0, TxCategory.FOOD, now, "FR"),
            Tx(1000.0, TxCategory.FOOD, now, "RU")
        )

        val result = suspiciousTransactions(txs, threshold = 3000.0, foreignThreshold = 2500.0)

        Assertions.assertEquals(2, result.size)
        Assertions.assertTrue(result.any { it.country == "US" })
        Assertions.assertTrue(result.any { it.country == "FR" })
    }

    @Test
    fun `не должен включать безопасные операции`() {
        val txs = listOf(
            Tx(500.0, TxCategory.FOOD, now, "RU"),
            Tx(1000.0, TxCategory.SHOPPING, now, "RU"),
            Tx(2500.0, TxCategory.TRANSFER, now, "RU")
        )

        val result = suspiciousTransactions(txs, threshold = 5000.0, foreignThreshold = 3000.0)

        Assertions.assertTrue(result.isEmpty(), "Безопасные операции не должны попасть в результат")
    }

    @Test
    fun `должен корректно работать при пустом списке`() {
        val result = suspiciousTransactions(emptyList(), threshold = 1000.0, foreignThreshold = 3000.0)
        Assertions.assertTrue(result.isEmpty(), "При пустом списке результат должен быть пустым")
    }

    @Test
    fun `операции могут попадать под несколько критериев одновременно`() {
        val txs = listOf(
            Tx(8000.0, TxCategory.CRYPTO, now, "US"), // подходит по всем условиям
            Tx(100.0, TxCategory.CRYPTO, now, "RU"),  // подходит по категории
            Tx(9000.0, TxCategory.SHOPPING, now, "RU") // подходит по сумме
        )

        val result = suspiciousTransactions(txs, threshold = 5000.0, foreignThreshold = 3000.0)

        Assertions.assertEquals(3, result.size)
    }
}