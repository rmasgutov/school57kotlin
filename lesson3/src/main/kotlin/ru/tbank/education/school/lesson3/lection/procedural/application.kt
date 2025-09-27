


// данные
val studentName = "Аня"
val studentGrades = listOf(5, 4, 3, 5, 4)

// функция для вычисления среднего балла
fun averageGrade(grades: List<Int>): Double {
    return grades.average()
}

fun main() {
    println("Ученик: $studentName")
    println("Оценки: $studentGrades")
    println("Средний балл: ${averageGrade(studentGrades)}")
}
