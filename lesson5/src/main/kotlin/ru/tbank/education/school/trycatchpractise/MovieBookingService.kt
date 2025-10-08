package ru.tbank.education.school.trycatchpractise

/**
 * Интерфейс сервиса бронирования билетов в кинотеатр
 */
interface SaveMovieBookingService {

    /**
     * Бронирует указанное место для фильма.
     *
     * @param movieId идентификатор фильма
     * @param seat номер места
     * @throws IllegalArgumentException если место вне допустимого диапазона
     * @throws SeatAlreadyBookedException если место уже забронировано
     */
    fun bookSeat(movieId: String, seat: Int)

    /**
     * Отменяет бронь указанного места.
     *
     * @param movieId идентификатор фильма
     * @param seat номер места
     * @throws NoSuchElementException если место не было забронировано
     */
    fun cancelBooking(movieId: String, seat: Int)

    /**
     * Проверяет, забронировано ли место
     *
     * @return true если место занято, false иначе
     */
    fun isSeatBooked(movieId: String, seat: Int): Boolean
}

/**
 * Исключение, которое выбрасывается при попытке забронировать занятое место
 */
class SeatAlreadyBookedException(message: String) : Exception(message)

data class Seat(val movieId: String, val seat: Int)


class MovieBookingService(): SaveMovieBookingService {
    var bookedSeats = mutableListOf<Seat>()

    override fun bookSeat(movieId: String, seat: Int) {
        if (seat >= 35) throw IllegalArgumentException()
        if (seat <= 0) throw IllegalArgumentException()
        if (Seat(movieId, seat) in bookedSeats) throw SeatAlreadyBookedException("Seat $seat is already booked")
        bookedSeats.add(Seat(movieId, seat))
    }

    override fun cancelBooking(movieId: String, seat: Int) {
        if (Seat(movieId, seat) !in bookedSeats) throw NoSuchElementException()
        bookedSeats.remove(Seat(movieId, seat))
    }

    override fun isSeatBooked(movieId: String, seat: Int): Boolean {
        return bookedSeats.contains(Seat(movieId, seat))
    }
}