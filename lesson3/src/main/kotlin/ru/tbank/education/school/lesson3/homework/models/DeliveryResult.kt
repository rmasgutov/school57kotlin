package ru.tbank.education.school.lesson3.homework.models

sealed class DeliveryResult {
    data class Success(val emailId: String) : DeliveryResult()
    data class TransientFailure(val reason: String) : DeliveryResult()
    data class PermanentFailure(val reason: String) : DeliveryResult()
}