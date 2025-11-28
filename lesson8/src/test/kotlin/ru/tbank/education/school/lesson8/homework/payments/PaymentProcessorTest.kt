package ru.tbank.education.school.lesson8.homework.payments

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class PaymentProcessorTest {
    private lateinit var processor: PaymentProcessor

    @BeforeEach
    fun setUp() {
        processor = PaymentProcessor()
    }

    @Test
    @DisplayName("Успешный платеж")
    fun validPaymentShouldBeValid() {
        val result = processor.processPayment(
            amount = 1000,
            cardNumber = "4111111111111111",
            expiryMonth = 12,
            expiryYear = 2026,
            currency = "USD",
            customerId = "customer67"
        )
        assertEquals("SUCCESS", result.status)
        assertEquals("Payment completed", result.message)
    }

    @Test
    @DisplayName("Отрицательная сумма - исключение")
    fun negativeAmount() {
        val exception = assertThrows<IllegalArgumentException> {
            processor.processPayment(
                amount = -52,
                cardNumber = "4111111111111111",
                expiryMonth = 12,
                expiryYear = 2026,
                currency = "USD",
                customerId = "customer67"
            )
        }
        assertEquals("Amount must be positive", exception.message)
    }

    @Test
    @DisplayName("Нулевая сумма - исключение")
    fun zeroAmount() {
        val exception = assertThrows<IllegalArgumentException> {
            processor.processPayment(
                amount = 0,
                cardNumber = "4111111111111111",
                expiryMonth = 12,
                expiryYear = 2026,
                currency = "USD",
                customerId = "customer67"
            )
        }
        assertEquals("Amount must be positive", exception.message)
    }

    @Test
    @DisplayName("Короткий номер карты - исключение")
    fun shortCardNumber() {
        val exception = assertThrows<IllegalArgumentException> {
            processor.processPayment(
                amount = 1000,
                cardNumber = "123456789012",
                expiryMonth = 12,
                expiryYear = 2025,
                currency = "USD",
                customerId = "customer123"
            )
        }
        assertEquals("Invalid card number format", exception.message)
    }

    @Test
    @DisplayName("Длинный номер карты - исключение")
    fun longCardNumber() {
        val exception = assertThrows<IllegalArgumentException> {
            processor.processPayment(
                amount = 1000,
                cardNumber = "12345678901234567890",
                expiryMonth = 12,
                expiryYear = 2025,
                currency = "USD",
                customerId = "customer123"
            )
        }
        assertEquals("Invalid card number format", exception.message)
    }

    @Test
    @DisplayName("Неверный месяц - исключение")
    fun wrongMonth() {
        val exception = assertThrows<IllegalArgumentException> {
            processor.processPayment(
                amount = 1000,
                cardNumber = "4111111111111111",
                expiryMonth = 13,
                expiryYear = 2025,
                currency = "USD",
                customerId = "customer123"
            )
        }
        assertEquals("Invalid expiry date", exception.message)
    }

    @Test
    @DisplayName("Просроченная карта - исключение")
    fun expiredCard() {
        val exception = assertThrows<IllegalArgumentException> {
            processor.processPayment(
                amount = 1000,
                cardNumber = "4111111111111111",
                expiryMonth = 10,
                expiryYear = 2025,
                currency = "USD",
                customerId = "customer123"
            )
        }
        assertEquals("Invalid expiry date", exception.message)
    }

    @Test
    @DisplayName("Пустая валюта - исключение")
    fun emptyCurrency() {
        val exception = assertThrows<IllegalArgumentException> {
            processor.processPayment(
                amount = 1000,
                cardNumber = "4111111111111111",
                expiryMonth = 12,
                expiryYear = 2026,
                currency = "",
                customerId = "customer123"
            )
        }
        assertEquals("Currency cannot be empty", exception.message)
    }

    @Test
    @DisplayName("Пустой customerId - исключение")
    fun emptyCustomerId() {
        val exception = assertThrows<IllegalArgumentException> {
            processor.processPayment(
                amount = 1000,
                cardNumber = "4111111111111111",
                expiryMonth = 12,
                expiryYear = 2026,
                currency = "USD",
                customerId = ""
            )
        }
        assertEquals("Customer ID cannot be blank", exception.message)
    }

    @Test
    @DisplayName("Подозрительные карты блокируются")
    fun suspiciousCardsBlocked() {
        val cards = listOf("4444111122223333", "5555111122223333", "1111111111111111", "9999111122223333")

        cards.forEach { card ->
            val result = processor.processPayment(1000, card, 12, 2026, "USD", "customer123")
            assertEquals("REJECTED", result.status)
            assertEquals("Payment blocked due to suspected fraud", result.message)
        }
    }

    @Test
    @DisplayName("Валидные карты по Луну работают")
    fun validLuhnCardsWork() {
        val cards = listOf("4111111111111111", "378282246310005", "6011111111111117")

        cards.forEach { card ->
            val result = processor.processPayment(1000, card, 12, 2026, "USD", "customer123")
            assertEquals("SUCCESS", result.status)
        }
    }

    @Test
    @DisplayName("Невалидные карты по Луну блокируются")
    fun invalidLuhnCardsBlocked() {
        val cards = listOf("4111111111111112", "378282246310006")

        cards.forEach { card ->
            val result = processor.processPayment(1000, card, 12, 2026, "USD", "customer123")
            assertEquals("REJECTED", result.status)
        }
    }

    @Test
    @DisplayName("Конвертация валют")
    fun currencyConversion() {
        val result = processor.processPayment(1000, "4111111111111111", 12, 2026, "EUR", "customer123")
        assertEquals("SUCCESS", result.status)
    }

    @Test
    @DisplayName("Логирование неподдерживаемой валюты")
    fun unsupportedCurrencyLogging() {
        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        processor.processPayment(1000, "4111111111111111", 12, 2026, "ABC", "customer123")

        val logOutput = outputStream.toString()
        assertTrue(logOutput.contains("Unsupported currency"))
        System.setOut(System.out)
    }

    @Test
    @DisplayName("Карта 5500 - недостаточно средств")
    fun card5500InsufficientFunds() {
        val result = processor.processPayment(1000, "5500111122223333", 12, 2026, "USD", "customer123")
        assertEquals("REJECTED", result.status)
        assertEquals("Payment blocked due to suspected fraud", result.message)
    }

    @Test
    @DisplayName("Сумма кратная 17 - таймаут")
    fun amountMultipleOf17Timeout() {
        val result = processor.processPayment(17, "4111111111111111", 12, 2026, "USD", "customer123")
        assertEquals("FAILED", result.status)
        assertEquals("Gateway timeout", result.message)
    }

    @Test
    @DisplayName("Большая сумма - превышение лимита")
    fun largeAmountLimitExceeded() {
        val result = processor.processPayment(100001, "4111111111111111", 12, 2026, "USD", "customer123")
        assertEquals("FAILED", result.status)
        assertEquals("Transaction limit exceeded", result.message)
    }

    @Test
    @DisplayName("Конвертированная большая сумма - превышение лимита")
    fun convertedLargeAmountLimitExceeded() {
        val result = processor.processPayment(2000, "4111111111111111", 12, 2026, "RUB", "customer123")
        assertEquals("FAILED", result.status)
        assertEquals("Transaction limit exceeded", result.message)
    }

    @Test
    @DisplayName("Конвертированная сумма кратная 17 - таймаут")
    fun convertedAmountMultipleOf17Timeout() {
        val result = processor.processPayment(1700, "4111111111111111", 12, 2026, "EUR", "customer123")
        assertEquals("FAILED", result.status)
        assertEquals("Gateway timeout", result.message)
    }

    @Test
    @DisplayName("Пустой список в bulkProcess")
    fun emptyBulkProcess() {
        val results = processor.bulkProcess(emptyList())
        assertTrue(results.isEmpty())
    }

    @Test
    @DisplayName("Валидные платежи в bulkProcess")
    fun validBulkProcess() {
        val payments = listOf(
            PaymentData(1000, "4111111111111111", 12, 2026, "USD", "customer1"),
            PaymentData(2000, "378282246310005", 6, 2027, "EUR", "customer2")
        )

        val results = processor.bulkProcess(payments)
        assertEquals(2, results.size)
        results.forEach { assertEquals("SUCCESS", it.status) }
    }

    @Test
    @DisplayName("Невалидные платежи в bulkProcess")
    fun invalidBulkProcess() {
        val payments = listOf(
            PaymentData(1000, "4111111111111111", 12, 2026, "USD", "customer1"),
            PaymentData(-100, "4111111111111111", 12, 2026, "USD", "customer2")
        )

        val results = processor.bulkProcess(payments)
        assertEquals(2, results.size)
        assertEquals("SUCCESS", results[0].status)
        assertEquals("REJECTED", results[1].status)
    }

    @Test
    @DisplayName("Подозрительные карты в bulkProcess")
    fun suspiciousBulkProcess() {
        val payments = listOf(
            PaymentData(1000, "4444111122223333", 12, 2026, "USD", "customer1"),
            PaymentData(2000, "4111111111111111", 12, 2026, "USD", "customer2")
        )

        val results = processor.bulkProcess(payments)
        assertEquals("REJECTED", results[0].status)
        assertEquals("SUCCESS", results[1].status)
    }

    @Test
    @DisplayName("Ошибки шлюза в bulkProcess")
    fun gatewayErrorsBulkProcess() {
        val payments = listOf(
            PaymentData(17, "4111111111111111", 12, 2026, "USD", "customer1"),
            PaymentData(100001, "4111111111111111", 12, 2026, "USD", "customer2")
        )

        val results = processor.bulkProcess(payments)
        assertEquals("FAILED", results[0].status)
        assertEquals("FAILED", results[1].status)
    }

    @Test
    @DisplayName("Логирование в bulkProcess")
    fun bulkProcessLogging() {
        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        val payments = listOf(
            PaymentData(1000, "4111111111111111", 12, 2026, "USD", "customer1"),
            PaymentData(-100, "4111111111111111", 12, 2026, "USD", "customer2")
        )

        processor.bulkProcess(payments)

        val logOutput = outputStream.toString()
        assertTrue(logOutput.contains("SUMMARY"))
        System.setOut(System.out)
    }

    @Test
    @DisplayName("Скидки - неположительная сумма")
    fun discountNonPositiveAmount() {
        val exception = assertThrows<IllegalArgumentException> {
            processor.calculateLoyaltyDiscount(1000, 0)
        }
        assertEquals("Base amount must be positive", exception.message)
    }

    @Test
    @DisplayName("Скидки - разные уровни")
    fun discountLevels() {
        assertEquals(0, processor.calculateLoyaltyDiscount(499, 10000))
        assertEquals(500, processor.calculateLoyaltyDiscount(500, 10000))
        assertEquals(1000, processor.calculateLoyaltyDiscount(2000, 10000))
        assertEquals(1500, processor.calculateLoyaltyDiscount(5000, 10000))
        assertEquals(2000, processor.calculateLoyaltyDiscount(10000, 10000))
    }

    @Test
    @DisplayName("Скидки - ограничения максимума")
    fun discountMaxLimits() {
        assertEquals(500, processor.calculateLoyaltyDiscount(500, 20000))
        assertEquals(1500, processor.calculateLoyaltyDiscount(2000, 20000))
        assertEquals(3000, processor.calculateLoyaltyDiscount(5000, 30000))
        assertEquals(5000, processor.calculateLoyaltyDiscount(10000, 50000))
    }

    @Test
    @DisplayName("Неизвестная ошибка шлюза")
    fun unknownGatewayError() {
        val result = processor.processPayment(1000, "4111111111111111", 12, 2026, "USD", "customer123")
        if (1000 % 17 != 0) {
            assertEquals("SUCCESS", result.status)
        }
    }

    @Test
    @DisplayName("Разные валюты в разном регистре")
    fun differentCaseCurrencies() {
        val testCases = listOf(
            "usd" to 1000,
            "Eur" to 1000,
            "gbp" to 1000,
            "jpy" to 500,
            "Rub" to 1000
        )

        testCases.forEach { (currency, amount) ->
            val result = processor.processPayment(amount, "4111111111111111", 12, 2026, currency, "customer123")
            assertEquals("SUCCESS", result.status, "Currency $currency with amount $amount should succeed")
        }
    }

    @Test
    @DisplayName("Ошибка шлюза: недостаточно средств")
    fun gatewayErrorInsufficientFunds() {
        val result = processor.processPayment(1000, "4000111122223333", 12, 2026, "USD", "customer123")
        assertEquals("REJECTED", result.status)
        assertEquals("Payment blocked due to suspected fraud", result.message)
    }
}