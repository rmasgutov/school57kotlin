package ru.tbank.education.school.lesson3.seminar.university.sealedclasses

sealed class OperationResult<out T> {
    data class Success<T>(val data: T) : OperationResult<T>()
    data class Error(val message: String) : OperationResult<Nothing>()
    object Loading : OperationResult<Nothing>()
}