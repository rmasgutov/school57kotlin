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
    init {
        if (maxQuantityOfSeats <= 0) {
            throw  IllegalArgumentException("Максимальное количество мест должно быть положительным")
        }
    }
    var BookingSeats : MutableMap<String, BooleanArray> = mutableMapOf()
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
            throw IllegalArgumentException("Номер места некорректен")
        }
        var isbooked = true
        BookingSeats[movieId]?.forEach {flag = flag && it}
        if (flag) {
            throw NoAvailableSeatException("Все места уже заняты.")
        }
        if (BookingSeats[movieId]?.get(seat) == true) {
            throw SeatAlreadyBookedException("Место уже забронирвано.")
        }
        BookingSeats[movieId]?.set(seat, true)
    }

    /**
     * Отменяет бронь указанного места.
     *
     * @param movieId идентификатор фильма
     * @param seat номер места
     * @throws NoSuchElementException если место не было забронировано
     */
    fun cancelBooking(movieId: String, seat: Int) {
        if (BookingSeats[movieId]?.get(seat) == false) {
            throw NoSuchElementException("Место не было забронировано")
        }
        BookingSeats[movieId]?.set(seat, false)
    }

    /**
     * Проверяет, забронировано ли место
     *
     * @return true если место занято, false иначе
     */
    fun isSeatBooked(movieId: String, seat: Int): Boolean {
        return BookingSeats[movieId]?.get(seat)
    }
}