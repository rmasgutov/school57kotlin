package ru.tbank.education.school.homework

class SeatAlreadyBookedException(message: String) : Exception(message)

class NoAvailableSeatException(message: String) : Exception(message)

data class BookedSeat(
    val movieId: String, // идентификатор фильма
    val seat: Int        // номер места
)

class MovieBookingService(
    private val maxQuantityOfSeats: Int // Максимальное кол-во мест
) {

    // Хранилище бронирований: фильм -> список занятых мест
    private val data = mutableMapOf<String, MutableList<Int>>()

    init {
        require(maxQuantityOfSeats > 0) {
            "maxQuantityOfSeats должен быть > 0, получено: $maxQuantityOfSeats"
        }
    }

    fun bookSeat(movieId: String, seat: Int) {
        if (seat !in 1..maxQuantityOfSeats) {
            throw IllegalArgumentException("Номер места вне диапазона: $seat (1..$maxQuantityOfSeats)")
        }
        val seats = data.getOrPut(movieId) { mutableListOf() }

        // Важно: сначала проверяем, что зал для этого фильма заполнен,
        // чтобы кейс из тестов кидал именно NoAvailableSeatException.
        if (seats.size >= maxQuantityOfSeats) {
            throw NoAvailableSeatException("Нет свободных мест для фильма $movieId")
        }
        if (seat in seats) {
            throw SeatAlreadyBookedException("Место $seat уже забронировано для $movieId")
        }
        seats.add(seat)
    }

    fun cancelBooking(movieId: String, seat: Int) {
        val seats = data[movieId] ?: throw NoSuchElementException("Бронирование для $movieId не найдено")
        if (!seats.remove(seat)) {
            throw NoSuchElementException("Место $seat не было забронировано для $movieId")
        }
        if (seats.isEmpty()) data.remove(movieId)
    }

    fun isSeatBooked(movieId: String, seat: Int): Boolean {
        return data[movieId]?.contains(seat) == true
    }
}