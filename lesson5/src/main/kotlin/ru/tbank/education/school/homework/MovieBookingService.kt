package ru.tbank.education.school.homework

class SeatAlreadyBookedException(message: String) : Exception(message)

class NoAvailableSeatException(message: String) : Exception(message)

data class BookedSeat(
    val movieId: String,
    val seat: Int
)

class MovieBookingService(
    private val maxQuantityOfSeats: Int
) {
    private val bookings: MutableMap<String, MutableList<Int>> = mutableMapOf()

    init {
        if (maxQuantityOfSeats <= 0) {
            throw IllegalArgumentException(
                "Максимальное количество мест должно быть больше нуля (указано $maxQuantityOfSeats)"
            )
        }
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
        if (seat !in 1..maxQuantityOfSeats) {
            throw IllegalArgumentException(
                "Номер места должен быть в диапазоне от 1 до $maxQuantityOfSeats (указано $seat)"
            )
        }

        val movieSeats = bookings.getOrPut(movieId) { mutableListOf() }

        if (movieSeats.size >= maxQuantityOfSeats) {
            throw NoAvailableSeatException("Все $maxQuantityOfSeats мест для фильма '$movieId' уже забронированы")
        }

        if (seat in movieSeats) {
            throw SeatAlreadyBookedException("Место $seat уже забронировано для фильма '$movieId'")
        }

        movieSeats.add(seat)
    }

    fun cancelBooking(movieId: String, seat: Int) {
        val movieSeats = bookings[movieId]
            ?: throw NoSuchElementException("Нет бронирований для фильма '$movieId'")

        val removed = movieSeats.remove(seat)
        if (!removed) {
            throw NoSuchElementException("Место $seat не было забронировано для фильма '$movieId'")
        }

        if (movieSeats.isEmpty()) {
            bookings.remove(movieId)
        }
    }

    /**
     * Проверяет, забронировано ли место
     *
     * @return true если место занято, false иначе
     */
    fun isSeatBooked(movieId: String, seat: Int): Boolean {
        return bookings[movieId]?.contains(seat) ?: false
    }
}
