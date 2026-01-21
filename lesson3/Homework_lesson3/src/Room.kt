abstract class Room(
    val roomNumber: String,
    open var price: Double
): Bookable, Cancelable {

    protected open var isOccupied: Boolean = false

    protected var currentGuest: String? = null

    abstract fun calculatePrice(): Double

    open fun info() {
        println("Room $roomNumber, Цена: ${calculatePrice()}")
    }

    override fun book(guestName: String): Boolean {
        return if (!isOccupied) {
            isOccupied = true
            currentGuest = guestName
            true
        } else {
            false
        }
    }

    open fun release() {
        isOccupied = false
    }

    override fun isAvailable(): Boolean = !isOccupied

    override fun cancel(guestName: String): Boolean {
        currentGuest = null
        return true
    }

    override fun getCancellationFee(): Double = calculatePrice() * 0.1


}