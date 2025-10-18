package ru.tbank.education.school.lesson7.practise.task3

/**
 * Задание 3. Проверка ролей пользователя
 *
 * Реализуй infix-функцию для проверки наличия роли у пользователя.
 *
 * Пример:
 * ```
 * val user = User("Иван", setOf("USER", "ADMIN"))
 *
 * if (user hasRole "ADMIN") {
 *     println("Доступ разрешён")
 * }
 * ```
 */


// Инфиксная функция для проверки наличия роли
infix fun User.hasRole(role: String): Boolean = TODO()