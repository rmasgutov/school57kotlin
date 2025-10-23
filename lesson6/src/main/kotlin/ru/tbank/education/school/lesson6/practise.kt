package ru.tbank.education.school.lesson6

fun main() {
    task1Lists()
    task2Sets()
    task3Maps()
    task4FilterAndGroup()
    task5Books()
    task6Store()
    task7Students()
}

// -----------------------
// 1. Работа со списками
// -----------------------
fun task1Lists() {
    println("=== Task 1: Lists ===")
    val shoppingList = listOf("Молоко", "Хлеб", "Яблоки", "Сыр")

    val hasBread = "Хлеб" in shoppingList
    println("Есть ли Хлеб: $hasBread")

    val sorted = shoppingList.sorted()
    println("Отсортированный список: $sorted")

    val startsWithC = shoppingList.filter { it.startsWith("С") }
    println("Товары на С: $startsWithC")
}

// -----------------------
// 2. Работа с множествами
// -----------------------
fun task2Sets() {
    println("=== Task 2: Sets ===")
    val kotlinStudents = mutableSetOf("Анна", "Иван", "Мария")
    val javaStudents = setOf("Иван", "Петр", "Ольга")

    kotlinStudents.add("Анна")
    println("После добавления дубликата: $kotlinStudents")

    val hasMaria = "Мария" in kotlinStudents
    println("Есть ли Мария: $hasMaria")

    val both = kotlinStudents.intersect(javaStudents)
    println("Общие студенты: $both")
}

// -----------------------
// 3. Работа с Map
// -----------------------
fun task3Maps() {
    println("=== Task 3: Maps ===")
    val products = mutableMapOf(
        "Телефон" to 50000,
        "Ноутбук" to 80000,
        "Наушники" to 3000
    )

    products["Телефон"] = 55000
    products["Планшет"] = 40000

    println("Обновленный список товаров: $products")

    val expensive = products.filter { it.value > 10000 }
    println("Товары дороже 10000: $expensive")

    val item = "Телевизор"
    val price = products[item] ?: "Нет в наличии"
    println("$item: $price")
}

// -----------------------
// 4. Фильтрация и группировка
// -----------------------
fun task4FilterAndGroup() {
    println("=== Task 4: Filter & Group ===")
    val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)

    val grouped = numbers.groupBy { if (it % 2 == 0) "Четные" else "Нечетные" }
    println(grouped)

    val labeled = numbers.map { "Число: $it" }
    println(labeled)

    val words = listOf("кот", "пес", "кот", "лиса", "пес")
    val unique = words.toSet()
    println("Уникальные слова: $unique")
}

// -----------------------
// 5. Каталог книг
// -----------------------
fun task5Books() {
    println("=== Task 5: Books ===")
    data class Book(val title: String, val author: String, val year: Int)

    val books = listOf(
        Book("Kotlin в действии", "Иванов", 2019),
        Book("Программирование на Java", "Петров", 2018),
        Book("Современный Kotlin", "Иванов", 2021),
    )

    val byIvanov = books.filter { it.author == "Иванов" }
    println("Книги Иванова: $byIvanov")

    val sorted = books.sortedBy { it.year }
    println("Отсортировано по году: $sorted")

    val grouped = books.groupBy { it.author }
    println("Сгруппировано по авторам: $grouped")
}

// -----------------------
// 6. Магазин
// -----------------------
fun task6Store() {
    println("=== Task 6: Store ===")
    val store = mapOf(
        "Молоко" to 70,
        "Хлеб" to 40,
        "Яблоки" to 100
    )
    val cart = listOf("Молоко", "Хлеб", "Сыр")

    var total = 0
    for (item in cart) {
        val price = store[item]
        if (price != null) {
            total += price
        } else {
            println("Товара '$item' нет в магазине")
        }
    }
    println("Общая сумма покупок: $total")
}

// -----------------------
// 7. Студенты
// -----------------------
fun task7Students() {
    println("=== Task 7: Students ===")
    data class Student(val name: String, val group: String)

    val kotlinCourse = setOf(
        Student("Анна", "101"),
        Student("Иван", "102"),
        Student("Мария", "101")
    )

    val javaCourse = setOf(
        Student("Иван", "102"),
        Student("Петр", "103"),
        Student("Ольга", "104")
    )

    val both = kotlinCourse.intersect(javaCourse)
    println("Учится на обоих курсах: $both")

    val grouped = kotlinCourse.groupBy { it.group }
    println("Сгруппировано по группам: $grouped")
}