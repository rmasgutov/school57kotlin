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
            throw IllegalArgumentException("Максимальное кол-во мест отрицательное или равно нулю")
        }
    }

    private val allBookedSeats = mutableListOf<BookedSeat>()

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
        try {
            if (seat < 1 or seat > maxQuantityOfSeats) {
                throw IllegalArgumentException("Номер места некорректен. Место $seat не входит в диапозон от 1 до $maxQuantityOfSeats")
            }
            for (bookedSeat in allBookedSeats) {
                if (bookedSeat.movieId == movieId and bookedSeat.seat == seat) {
                    throw SeatAlreadyBookedException("Место $seat уже было забронировано для фильма $movieId")
                }
            }
            var boco = 0
            for (bookedSeat in allBookedSeats) {
                if (bookedSeat.movieId == movieId) {
                    boco++
                }
            }
            if (boco >= maxQuantityOfSeats) {
                throw NoAvailableSeatException("Нет свободных мест на фильм $movieId")
            }
            allBookedSeats.add(BookedSeat(movieId, seat))
        } catch (e: IllegalArgumentException) {
            throw e
        } catch (e: SeatAlreadyBookedException) {
            throw e
        } catch (e: NoAvailableSeatException) {
            throw e
        } finally {
        }
    }

    /**
     * Отменяет бронь указанного места.
     *
     * @param movieId идентификатор фильма
     * @param seat номер места
     * @throws NoSuchElementException если место не было забронировано
     */
    fun cancelBooking(movieId: String, seat: Int) {
        try {
            var ib: BookedSeat? = null
            for (bookedSeat in allBookedSeats) {
                if (bookedSeat.movieId == movieId and bookedSeat.seat == seat) {
                    ib = bookedSeat
                    break
                }
            }
            if (ib == null) {
                throw NoSuchElementException("Место $seat не забронировано для фильма $movieId")
            }
            allBookedSeats.remove(ib)
        } catch (e: NoSuchElementException) {
            throw e
        } finally {
        }
    }

    /**
     * Проверяет, забронировано ли место
     *
     * @return true если место занято, false иначе
     */
    fun isSeatBooked(movieId: String, seat: Int): Boolean {
        for (bookedSeat in allBookedSeats) {
            if (bookedSeat.movieId == movieId and bookedSeat.seat == seat) {
                return true
            }
        }
 ``       return false
    }
}

