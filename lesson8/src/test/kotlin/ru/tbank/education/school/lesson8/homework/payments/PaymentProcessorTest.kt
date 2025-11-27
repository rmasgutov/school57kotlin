package ru.tbank.education.school.lesson8.homework.payments

import org.junit.jupiter.api.BeforeEach

class PaymentProcessorTest {
    private lateinit var processor: PaymentProcessor

    @BeforeEach
    fun setUp() {
        processor = PaymentProcessor()
    }
}