package ru.tbank.education.school.ru.tbank.education.school.lesson4.Hospital

sealed class UserRole {
    object PATIENT : UserRole()
    object DOCTOR : UserRole()
}