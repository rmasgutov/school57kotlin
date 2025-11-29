package ru.tbank.education.school.lesson8.lection.nullsafety

data class City(val name: String?)
data class Address(val city: City?)
data class Customer(val name: String, val address: Address?)
data class Order(val id: String, val customer: Customer?)
