package ru.tbank.education.school.lesson8.homework.payments
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import ru.tbank.education.school.lesson8.homework.library.Book
import ru.tbank.education.school.lesson8.homework.payments.PaymentProcessor

class PaymentProcessorTest {
    private lateinit var processor: PaymentProcessor

    @BeforeEach
    fun setUp() {
        processor = PaymentProcessor()
    }

    @Test
    @DisplayName("Должен выбросить исключение при отрицательной сумме")
    fun test1() {
        assertThrows(IllegalArgumentException::class.java) {
            processor.processPayment(
                amount = -100,
                cardNumber = "4111111111111111",
                expiryMonth = 12,
                expiryYear = 2028,
                currency = "USD",
                customerId = "customer123"
            )
        }
    }

    @Test
    @DisplayName("Должен выбросить исключение при номере карты с недопустимой длиной")
    fun test2() {
        assertThrows(IllegalArgumentException::class.java) {
            processor.processPayment(
                amount = 100,
                cardNumber = "41111",
                expiryMonth = 12,
                expiryYear = 2009,
                currency = "USD",
                customerId = "customer123"
            )
        }
    }

    @Test
    @DisplayName("Должен выбросить исключение при не допустимом номере месяца")
    fun test3() {
        assertThrows(IllegalArgumentException::class.java) {
            processor.processPayment(
                amount = 100,
                cardNumber = "411114111141111",
                expiryMonth = 15,
                expiryYear = 2028,
                currency = "USD",
                customerId = "customer123"
            )
        }
    }

    @Test
    @DisplayName("Должен выбросить исключение при пустом currency")
    fun test4() {
        assertThrows(IllegalArgumentException::class.java) {
            processor.processPayment(
                amount = 100,
                cardNumber = "411114111141111",
                expiryMonth = 11,
                expiryYear = 2028,
                currency = "",
                customerId = "customer123"
            )
        }
    }

    @Test
    @DisplayName("Должен выбросить сообщение при подозрительном номере карты")
    fun test5() {
        val result = processor.processPayment(
            amount = 100,
            cardNumber = "444411111111111",
            expiryMonth = 11,
            expiryYear = 2028,
            currency = "USD",
            customerId = "customer123"
        )
        assertEquals("Payment blocked due to suspected fraud", result.message)
        assertEquals("REJECTED", result.status)
    }

    @Test
    @DisplayName("Успешный платёж")
    fun test6() {
        val result = processor.processPayment(
            amount = 100,
            cardNumber = "4532015112830366",
            expiryMonth = 11,
            expiryYear = 2028,
            currency = "USD",
            customerId = "customer123"
        )

        // Assert
        assertEquals("SUCCESS", result.status)
        assertEquals("Payment completed", result.message)
    }

    @Test
    @DisplayName("При points=500,baseAmount=20000 Cкидка-500")
    fun test7() {
        val paymentProcessor = PaymentProcessor()
        assertEquals(500, paymentProcessor.calculateLoyaltyDiscount(500, 20000))
    }

    @Test
    @DisplayName("При points=2000,baseAmount=2000 Cкидка-200")
    fun test8() {
        val paymentProcessor = PaymentProcessor()
        assertEquals(200, paymentProcessor.calculateLoyaltyDiscount(2000, 2000))
    }

    @Test
    @DisplayName("При points=6000,baseAmount=8000 Cкидка-1200")
    fun test9() {
        val paymentProcessor = PaymentProcessor()
        assertEquals(1200, paymentProcessor.calculateLoyaltyDiscount(6000, 8000))
    }

    @Test
    @DisplayName("При отрицательном baseAmount должно выбрасываться исключение")
    fun test10() {
        val paymentProcessor = PaymentProcessor()
        assertThrows(IllegalArgumentException::class.java) {
            paymentProcessor.calculateLoyaltyDiscount(6000, -8000)
        }
    }

    @Test
    @DisplayName("При points=10000,baseAmount=10000 Cкидка-2000")
    fun test11() {
        val paymentProcessor = PaymentProcessor()
        assertEquals(2000, paymentProcessor.calculateLoyaltyDiscount(10000, 10000))

    }

