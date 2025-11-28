package ru.tbank.education.school.lesson8.homework.payments

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PaymentProcessorTest {
    private lateinit var processor: PaymentProcessor

    @BeforeEach
    fun setUp() {
        processor = PaymentProcessor()
    }

    @Test
    fun `amount must be positive`() {
        assertThrows(IllegalArgumentException::class.java) {
            processor.processPayment(
                -10, "5105105105105100", 12, 2026, "USD", "C1"
            )
        }
    }

    @Test
    fun `invalid card number format - too short`() {
        assertThrows(IllegalArgumentException::class.java) {
            processor.processPayment(
                100, "123", 12, 2027, "USD", "C1"
            )
        }
    }

    @Test
    fun `invalid card number format - letters`() {
        assertThrows(IllegalArgumentException::class.java) {
            processor.processPayment(
                100, "1234abc56789", 12, 2030, "USD", "C2"
            )
        }
    }

    @Test
    fun `expiry date invalid - past`() {
        assertThrows(IllegalArgumentException::class.java) {
            processor.processPayment(
                100, "5105105105105100", 1, 2020, "USD", "C1"
            )
        }
    }

    @Test
    fun `currency cannot be empty`() {
        assertThrows(IllegalArgumentException::class.java) {
            processor.processPayment(
                100, "5105105105105100", 12, 2026, "", "C1"
            )
        }
    }

    @Test
    fun `customer id cannot be blank`() {
        assertThrows(IllegalArgumentException::class.java) {
            processor.processPayment(
                100, "5105105105105100", 12, 2026, "USD", ""
            )
        }
    }

    @Test
    fun `suspicious card prefix leads to fraud rejection`() {
        val result = processor.processPayment(
            100, "4444111122223333", 12, 2026, "USD", "C1"
        )
        assertEquals("REJECTED", result.status)
    }

    @Test
    fun `Luhn invalid card triggers suspicious rejection`() {
        val invalidCard = "1234567890123"
        val res = processor.processPayment(
            100, invalidCard, 12, 2026, "USD", "C1"
        )
        assertEquals("REJECTED", res.status)
    }

    @Test
    fun `unsupported currency logs and defaults to USD`() {
        val result = processor.processPayment(
            100, "5105105105105100", 12, 2026, "ABC", "C1"
        )
        assertEquals("SUCCESS", result.status)
    }

    @Test
    fun `JPY conversion`() {
        val res = processor.processPayment(
            100, "5105105105105100", 12, 2026, "JPY", "C1"
        )
        assertEquals("SUCCESS", res.status)
    }

    @Test
    fun `RUB conversion`() {
        val res = processor.processPayment(
            100, "5105105105105100", 12, 2026, "RUB", "C1"
        )
        assertEquals("SUCCESS", res.status)
    }

    @Test
    fun `gateway transaction limit exceeded`() {
        val result = processor.processPayment(
            200_000, "5105105105105100", 12, 2026, "USD", "C10"
        )
        assertEquals("FAILED", result.status)
    }

    @Test
    fun `gateway card blocked`() {
        val result = processor.processPayment(
            100, "4444333322221111", 12, 2026, "USD", "C11"
        )
        assertEquals("FAILED", result.status)
    }

    @Test
    fun `gateway insufficient funds`() {
        val result = processor.processPayment(
            100, "5500550055005500", 12, 2026, "USD", "C12"
        )
        assertEquals("FAILED", result.status)
    }

    @Test
    fun `gateway timeout when amount divisible by 17`() {
        val amount = 34
        val result = processor.processPayment(
            amount, "5105105105105100", 12, 2026, "USD", "C13"
        )
        assertEquals("FAILED", result.status)
    }

    @Test
    fun `successful payment`() {
        val result = processor.processPayment(
            123, "5105105105105100", 12, 2026, "USD", "C55"
        )
        assertEquals("SUCCESS", result.status)
    }

    @Test
    fun `loyalty discount invalid base amount`() {
        assertThrows(IllegalArgumentException::class.java) {
            processor.calculateLoyaltyDiscount(1000, 0)
        }
    }

    @Test
    fun `loyalty discount all levels`() {
        assertEquals(0, processor.calculateLoyaltyDiscount(100, 1000))
        assertEquals(50, processor.calculateLoyaltyDiscount(500, 1000))
        assertEquals(100, processor.calculateLoyaltyDiscount(2000, 1000))
        assertEquals(150, processor.calculateLoyaltyDiscount(5000, 1000))
        assertEquals(200, processor.calculateLoyaltyDiscount(10000, 1000))

        assertEquals(5000, processor.calculateLoyaltyDiscount(20000, 50000))
    }

    @Test
    fun `bulkProcess empty list`() {
        val result = processor.bulkProcess(emptyList())
        assertTrue(result.isEmpty())
    }

    @Test
    fun `bulkProcess mixed valid and invalid`() {
        val payments = listOf(
            PaymentData(100, "5105105105105100", 12, 2026, "USD", "C1"),
            PaymentData(-10, "5105105105105100", 12, 2026, "USD", "C2"),
            PaymentData(100, "1234567890123", 12, 2026, "USD", "C3") // Luhn invalid → REJECTED
        )

        val results = processor.bulkProcess(payments)

        assertEquals(3, results.size)
        assertEquals("SUCCESS", results[0].status)
        assertEquals("REJECTED", results[1].status)
        assertEquals("REJECTED", results[2].status)
    }

    @Test
    fun `bulkProcess handles unexpected exceptions`() {

        val payments = listOf(
            PaymentData(100, "4242424242424", 0, 2026, "USD", "C1")
        )

        val results = processor.bulkProcess(payments)

        assertEquals("REJECTED", results[0].status)
    }
}
