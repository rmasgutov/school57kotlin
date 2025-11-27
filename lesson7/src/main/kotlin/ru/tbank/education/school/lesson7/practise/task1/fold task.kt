package ru.tbank.education.school.lesson7.practise.task1

/**
 *
 * Дано: список сотрудников.
 *
 * Нужно:
 *  - Посчитать общую сумму зарплат (`totalSalary`),
 *  - Среднюю зарплату (`avgSalary`),
 *  - Количество отделов (`departmentsCount`).
 *
 * Верни результат в виде `SalaryReport`.
 *
 * Подсказки:
 *  - `fold(initial, operation)` — позволяет задать аккумулятор с другим типом.
 *  - Используй `Set` для накопления уникальных департаментов.
 *
 * Пример:
 * ```
 * employees = [
 *   ("Anna", 100000, "IT"),
 *   ("Ivan", 80000, "IT"),
 *   ("Olga", 70000, "HR")
 * ]
 * → SalaryReport(totalSalary=250000, avgSalary≈83333.33, departmentsCount=2)
 * ```
 */
data class Employee(val name: String, val salary: Double, val department: String)
data class SalaryReport(val totalSalary: Double, val avgSalary: Double, val departmentsCount: Int)

fun buildSalaryReport(employees: List<Employee>): SalaryReport {
    TODO()
}
