package ru.tbank.education.school.lesson6.cache

/**
 * Интерфейс для простого кэша с временем жизни записей.
 * Автоматически удаляет устаревшие записи при обращении.
 */
interface TTLCache<K, V> {
    
    /**
     * Помещает значение в кэш с указанным временем жизни.
     * @param key Ключ для доступа к значению
     * @param value Сохраняемое значение
     * @param ttlMs Время жизни записи в миллисекундах
     */
    fun put(key: K, value: V, ttlMs: Long)
    
    /**
     * Помещает значение в кэш со временем жизни по умолчанию (60 секунд).
     * @param key Ключ для доступа к значению
     * @param value Сохраняемое значение
     */
    fun put(key: K, value: V)
    
    /**
     * Получает значение из кэша по ключу.
     * Автоматически удаляет устаревшие записи при обращении.
     * @param key Ключ для поиска значения
     * @return Значение или null если не найдено или устарело
     */
    fun get(key: K): V?
}
data class TTLCashValue<V>(val value: V, val ttlMs: Long)

class TTLCacheImpl<K, V> : TTLCache<K, V> {
    protected val cacheData = HashMap<K, TTLCashValue<V>>()
    override fun put(key: K, value: V, ttlMs: Long) {
        cacheData[key] = TTLCashValue(value, System.currentTimeMillis() + ttlMs)
    }
    override fun put(key: K, value: V) {
        put(key, value, ttlMs = 60000)
    }
    override fun get(key: K): V? {
        if(key in cacheData){
            if(System.currentTimeMillis() < cacheData[key]!!.ttlMs){
                return cacheData[key]!!.value
            }
            else{
                cacheData.remove(key)
                return null
            }
        }
        else {
            return null
        }
    }
}