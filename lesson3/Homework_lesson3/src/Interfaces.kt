interface Bookable {
    fun book(guestName: String): Boolean
    fun isAvailable(): Boolean
}
//Интерфейс для отмены бронирования
//getCancellationFee - штраф за частые отмены брони
interface Cancelable {
    fun cancel(guestName: String): Boolean
    fun getCancellationFee(): Double
}

fun interface BookingHandler {
    fun handle(
        serviceName: String,
        guestName: String,
        details: String
    ): Boolean
}

fun interface AvailabilityChecker {
    fun check(
        serviceName: String,
        guestName: String,
        details: String
    ): String
}

//Интерфейс для услуг отеля
interface HotelServiceable {
    fun sheduleInformation(): String
    fun guestState(guestName: String): MutableList<String>
    fun getPrice(): String
}