package ru.tbank.education.school.lesson3.accounting.impl

import ru.tbank.education.school.lesson3.accounting.Category
import ru.tbank.education.school.lesson3.accounting.Product

/**
 * @author <a href="https://github.com/Neruxov">Neruxov</a>
 */
class ElectronicsCategory(products: List<Product>) : Category(
    "Электроника", products.toMutableList()
)