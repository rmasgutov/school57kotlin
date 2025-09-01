package ru.tbank.education.school.lesson6.cache

/**
 * Простой интерфейс для In-Memory кэша.
 */
interface InMemoryCache<K, V> {
    /**
     * Сохраняет значение value под ключом key в кэш.
     */
    fun put(key: K, value: V)

    /**
     * Получает значение из кэша по ключу key.
     * Возвращает null, если такого ключа нет.
     */
    fun get(key: K): V?
    
    /**
     * Удаляет значение из кэша по ключу key.
     */
    fun remove(key: K)
    
    /**
     * Очищает весь кэш.
     */
    fun clear()
}