    @Test
    @DisplayName("Должен выбросить исключение при пустом customerId")
    fun test12() {
        assertThrows(IllegalArgumentException::class.java) {
            processor.processPayment(
                amount = 100,
                cardNumber = "411114111141111",
                expiryMonth = 11,
                expiryYear = 2028,
                currency = "EUR",
                customerId = ""
            )
        }

    }

    @Test
    @DisplayName("Должен выбросить исключение из-за истёкшего срока")
    fun test13() {
        assertThrows(IllegalArgumentException::class.java) {
            processor.processPayment(
                amount = 100,
                cardNumber = "411114111141111",
                expiryMonth = 1,
                expiryYear = 2025,
                currency = "EUR",
                customerId = "customer123"
            )
        }
    }

    @Test
    @DisplayName("Должен выбросить исключение из-за истёкшего срока")
    fun test14() {
        assertThrows(IllegalArgumentException::class.java) {
            processor.processPayment(
                amount = 100,
                cardNumber = "411114111141111",
                expiryMonth = 2,
                expiryYear = 2025,
                currency = "EUR",
                customerId = "customer123"
            )
        }
    }

    @Test
    @DisplayName("При points=10,baseAmount=2000 Cкидка-0")
    fun test15() {
        val paymentProcessor = PaymentProcessor()
        assertEquals(0, paymentProcessor.calculateLoyaltyDiscount(10, 2000))
    }

    @Test
    @DisplayName("Проверяем что всё работает с currency = \"EUR\"")
    fun test16() {
        val result = processor.processPayment(
            amount = 100,
            cardNumber = "4111111111111111",
            expiryMonth = 12,
            expiryYear = 2028,
            currency = "EUR",
            customerId = "customer123"
        )
        assertNotNull(result)
    }

    @Test
    @DisplayName("Проверяем что всё работает с currency = \"GBP\"")
    fun test17() {
        val result = processor.processPayment(
            amount = 100,
            cardNumber = "4111111111111111",
            expiryMonth = 12,
            expiryYear = 2028,
            currency = "GBP",
            customerId = "customer123"
        )
        assertNotNull(result)
    }

    @Test
    @DisplayName("Проверяем что всё работает с currency = \"JPY\"")
    fun test18() {
        val result = processor.processPayment(
            amount = 100,
            cardNumber = "4111111111111111",
            expiryMonth = 12,
            expiryYear = 2028,
            currency = "JPY",
            customerId = "customer123"
        )
        assertNotNull(result)
    }

    @Test
    @DisplayName("Проверяем что всё работает с currency = \"RUB\"")
    fun test19() {
        val result = processor.processPayment(
            amount = 100,
            cardNumber = "4111111111111111",
            expiryMonth = 12,
            expiryYear = 2028,
            currency = "RUB",
            customerId = "customer123"
        )
        assertNotNull(result)
    }

    @Test
    @DisplayName("Проверяем работу системы с несуществующей валютой")
    fun test20() {
        val result = processor.processPayment(
            amount = 100,
            cardNumber = "4111111111111111",
            expiryMonth = 12,
            expiryYear = 2028,
            currency = "CAD",
            customerId = "customer123"
        )
        assertNotNull(result)
    }

    @Test
    @DisplayName("Проверяем работу системы с несуществующей валютой")
    fun test21() {
        val result = processor.processPayment(
            amount = 100,
            cardNumber = "55001111111111111",
            expiryMonth = 11,
            expiryYear = 2025,
            currency = "RUB",
            customerId = "customer123"
        )
        assertNotNull(result)
    }

    @Test
    fun test22() {
        val emptyPayments = emptyList<PaymentData>()
        val paymentProcessor = PaymentProcessor()
        val results = paymentProcessor.bulkProcess(emptyPayments)
        assertTrue(results.isEmpty())
    }

