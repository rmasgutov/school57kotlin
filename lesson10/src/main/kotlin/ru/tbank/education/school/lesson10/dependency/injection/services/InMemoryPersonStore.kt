package ru.tbank.education.school.lesson10.dependency.injection.services

import ru.tbank.education.school.lesson10.dependency.injection.annotations.Component
import ru.tbank.education.school.lesson10.dependency.injection.model.Person

/**
 * In-memory реализация хранилища пользователей.
 *
 * Использует HashMap для хранения пользователей в памяти.
 * Помечен @Component для автоматической регистрации в DI-контейнере.
 */
@Component
class InMemoryPersonStore : PersonStore {
    /**
     * Внутреннее хранилище: Map с ключом - имя пользователя, значением - объект Person.
     */
    private val store = mutableMapOf<String, Person>()

    /**
     * Получает пользователя по имени из хранилища.
     * Возвращает null, если пользователь не найден.
     */
    override fun getPerson(name: String): Person? {
        return store[name]
    }

    /**
     * Сохраняет пользователя в хранилище.
     * Если пользователь с таким именем уже существует, он будет перезаписан.
     */
    override fun save(p: Person) {
        store[p.name] = p
    }

    /**
     * Возвращает список всех пользователей из хранилища.
     */
    override fun getPersons(): List<Person> {
        return store.values.toList()
    }
}
