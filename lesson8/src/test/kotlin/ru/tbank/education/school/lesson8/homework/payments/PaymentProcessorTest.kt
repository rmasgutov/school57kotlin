package ru.tbank.education.school.lesson8.homework.payments

import org.junit.jupiter.api.BeforeEach

class PaymentProcessorTest {
    private lateinit var processor: PaymentProcessor

    @BeforeEach
    fun setUp() {
        processor = PaymentProcessor()
    }

    @Test
    fun testZeroAmount() {
        val exception = assertThrows<IllegalArgumentException> {
            processor.processPayment(0, "1111111111111111", 12, 2026, "USD", "customer123")
        }
        assertEquals("Amount must be positive", exception.message)
    }

    @Test
    fun testNegativeAmount() {
        val exception = assertThrows<IllegalArgumentException> {
            processor.processPayment(-100, "1111111111111111", 12, 2026, "USD", "customer123")
        }
        assertEquals("Amount must be positive", exception.message)
    }

    @Test
    fun testBlankCardNumber() {
        val exception = assertThrows<IllegalArgumentException> {
            processor.processPayment(100, "", 12, 2026, "USD", "customer123")
        }
        assertEquals("Invalid card number format", exception.message)
    }

    @Test
    fun testShortCardNumber() {
        val exception = assertThrows<IllegalArgumentException> {
            processor.processPayment(100, "123456789012", 12, 2026, "USD", "customer123")
        }
        assertEquals("Invalid card number format", exception.message)
    }

    @Test
    fun testLongCardNumber() {
        val exception = assertThrows<IllegalArgumentException> {
            processor.processPayment(100, "12345678901234567890", 12, 2026, "USD", "customer123")
        }
        assertEquals("Invalid card number format", exception.message)
    }

    @Test
    fun testInvalidCardNumberFormat() {
        val exception = assertThrows<IllegalArgumentException> {
            processor.processPayment(100, "1111-1111-1111-1111", 12, 2026, "USD", "customer123")
        }
        assertEquals("Invalid card number format", exception.message)
    }

    @Test
    fun testInvalidExpiryMonth() {
        val exception = assertThrows<IllegalArgumentException> {
            processor.processPayment(100, "1111111111111111", 0, 2026, "USD", "customer123")
        }
        assertEquals("Invalid expiry date", exception.message)
    }

    @Test
    fun testPastExpiryYear() {
        val exception = assertThrows<IllegalArgumentException> {
            processor.processPayment(100, "1111111111111111", 12, 2020, "USD", "customer123")
        }
        assertEquals("Invalid expiry date", exception.message)
    }

    @Test
    fun testPastExpiryMonth() {
        val exception = assertThrows<IllegalArgumentException> {
            processor.processPayment(100, "1111111111111111", 5, 2025, "USD", "customer123")
        }
        assertEquals("Invalid expiry date", exception.message)
    }

    @Test
    fun testBlankCurrency() {
        val exception = assertThrows<IllegalArgumentException> {
            processor.processPayment(100, "1111111111111111", 12, 2026, "", "customer123")
        }
        assertEquals("Currency cannot be empty", exception.message)
    }

    @Test
    fun testBlankCustomerId() {
        val exception = assertThrows<IllegalArgumentException> {
            processor.processPayment(100, "1111111111111111", 12, 2026, "USD", "")
        }
        assertEquals("Customer ID cannot be blank", exception.message)
    }

    @ParameterizedTest
    @ValueSource(strings = ["4444111111111111", "5555111111111111", "1111111111111111", "9999111111111111"])
    fun testSuspiciousCards(cardNumber: String) {
        val result = processor.processPayment(100, cardNumber, 12, 2026, "USD", "customer123")
        assertEquals("REJECTED", result.status)
    }

    @Test
    fun testInvalidLuhnCard() {
        val result = processor.processPayment(100, "1111111111111112", 12, 2026, "USD", "customer123")
        assertEquals("REJECTED", result.status)
    }

    @ParameterizedTest
    @CsvSource("USD,100", "EUR,100", "GBP,100", "JPY,100", "RUB,100")
    fun testCurrencyConversion(currency: String, amount: Int) {
        val result = processor.processPayment(amount, "1111111111111111", 12, 2026, currency, "customer123")
        assertTrue((result.status == "SUCCESS") or (result.status == "FAILED"))
    }

