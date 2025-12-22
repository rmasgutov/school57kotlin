package ru.tbank.education.school.lesson6

data class Player(
    val id: Int,
    val name: String,
    var score: Int,
    val level: Int
)

/**
 * Класс для управления рейтингом игроков в онлайн-игре.
 * Поддерживает операции добавления, обновления и поиска игроков.
 */
class Leaderboard {
    private val players = mutableMapOf<Int, Player>()

    /**
     * Добавляет нового игрока в рейтинг.
     * @param player Объект игрока
     * @throws IllegalArgumentException если игрок с таким ID уже существует
     */
    fun addPlayer(player: Player) {}

    /**
     * Обновляет счет игрока.
     * @param playerId ID игрока
     * @param newScore Новое значение счета
     * @throws IllegalArgumentException если игрок не найден
     */
    fun updateScore(playerId: Int, newScore: Int) {}

    /**
     * Возвращает топ-N игроков по счету.
     * @param limit Количество возвращаемых игроков (по умолчанию 10)
     * @return Список игроков отсортированный по убыванию счета
     */
    fun getTopPlayers(limit: Int = 10): List<Player> = TODO()

    /**
     * Фильтрует игроков по уровню.
     * @param level Уровень для фильтрации
     * @return Список игроков указанного уровня
     */
    fun getPlayersByLevel(level: Int): List<Player> = TODO()

    /**
     * Удаляет игрока из рейтинга.
     * @param playerId ID игрока для удаления
     * @return true если игрок был удален, false если не найден
     */
    fun removePlayer(playerId: Int): Boolean = TODO()

    /**
     * Находит игроков с счетом в указанном диапазоне.
     * @param min Нижняя граница диапазона
     * @param max Верхняя граница диапазона
     * @return Список игроков с счетом в диапазоне [min, max]
     */
    fun getPlayersInScoreRange(min: Int, max: Int): List<Player> = TODO()
}