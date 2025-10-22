package ru.tbank.education.school.lesson3.seminar

abstract class ChessPiece(val color: String) {
    abstract fun move(): String
}

open class Rook(color: String) : ChessPiece(color) {
    override fun move() = "Ходит по прямой"
}

class Queen(color: String) : Rook(color) {
    override fun move(): String {
        return super.move() + " и по диагонали"
    }
}

class Player(val name: String, var rating: Int) {
    constructor(name: String) : this(name, 1200)
}

class Game(private val id: Int) {
    var moves: Int = 0
        set(value) {
            field = if (value >= 0) value else 0
        }
    val isLong: Boolean
        get() = moves > 40
}

class ChessBoard(val pieces: MutableList<ChessPiece>)

data class Move(val from: String, val to: String)

sealed class Result
class Victory(val winner: String) : Result()
class Draw : Result()

fun main() {
    val p1 = Player("Алиса")
    val p2 = Player("Боб", 1500)

    val rook = Rook("Белый")
    val queen = Queen("Чёрный")

    val board = ChessBoard(mutableListOf(rook, queen))
    val game = Game(1)
    game.moves = 45

    println("${p1.name} vs ${p2.name}")
    println("Фигуры на доске: ${board.pieces.map { it.move() }}")
    println("Партия длинная? ${game.isLong}")

    val m = Move("E2", "E4")
    println("Ход: $m")

    val res: Result = Victory(p1.name)
    when (res) {
        is Victory -> println("Победитель: ${res.winner}")
        is Draw -> println("Ничья")
    }
}
