package ru.tbank.education.school.homework

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class MovieBookingServiceTests {
    private lateinit var service: MovieBookingService

    @BeforeEach
    fun setUp() {
        service = MovieBookingService(5)
    }

    @Test
    fun `should throw IllegalArgumentException when maxQuantityOfSeats is zero`() {
        assertThrows<IllegalArgumentException> {
            MovieBookingService(0)
        }
    }

    @Test
    fun `should throw IllegalArgumentException when maxQuantityOfSeats is negative`() {
        assertThrows<IllegalArgumentException> {
            MovieBookingService(-5)
        }
    }

    @Test
    fun `should throw IllegalArgumentException when seat is out of range`() {
        assertThrows<IllegalArgumentException> {
            service.bookSeat("movie1", 6)
        }
    }

    @Test
    fun `should throw NoAvailableSeatException when all seats are booked`() {
        // Занимаем все места
        for (i in 1..5) {
            service.bookSeat("movie1", i)
        }

        assertThrows<NoAvailableSeatException> {
            service.bookSeat("movie1", 1)
        }
    }

    @Test
    fun `should throw SeatAlreadyBookedException when seat is already booked`() {
        service.bookSeat("movie1", 1)

        assertThrows<SeatAlreadyBookedException> {
            service.bookSeat("movie1", 1)
        }
    }

    @Test
    fun `should book seat successfully`() {
        service.bookSeat("movie1", 1)
        assertTrue(service.isSeatBooked("movie1", 1))
    }

    @Test
    fun `should cancel booking successfully`() {
        service.bookSeat("movie1", 1)
        service.cancelBooking("movie1", 1)

        assertFalse(service.isSeatBooked("movie1", 1))
    }

    @Test
    fun `should throw NoSuchElementException when canceling unbooked seat`() {
        assertThrows<NoSuchElementException> {
            service.cancelBooking("movie1", 1)
        }
    }

    @Test
    fun `should check if seat is booked`() {
        assertFalse(service.isSeatBooked("movie1", 1))

        service.bookSeat("movie1", 1)
        assertTrue(service.isSeatBooked("movie1", 1))
    }
}