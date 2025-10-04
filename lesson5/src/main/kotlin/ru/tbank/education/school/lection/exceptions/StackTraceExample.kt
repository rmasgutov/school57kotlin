package ru.tbank.education.school.lection.exceptions

import kotlin.RuntimeException

fun main() {
    processUserRequest("123")
}

// Уровень 1: Обработка пользовательского запроса
fun processUserRequest(userId: String) {
    try {
        val userData = fetchUserData(userId)
        println("Данные пользователя: $userData")
    } catch (e: RuntimeException) {
        throw RuntimeException("Не удалось обработать запрос пользователя", e)
    }
}

// Уровень 2: Получение данных пользователя
fun fetchUserData(userId: String): String {
    try {
        return loadUserFromDatabase(userId)
    } catch (e: RuntimeException) {
        throw RuntimeException("Ошибка при получении данных пользователя", e)
    }
}

// Уровень 3: Загрузка из "базы данных"
fun loadUserFromDatabase(userId: String): String {
    try {
        return queryDatabase(userId)
    } catch (e: RuntimeException) {
        throw RuntimeException("Ошибка при обращении к базе данных", e)
    }
}

// Уровень 4: Имитация SQL-запроса
fun queryDatabase(userId: String): String {
    throw RuntimeException("Ошибка SQL: Не удалось подключиться к базе данных")
}