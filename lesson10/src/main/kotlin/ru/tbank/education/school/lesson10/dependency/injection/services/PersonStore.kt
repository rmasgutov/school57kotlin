package ru.tbank.education.school.lesson10.dependency.injection.services

import ru.tbank.education.school.lesson10.dependency.injection.model.Person

/**
 * Интерфейс хранилища пользователей.
 * Определяет контракт для работы с хранилищем пользователей.
 */
interface PersonStore {
    /**
     * Получает пользователя по имени.
     *
     * @param name имя пользователя
     * @return Optional с пользователем, если найден, иначе пустой Optional
     */
    fun getPerson(name: String): Person?

    /**
     * Сохраняет пользователя в хранилище.
     *
     * @param p пользователь для сохранения
     */
    fun save(p: Person)

    /**
     * Возвращает список всех пользователей.
     *
     * @return список всех сохранённых пользователей
     */
    fun getPersons(): List<Person>
}
