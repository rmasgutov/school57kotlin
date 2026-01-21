abstract class BaseHotelService : HotelServiceable {
    abstract val serviceName: String
    abstract val basePrice: Double

    override fun getPrice(): String = "$basePrice RUB"

    fun provideService(guestName: String, handler: BookingHandler): Boolean {
        println("Предоставление услуги $serviceName для $guestName")
        return handler.handle(serviceName, guestName, details = "")
    }

}