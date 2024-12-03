package ru.tbank.education.school.lesson3.accounting

// Пример использования
fun main() {
    val apple = Product("Apple", 1.0, 50)
    val banana = Product("Banana", 0.5, 30)
    val phone = Product("Phone", 300.0, 10)

    val foodCategory = FoodCategory("Fruits", mutableListOf(apple, banana))
    val electronicsCategory = ElectronicsCategory("Gadgets", mutableListOf(phone))

    Store.init(listOf(foodCategory, electronicsCategory))

    Store.topUp(listOf(FoodCategory("Fruits", mutableListOf(Product("Apple", 1.0, 20)))))

    Store.sell(Product("Apple", 1.0, 5))
    Store.sell(Product("Phone", 300.0, 1))

    println(Store.search("Fru"))

    println("Общая стоимость в категории Fruits: ${Store.calculateTotalValue("Fruits")}")

    Store.showSales()
}