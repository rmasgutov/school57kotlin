package ru.tbank.education.school.homework

/**
 * Исключение, которое выбрасывается при попытке забронировать занятое место
 */
class SeatAlreadyBookedException(message: String) : Exception(message)

/**
 * Исключение, которое выбрасывается при попытке забронировать место при отсутствии свободных мест
 */
class NoAvailableSeatException(message: String) : Exception(message)

data class BookedSeat(
    val movieId: String, // идентификатор фильма
    val seat: Int // номер места
)

class MovieBookingService(
    private val maxQuantityOfSeats: Int // Максимальное кол-во мест
) {
    private val bookedSeats = mutableListOf<BookedSeat>()

    init {
        require(maxQuantityOfSeats > 0) { "максимальное кол-во мест отрицательное или равно нулю" }
    }

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
        if (seat <= 0 || seat > maxQuantityOfSeats) {
            throw IllegalArgumentException("от 1 до $maxQuantityOfSeats")
        }
        val bookedSeatsForMovie = bookedSeats.count { it.movieId == movieId }
        if (bookedSeatsForMovie >= maxQuantityOfSeats) {
            throw NoAvailableSeatException("все места заняты для фильма: $movieId")
        }
        val isSeatAlreadyBooked = bookedSeats.any { it.movieId == movieId && it.seat == seat }

        if (isSeatAlreadyBooked) {
            throw SeatAlreadyBookedException("место $seat уже забронировано для $movieId")
        }
        bookedSeats.add(BookedSeat(movieId, seat))
    }


    /**
     * Отменяет бронь указанного места.
     *
     * @param movieId идентификатор фильма
     * @param seat номер места
     * @throws NoSuchElementException если место не было забронировано
     */
    fun cancelBooking(movieId: String, seat: Int) {
        val bookedSeat = BookedSeat(movieId, seat)
        val wasRemoved = bookedSeats.remove(bookedSeat)

        if (!wasRemoved) {
            throw NoSuchElementException("место $seat не было забронировано для $movieId")
        }
    }


    /**
     * Проверяет, забронировано ли место
     *
     * @return true если место занято, false иначе
     */
    fun isSeatBooked(movieId: String, seat: Int): Boolean {
        return bookedSeats.any { it.movieId == movieId && it.seat == seat }
    }
}