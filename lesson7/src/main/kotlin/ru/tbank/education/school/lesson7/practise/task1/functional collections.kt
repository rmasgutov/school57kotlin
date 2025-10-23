package ru.tbank.education.school.lesson7.practise.task1

/**
 * Задание 1.
 * Дан список чисел. Необходимо вернуть только чётные числа.
 * Перепиши императивный код ниже в функциональном стиле (используя filter).
 */
fun filterEvenNumbers(numbers: List<Int>): List<Int> {
//    val evenNumbers = mutableListOf<Int>()
//    for (n in numbers) {
//        if (n % 2 == 0) {
//            evenNumbers.add(n)
//        }
//    }
//    return evenNumbers
    return numbers.filter { it % 2 == 0 }
}

/**
 * Задание 2.
 * Преобразуй все имена в нижний регистр.
 * Используй map вместо цикла.
 */
fun lowerCaseNames(names: List<String>): List<String> {
//    val lowerNames = mutableListOf<String>()
//    for (n in names) {
//        lowerNames.add(n.lowercase())
//    }
//    return lowerNames
    return names.map { it.lowercase() }
}

/**
 * Задание 3.
 * Подсчитай сумму всех чисел в списке.
 * Используй sum() или reduce().
 */
fun sumPrices(prices: List<Int>): Int {
//    var total = 0
//    for (p in prices) {
//        total += p
//    }
//    return total
    return prices.sum()
}

/**
 * Задание 4.
 * Найди средний возраст людей.
 * Используй map и average.
 */
data class Person(val name: String, val age: Int)

fun averageAge(people: List<Person>): Double {
//    var totalAge = 0
//    for (p in people) {
//        totalAge += p.age
//    }
//    return totalAge.toDouble() / people.size
    return people.map { it.age }.average()
}

/**
 * Задание 5.
 * Верни сумму только положительных чисел.
 * Используй filter и sum().
 */
fun sumPositive(numbers: List<Int>): Int {
//    var sum = 0
//    for (n in numbers) {
//        if (n > 0) {
//            sum += n
//        }
//    }
//    return sum
    return numbers.filter { it > 0 }.sum()
}

/**
 * Задание 6.
 * Сгруппируй слова по первой букве.
 * Используй groupBy.
 */
fun groupWordsByFirstLetter(words: List<String>): Map<Char, List<String>> {
//    val grouped = mutableMapOf<Char, MutableList<String>>()
//    for (w in words) {
//        val first = w.first()
//        if (first !in grouped) {
//            grouped[first] = mutableListOf()
//        }
//        grouped[first]!!.add(w)
//    }
//    return grouped
    return words.groupBy { it.first() }
}

/**
 * Задание 7.
 * Проверь, что все числа положительные.
 * Используй all { it > 0 }.
 */
fun areAllPositive(numbers: List<Int>): Boolean {
//    var allPositive = true
//    for (n in numbers) {
//        if (n <= 0) {
//            allPositive = false
//            break
//        }
//    }
//    return allPositive
    return numbers.all { it > 0 }
}


/**
 * Задание 8.
 * Подсчитай количество слов длиной больше 3 символов.
 * Используй count.
 */
fun countLongWords(words: List<String>): Int {
//    var count = 0
//    for (w in words) {
//        if (w.length > 3) count++
//    }
//    return count
    return words.count { it.length > 3 }
}

/**
 * Задание 9.
 * Верни квадраты только чётных чисел.
 * Используй цепочку filter → map.
 */
fun squaredEvenNumbers(numbers: List<Int>): List<Int> {
//    val squaredEven = mutableListOf<Int>()
//    for (n in numbers) {
//        if (n % 2 == 0) {
//            squaredEven.add(n * n)
//        }
//    }
//    return squaredEven
    return numbers.filter { it % 2 == 0 }.map { it * it }
}

/**
 * Задание 10.
 * Удали дубликаты из списка.
 * Используй distinct().
 */
fun uniqueElements(data: List<Int>): List<Int> {
//    val unique = mutableListOf<Int>()
//    for (d in data) {
//        if (d !in unique) {
//            unique.add(d)
//        }
//    }
//    return unique
    return data.distinct()
}

/**
 * Задание 11.
 * Объедини два списка.
 * Используй плюс-оператор (+) или plus().
 */
fun mergeLists(list1: List<Int>, list2: List<Int>): List<Int> {
//    val result = mutableListOf<Int>()
//    for (a in list1) result.add(a)
//    for (b in list2) result.add(b)
//    return result
    return list1 + list2
}


/**
 * Задание 12.
 * Сумма транзакций по категории FOOD.
 * Используй filter и sumOf.
 */
data class Transaction(val id: Int, val amount: Int, val category: String)

fun totalFoodExpenses(txs: List<Transaction>): Int {
//    val foodAmounts = mutableListOf<Int>()
//    for (t in txs) {
//        if (t.category == "FOOD") {
//            foodAmounts.add(t.amount)
//        }
//    }
//    return foodAmounts.sum()
    return txs.filter { it.category == "FOOD" }.sumOf { it.amount }
}