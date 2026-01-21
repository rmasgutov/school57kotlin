fun main() {

    menu()

    val currentState: BookingState = BookingState.Pending
    when (currentState) {
        is BookingState.Pending -> println("Бронирование ожидает подтверждения.")
        is BookingState.Confirmed -> println("Бронирование подтверждено.")
        is BookingState.Cancelled -> println("Бронирование отменено.")
    }
}