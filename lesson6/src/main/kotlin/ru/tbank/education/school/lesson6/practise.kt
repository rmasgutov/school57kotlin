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



    // TODO: проверить, есть ли "Хлеб" в списке

    if("Хлеб" in shoppingList){
        println("yes")
    }else{
        println("no")
    }

   val sortedShoppingList = shoppingList.sorted()
   println(sortedShoppingList)

   val filtered = sortedShoppingList.filter { it.lowercase().startsWith("с") }
   println(filtered)  
}

// -----------------------
// 2. Работа с множествами
// -----------------------
fun task2Sets() {
    println("=== Task 2: Sets ===")
    val kotlinStudents = mutableSetOf("Анна", "Иван", "Мария")
    val javaStudents = setOf("Иван", "Петр", "Ольга")

    kotlinStudents.add("Иван") // we should not add duplicates
    println(kotlinStudents)
    

    if("Мария" in kotlinStudents){
        println("yes")
    }else{
        println("no")
    }
     

    val intersected = kotlinStudents.intersect(javaStudents)
    println(intersected)
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

    products["Телефон"] = 90000
    products["Планшет"] = 40000

    for ((name, price) in products){
        if(price > 10000){
            println(price)
        }
    }

    // imagine some business logic here...

    val product = "Планшет"

    try {
        println(products.getValue(product))
    } catch (e: NoSuchElementException){
        println("Product $product could not be found")
    }
}

// -----------------------
// 4. Фильтрация и группировка
// -----------------------
fun task4FilterAndGroup() {
    println("=== Task 4: Filter & Group ===")
    val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)

    val even = numbers.filter { it % 2 == 0 }
    val odd = numbers.filter { it % 2 == 1 }

    val mapped =  numbers.map { "Число: $it" }

    val words = listOf("кот", "пес", "кот", "лиса", "пес")

    println(words.distinct())
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

   for (book in books){
    if(book.author == "Иванов"){
        println(book)
    }
   }

  
   println( books.sortedBy { it.year })
  
   val grouped = books.groupBy { it.author }

   println(grouped)
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
            println("Product \"$item\" is not presented in the shop")
        }
    }

    println("Total: $total")
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
    println(both)

    val grouped = kotlinCourse.groupBy { it.group }
    println(grouped)
}