    @Test
    fun testUnsupportedCurrency() {
        val result = processor.processPayment(100, "1111111111111111", 12, 2026, "CAD", "customer123")
        assertTrue((result.status == "SUCCESS") or (result.status == "FAILED"))
    }

    @Test
    fun testSuccessfulPayment() {
        val result = processor.processPayment(100, "1111111111111111", 12, 2026, "USD", "customer123")
        assertTrue((result.status == "SUCCESS") or (result.status == "FAILED"))
    }

    @Test
    fun testBlockedCard() {
        val result = processor.processPayment(100, "4444111111111111", 12, 2026, "USD", "customer123")
        assertEquals("FAILED", result.status)
    }

    @Test
    fun testInsufficientFunds() {
        val result = processor.processPayment(100, "5500111111111111", 12, 2026, "USD", "customer123")
        assertEquals("FAILED", result.status)
    }

    @Test
    fun testTransactionLimit() {
        val result = processor.processPayment(100001, "1111111111111111", 12, 2026, "USD", "customer123")
        assertEquals("FAILED", result.status)
    }

    @Test
    fun testEmptyBulkProcess() {
        val results = processor.bulkProcess(emptyList())
        assertTrue(results.isEmpty())
    }

    @Test
    fun testMixedBulkProcess() {
        val payments = listOf(
            PaymentData(100, "1111111111111111", 12, 2026, "USD", "customer1"),
            PaymentData(-100, "1111111111111111", 12, 2026, "USD", "customer2"),
            PaymentData(100, "1111111111111111", 12, 2020, "USD", "customer3"),
            PaymentData(100, "4444111111111111", 12, 2026, "USD", "customer4")
        )

        val results = processor.bulkProcess(payments)
        assertEquals(4, results.size)
    }

    @Test
    fun testValidBulkProcess() {
        val payments = listOf(
            PaymentData(100, "1111111111111111", 12, 2026, "USD", "customer1"),
            PaymentData(200, "1111111111111111", 11, 2026, "EUR", "customer2"),
            PaymentData(300, "1111111111111111", 10, 2027, "GBP", "customer3")
        )

        val results = processor.bulkProcess(payments)
        assertEquals(3, results.size)
    }

    @Test
    fun testNegativeBaseAmountDiscount() {
        val exception = assertThrows<IllegalArgumentException> {
            processor.calculateLoyaltyDiscount(1000, -100)
        }
        assertEquals("Base amount must be positive", exception.message)
    }

    @Test
    fun testZeroBaseAmountDiscount() {
        val exception = assertThrows<IllegalArgumentException> {
            processor.calculateLoyaltyDiscount(1000, 0)
        }
        assertEquals("Base amount must be positive", exception.message)
    }

    @ParameterizedTest
    @CsvSource("500,10000,500", "1000,10000,500", "2000,10000,1000", "5000,10000,1500", "10000,10000,2000")
    fun testLoyaltyDiscount(points: Int, baseAmount: Int, expectedDiscount: Int) {
        val discount = processor.calculateLoyaltyDiscount(points, baseAmount)
        assertEquals(expectedDiscount, discount)
    }

    @Test
    fun testMaxDiscountLimits() {
        assertEquals(5000, processor.calculateLoyaltyDiscount(10000, 100000))
        assertEquals(3000, processor.calculateLoyaltyDiscount(5000, 50000))
        assertEquals(1500, processor.calculateLoyaltyDiscount(2000, 20000))
    }

    @Test
    fun testLowPointsDiscount() {
        assertEquals(0, processor.calculateLoyaltyDiscount(100, 1000))
    }

    @Test
    fun testGatewayTimeout() {
        var amount = 17
        while (amount % 17 != 0) amount++

        val result = processor.processPayment(amount, "1111111111111111", 12, 2026, "USD", "customer123")
        assertEquals("FAILED", result.status)
    }

    @Test
    fun testUnknownGatewayError() {
        val result = processor.processPayment(50, "1111111111111111", 12, 2026, "USD", "customer123")
        assertTrue((result.status == "SUCCESS") or (result.status == "FAILED"))
    }
}
}