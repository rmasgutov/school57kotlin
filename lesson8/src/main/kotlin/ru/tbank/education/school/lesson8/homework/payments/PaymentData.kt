package ru.tbank.education.school.lesson8.homework.payments

data class PaymentData(
    val amount: Int,
    val cardNumber: String,
    val expiryMonth: Int,
    val expiryYear: Int,
    val currency: String,
    val customerId: String
)