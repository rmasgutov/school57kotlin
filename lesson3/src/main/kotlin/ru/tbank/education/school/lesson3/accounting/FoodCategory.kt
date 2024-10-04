package ru.tbank.education.school.lesson3.accounting

class FoodCategory(products: List<Product>) : Category("Еда", products.toMutableList())
