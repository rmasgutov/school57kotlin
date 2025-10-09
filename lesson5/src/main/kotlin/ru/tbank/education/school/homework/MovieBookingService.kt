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
            throw IllegalArgumentException("Максимальное количество мест должно быть положительным числом. " +
                    "Получено: $maxQuantityOfSeats")
        }
    }

    private val bookedSeats = mutableSetOf<BookedSeat>()

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
        if (seat > maxQuantityOfSeats) throw IllegalArgumentException("Место $seat вне допустимого диапазона." +
                "Максимальный номер места: $maxQuantityOfSeats")
        if (seat <= 0) throw IllegalArgumentException("Номер места должен быть положительным числом. " +
                "Получено: $seat")

        if (bookedSeats.size >= maxQuantityOfSeats) {
            throw NoAvailableSeatException("Нет свободных мест. Все $maxQuantityOfSeats мест уже заняты")
        }

        if (BookedSeat(movieId, seat) in bookedSeats) throw SeatAlreadyBookedException(
            "Место $seat уже забронировано для фильма $movieId")
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
        if (BookedSeat(movieId, seat) !in bookedSeats) throw NoSuchElementException(
            "Бронь для места $seat фильма $movieId не найдена")
        bookedSeats.remove(BookedSeat(movieId, seat))
    }

    /**
     * Проверяет, забронировано ли место
     *
     * @return true если место занято, false иначе
     */
    fun isSeatBooked(movieId: String, seat: Int): Boolean {
        return bookedSeats.contains(BookedSeat(movieId, seat))
    }
}