    @Test
    fun test23() {
        val paymentProcessor = PaymentProcessor()
        val payments = listOf(
            PaymentData(100, "4111111111111111", 12, 2025, "USD", "customer1"),
            PaymentData(200, "5555555555554444", 6, 2026, "EUR", "customer2"),
            PaymentData(300, "378282246310005", 3, 2024, "USD", "customer3")
        )

        val results = paymentProcessor.bulkProcess(payments)
        assertEquals(3, results.size)
        results.forEach { result ->
            assertNotNull(result)
            assertTrue(result.status in listOf("SUCCESS", "FAILED", "REJECTED"))
        }
    }

    @Test
    fun test24() {
        val paymentProcessor = PaymentProcessor()
        val payments = listOf(
            PaymentData(100, "4111111111111111", 12, 2025, "USD", "customer1"), // valid
            PaymentData(-50, "4111111111111111", 12, 2025, "USD", "customer2"), // invalid amount
            PaymentData(100, "1234", 12, 2025, "USD", "customer3") // invalid card
        )
        val results = paymentProcessor.bulkProcess(payments)
        assertEquals(3, results.size)
        assertNotNull(results[0])
        assertEquals("REJECTED", results[1].status)
        assertEquals("REJECTED", results[2].status)
        assertTrue(results[1].message.contains("Amount must be positive") || results[1].message.contains("Invalid"))
        assertTrue(results[2].message.contains("card number") || results[2].message.contains("Invalid"))
    }
    @Test
    @DisplayName("Должен выбросить исключение из-за истёкшего срока")
    fun test30() {
        assertThrows(IllegalArgumentException::class.java) {
            processor.processPayment(
                amount = 100,
                cardNumber = "411114111141111",
                expiryMonth = 0,
                expiryYear = 2025,
                currency = "EUR",
                customerId = "customer123"
            )
        }
    }
    @Test
    @DisplayName("Должен выбросить исключение из-за неподходящего номера месяца")
    fun test31() {
        assertThrows(IllegalArgumentException::class.java) {
            processor.processPayment(
                amount = 100,
                cardNumber = "411114111141111",
                expiryMonth = 0,
                expiryYear = 2028,
                currency = "EUR",
                customerId = "customer123"
            )
        }
    }
    @Test
    @DisplayName("Должен выбросить исключение из-за неподходящего номера месяца")
    fun test32() {
        assertThrows(IllegalArgumentException::class.java) {
            processor.processPayment(
                amount = 100,
                cardNumber = "411114111141111",
                expiryMonth = 13,
                expiryYear = 2028,
                currency = "EUR",
                customerId = "customer123"
            )
        }
    }
    @Test
    @DisplayName("Должен выбросить исключение из-за неподходящего номера месяца")
    fun test34() {
        assertThrows(IllegalArgumentException::class.java) {
            processor.processPayment(
                amount = 100,
                cardNumber = "411114111141111",
                expiryMonth = 13,
                expiryYear = 2025,
                currency = "EUR",
                customerId = "customer123"
            )
        }
    }
    @Test
    @DisplayName("Должен выбросить исключение из-за неподходящего номера месяца")
    fun test35() {
        assertThrows(IllegalArgumentException::class.java) {
            processor.processPayment(
                amount = 100,
                cardNumber = "411114111141111",
                expiryMonth = 13,
                expiryYear = 2028,
                currency = "EUR",
                customerId = "customer123"
            )
        }
    }
    @Test
    @DisplayName("Должен выбросить исключение при номере карты с недопустимыми символами")
    fun test36() {
        assertThrows(IllegalArgumentException::class.java) {
            processor.processPayment(
                amount = 100,
                cardNumber = "41111AAAAAAAAAAAAA",
                expiryMonth = 12,
                expiryYear = 2009,
                currency = "USD",
                customerId = "customer123"
            )
        }
    }
    @Test
    @DisplayName("Должен выбросить исключение при номере карты с недопустимыми символами")
    fun test37() {
        assertThrows(IllegalArgumentException::class.java) {
            processor.processPayment(
                amount = 100,
                cardNumber = "AAAAAAAA",
                expiryMonth = 12,
                expiryYear = 2009,
                currency = "USD",
                customerId = "customer123"
            )
        }
    }
}
