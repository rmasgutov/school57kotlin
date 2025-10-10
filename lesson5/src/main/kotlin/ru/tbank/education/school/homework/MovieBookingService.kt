package ru.tbank.education.school.homework

/**
 * Исключение, которое выбрасывается при попытке забронировать занятое место
 */
class SeatAlreadyBookedException(message: String) : Exception(message)

/**
 * Исключение, которое выбрасывается при попытке забронировать место при отсутствии свободных мест
 */
class NoAvailableSeatException(message: String) : Exception(message)

/*
data class BookedSeat(
    val movieId: String, // идентификатор фильма
    val seat: Int // номер места
)
*/

class MovieBookingService(
    private val maxQuantityOfSeats: Int // Максимальное кол-во мест
) {
    init {
        if (maxQuantityOfSeats <= 0) {
            throw IllegalArgumentException("Число мест в зале должно быть не менее одного")
        }
    }
    var Seats : MutableMap<String, BooleanArray> = mutableMapOf()

    /**
     * Бронирует указанное место для фильма.
     *
     * @param movieId идентификатор фильма
     * @param seat номер места
     * @throws IllegalArgumentException если номер места вне допустимого диапазона
     * @throws NoAvailableSeatException если нет больше свободных мест
     * @throws SeatAlreadyBookedException если место уже забронировано
     */
    fun bookSeat(movieId: String, seat: Int) {
        if (seat !in 1..maxQuantityOfSeats) {
            throw IllegalArgumentException("Некорректный номер места")
        }
        if (!Seats.containsKey(movieId)) {
            Seats[movieId] = BooleanArray(maxQuantityOfSeats + 1)
            Seats[movieId]?.set(0, true)
            Seats[movieId]?.set(seat, true)
            return
        }
        if (Seats[movieId]!!.all { it }) {throw NoAvailableSeatException("Все места заняты")}
        if (Seats[movieId]!![seat]) {throw SeatAlreadyBookedException("Место забронированно")}
        Seats[movieId]?.set(seat, true)
    }

    /**
     * Отменяет бронь указанного места.
     *
     * @param movieId идентификатор фильма
     * @param seat номер места
     * @throws NoSuchElementException если место не было забронировано
     */
    fun cancelBooking(movieId: String, seat: Int) {
        if (!Seats[movieId]!![seat]) { throw NoSuchElementException("Место не забронированно") }
        Seats[movieId]!![seat] = false
    }

    /**
     * Проверяет, забронировано ли место
     *
     * @return true если место занято, false иначе
     */
    fun isSeatBooked(movieId: String, seat: Int): Boolean {
        return Seats[movieId]?.get(seat) ?: false
    }
}