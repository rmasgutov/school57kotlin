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
    fun addPlayer(player: Player) {
        if (players.containsKey(player.id)) {
            throw IllegalArgumentException("Игрок с ID ${player.id} уже существует")
        }
        players[player.id] = player
    }

    /**
     * Обновляет счет игрока.
     * @param playerId ID игрока
     * @param newScore Новое значение счета
     * @throws IllegalArgumentException если игрок не найден
     */
    fun updateScore(playerId: Int, newScore: Int) {
        val player = players[playerId] ?: throw IllegalArgumentException("Игрок с ID $playerId не найден")
        player.score = newScore
    }

    /**
     * Возвращает топ-N игроков по счету.
     * @param limit Количество возвращаемых игроков (по умолчанию 10)
     * @return Список игроков отсортированный по убыванию счета
     */
    fun getTopPlayers(limit: Int = 10): List<Player> {
        val all = players.values.toMutableList()
        for (i in 0 until all.size - 1) {
            for (j in 0 until all.size - i - 1) {
                if (all[j].score < all[j + 1].score) {
                    val temp = all[j]
                    all[j] = all[j + 1]
                    all[j + 1] = temp
                }
            }
        }
        val result = mutableListOf<Player>()
        var count = 0
        for (p in all) {
            if (count >= limit) break
            result.add(p)
            count++
        }
        return result
    }

    /**
     * Фильтрует игроков по уровню.
     * @param level Уровень для фильтрации
     * @return Список игроков указанного уровня
     */
    fun getPlayersByLevel(level: Int): List<Player> {
        val result = mutableListOf<Player>()
        for (p in players.values) {
            if (p.level == level) result.add(p)
        }
        return result
    }

    /**
     * Удаляет игрока из рейтинга.
     * @param playerId ID игрока для удаления
     * @return true если игрок был удален, false если не найден
     */
    fun removePlayer(playerId: Int): Boolean {
        return players.remove(playerId) != null
    }

    /**
     * Находит игроков с счетом в указанном диапазоне.
     * @param min Нижняя граница диапазона
     * @param max Верхняя граница диапазона
     * @return Список игроков с счетом в диапазоне [min, max]
     */
    fun getPlayersInScoreRange(min: Int, max: Int): List<Player> {
        val result = mutableListOf<Player>()
        for (p in players.values) {
            if (p.score in min..max) result.add(p)
        }
        return result
    }
}