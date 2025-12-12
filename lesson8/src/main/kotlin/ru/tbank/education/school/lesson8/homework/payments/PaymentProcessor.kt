package ru.tbank.education.school.lesson8.homework.payments

class PaymentProcessor {

    /**
     * Обрабатывает платёж и возвращает результат.
     *
     * @param amount сумма платежа
     * @param cardNumber номер карты
     * @param expiryMonth месяц истечения срока (1-12)
     * @param expiryYear год истечения срока
     * @param currency валюта ("USD", "EUR", "RUB", "GBP", и др.)
     * @param customerId идентификатор клиента
     *
     * @return результат платежа
     *
     * @throws IllegalArgumentException если данные некорректны
     */
    fun processPayment(
        amount: Int,
        cardNumber: String,
        expiryMonth: Int,
        expiryYear: Int,
        currency: String,
        customerId: String
    ): PaymentResult {
        if (amount <= 0) {
            throw IllegalArgumentException("Amount must be positive")
        }

        if (cardNumber.isBlank() || cardNumber.length !in 13..19 || !cardNumber.all { it.isDigit() }) {
            throw IllegalArgumentException("Invalid card number format")
        }

        if (!isValidExpiry(expiryMonth, expiryYear)) {
            throw IllegalArgumentException("Invalid expiry date")
        }

        if (currency.isBlank()) {
            throw IllegalArgumentException("Currency cannot be empty")
        }

        if (customerId.isBlank()) {
            throw IllegalArgumentException("Customer ID cannot be blank")
        }

        // Проверка на подозрительные карты (например, тестовые номера)
        if (isSuspiciousCard(cardNumber)) {
            log("FRAUD_BLOCKED", "Suspicious card detected: $cardNumber")
            return PaymentResult("REJECTED", "Payment blocked due to suspected fraud")
        }

        val normalizedCurrency = currency.uppercase()
        val convertedAmount = when (normalizedCurrency) {
            "USD" -> amount
            "EUR" -> (amount * 0.92).toInt()
            "GBP" -> (amount * 0.78).toInt()
            "JPY" -> (amount * 150)
            "RUB" -> (amount * 90)
            else -> {
                log("WARNING", "Unsupported currency: $normalizedCurrency, defaulting to USD")
                amount
            }
        }

        // Имитация шлюза — логика внутри
        val gatewayResult = tryChargeGateway(cardNumber, convertedAmount)

        return when {
            !gatewayResult.success -> {
                when {
                    gatewayResult.message?.contains("insufficient funds", ignoreCase = true) == true -> {
                        log("FAILED", "Payment failed: insufficient funds")
                        PaymentResult("FAILED", "Insufficient funds")
                    }

                    gatewayResult.message?.contains("card blocked", ignoreCase = true) == true -> {
                        log("FAILED", "Payment failed: card is blocked")
                        PaymentResult("FAILED", "Card is blocked")
                    }

                    else -> {
                        log("FAILED", "Payment declined: ${gatewayResult.message}")
                        PaymentResult("FAILED", gatewayResult.message ?: "Unknown error")
                    }
                }
            }

            else -> {
                log("SUCCESS", "Payment successful: $amount $currency")
                PaymentResult("SUCCESS", "Payment completed")
            }
        }
    }

    /**
     * Проверяет, действителен ли срок действия карты.
     */
    private fun isValidExpiry(month: Int, year: Int): Boolean {
        val currentYear = 2025
        val currentMonth = 11

        return when {
            year < currentYear -> false
            year == currentYear -> month in 1..12 && month >= currentMonth
            year > currentYear -> month in 1..12
            else -> false
        }
    }

    /**
     * Проверка на подозрительные номера карт (например, тестовые)
     */
    private fun isSuspiciousCard(cardNumber: String): Boolean {
        val suspiciousPrefixes = listOf("4444", "5555", "1111", "9999")
        return suspiciousPrefixes.any { cardNumber.startsWith(it) } ||
                isLuhnInvalid(cardNumber)
    }

    /**
     * Проверка номера карты по алгоритму Луна.
     */
    private fun isLuhnInvalid(cardNumber: String): Boolean {
        if (cardNumber.length < 13) return true

        var sum = 0
        var isEven = false

        for (i in cardNumber.length - 1 downTo 0) {
            val digit = cardNumber[i] - '0'
            if (digit !in 0..9) return true

            var adjustedDigit = if (isEven) digit * 2 else digit
            if (adjustedDigit > 9) adjustedDigit -= 9

            sum += adjustedDigit
            isEven = !isEven
        }

        return sum % 10 != 0
    }

    /**
     * Имитация внешнего шлюза — внутренняя логика
     */
    private fun tryChargeGateway(cardNumber: String, amount: Int): GatewayResult {
        // Имитация случайных сбоев
        if (amount > 100_000) {
            return GatewayResult(false, "Transaction limit exceeded")
        }

        if (cardNumber.startsWith("4444")) {
            return GatewayResult(false, "Card blocked")
        }

        if (cardNumber.startsWith("5500")) {
            return GatewayResult(false, "Insufficient funds")
        }

        // 5% шанс случайной ошибки
        if (amount % 17 == 0) {
            return GatewayResult(false, "Gateway timeout")
        }

        return GatewayResult(true, null)
    }

    /**
     * Условная конвертация баллов лояльности в скидку
     */
    fun calculateLoyaltyDiscount(points: Int, baseAmount: Int): Int {
        if (baseAmount <= 0) {
            throw IllegalArgumentException("Base amount must be positive")
        }

        return when {
            points >= 10_000 -> minOf((baseAmount * 0.2).toInt(), 5000) // макс. 5000
            points >= 5_000 -> minOf((baseAmount * 0.15).toInt(), 3000)
            points >= 2_000 -> minOf((baseAmount * 0.1).toInt(), 1500)
            points >= 500 -> minOf((baseAmount * 0.05).toInt(), 500)
            else -> 0
        }
    }

    /**
     * Пакетная обработка платежей
     */
    fun bulkProcess(payments: List<PaymentData>): List<PaymentResult> {
        if (payments.isEmpty()) {
            log("INFO", "No payments to process")
            return emptyList()
        }

        val results = mutableListOf<PaymentResult>()
        var successCount = 0
        var failCount = 0

        for ((index, payment) in payments.withIndex()) {
            try {
                val result = processPayment(
                    payment.amount,
                    payment.cardNumber,
                    payment.expiryMonth,
                    payment.expiryYear,
                    payment.currency,
                    payment.customerId
                )
                results.add(result)
                if (result.status == "SUCCESS") successCount++ else failCount++
            } catch (e: IllegalArgumentException) {
                log("REJECTED", "Invalid data at index $index: ${e.message}")
                results.add(PaymentResult("REJECTED", e.message ?: "Invalid input"))
            } catch (e: Exception) {
                log("CRITICAL", "Unexpected error at index $index: ${e.message}")
                results.add(PaymentResult("FAILED", "Internal error"))
            }
        }

        log("SUMMARY", "Processed ${payments.size} payments: $successCount success, $failCount fail")
        return results
    }

    /**
     * Простое логирование
     */
    private fun log(level: String, message: String) {
        println("[$level] $message")
    }
}