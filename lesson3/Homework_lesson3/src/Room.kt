abstract class Room(
    val roomNumber: String,
    open var price: Double,
) {
    protected var isOccupied: Boolean = false

    abstract fun calculatePrice(): Double

    open fun info() {
        println("Room $roomNumber, Цена: ${calculatePrice()}")
    }
}