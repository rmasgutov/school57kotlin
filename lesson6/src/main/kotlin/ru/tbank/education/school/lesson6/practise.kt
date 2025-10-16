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
    println("Хлеб" in shoppingList)
    println(shoppingList.sorted())
    println(shoppingList.filter {it[0] == 'С'})

    // TODO: проверить, есть ли "Хлеб" в списке
    // TODO: отсортировать список по алфавиту и вывести
    // TODO: вывести только товары, начинающиеся на букву "С"
}

// -----------------------
// 2. Работа с множествами
// -----------------------
fun task2Sets() {
    println("=== Task 2: Sets ===")
    val kotlinStudents = mutableSetOf("Анна", "Иван", "Мария")
    val javaStudents = setOf("Иван", "Петр", "Ольга")
    kotlinStudents.add("Анна")
    println("Мария" in kotlinStudents)
    println(kotlinStudents.filter {it in javaStudents})
    // TODO: добавить дубликат в kotlinStudents и посмотреть, что произойдет
    // TODO: проверить, есть ли "Мария" в списке студентов Kotlin
    // TODO: найти пересечение студентов Kotlin и Java курсов
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
    products["Телефон"] = 100000
    products["Планшет"] = 40000
    println(products.filter {it.value > 10000})
    val a = readLine()
    if(!products.containsKey(a)){
        println("Такого продукта нет")
    }
    else{
        println(products[a])
    }
    // TODO: изменить цену для "Телефон"
    // TODO: добавить новый товар "Планшет" с ценой 40000
    // TODO: вывести только товары дороже 10000
    // TODO: обработать случай, когда пользователь запрашивает отсутствующий товар
}

// -----------------------
// 4. Фильтрация и группировка
// -----------------------
fun task4FilterAndGroup() {
    println("=== Task 4: Filter & Group ===")
    val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
    val evenNumbers = numbers.filter {it % 2 == 0}
    val oddNumbers = numbers.filter {it % 2 == 1}
    println(evenNumbers)
    println(oddNumbers)
    // TODO: сгруппировать числа на четные и нечетные
    // TODO: преобразовать список чисел в список строк "Число: X"

    val words = listOf("кот", "пес", "кот", "лиса", "пес")
    // TODO: выделить только уникальные слова
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

    // TODO: найти все книги автора "Иванов"
    // TODO: отсортировать книги по году
    // TODO: сгруппировать книги по авторам
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

    // TODO: посчитать общую стоимость покупок
    // TODO: вывести сообщение для товаров, которых нет в магазине
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

    // TODO: найти студентов, которые учатся и на Kotlin, и на Java
    // TODO: сгруппировать студентов Kotlin по группам
}
