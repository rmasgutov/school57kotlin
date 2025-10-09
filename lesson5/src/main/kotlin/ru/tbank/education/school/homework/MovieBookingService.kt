package ru.tbank.education.school.homework

import kotlin.collections.mutableListOf

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

class MovieBookingService(private val maxQuantityOfSeats: Int) {
    private val bookings: MutableMap<String, MutableList<Int>> = mutableMapOf()
    init {
        if (maxQuantityOfSeats <= 0) {
            throw IllegalArgumentException("Максимальное кол-во место должно быть больше нуля.")
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
            throw IllegalArgumentException("Номер места не в диапазоне.")
        }

        val bookedSeats = bookings.getOrPut(movieId) { mutableListOf() }

        if (seat in bookedSeats) {
            throw SeatAlreadyBookedException("Место $seat уже забронировано для фильма '$movieId'.")
        }

        if (bookedSeats.size >= maxQuantityOfSeats) {
            throw NoAvailableSeatException("Все места для фильма '$movieId' уже забронированы.")
        }

        bookedSeats.add(seat)
    }

    /**
     * Отменяет бронь указанного места.
     *
     * @param movieId идентификатор фильма
     * @param seat номер места
     * @throws NoSuchElementException если место не было забронировано
     */
    fun cancelBooking(movieId: String, seat: Int) {
        val bookedSeats = bookings[movieId]
            ?: throw NoSuchElementException("Место $seat для фильма '$movieId' не было забронировано.")
        if (!bookedSeats.remove(seat)) {
            throw NoSuchElementException("Место $seat для фильма '$movieId' не забронировано.")
        }

        if (bookedSeats.isEmpty()) {
            bookings.remove(movieId)
        }
    }

    /**
     * Проверяет, забронировано ли место
     *
     * @return true если место занято, false иначе
     */
    fun isSeatBooked(movieId: String, seat: Int): Boolean {
        val bookedSeats = bookings[movieId]
        return bookedSeats?.contains(seat) ?: false
    }
}