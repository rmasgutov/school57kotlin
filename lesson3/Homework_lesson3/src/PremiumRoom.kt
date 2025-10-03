class PremiumRoom(
    roomNumber: String,
    override var price: Double,
    private var hasBalcony: Boolean = false,
    private var housing: String = "Люкс",
) : Room(roomNumber, price), Bookable, Cancelable {

    override var isOccupied: Boolean = false

    override fun calculatePrice(): Double {
        return if (hasBalcony) 5500.0 else 5000.0
    }

    override fun info() {
        println("Премиум номер $roomNumber")
        println("Балкон: ${if (hasBalcony) "Да" else "Нет"}")
        println("Цена за ночь: ${calculatePrice()}")
        println("Статус: ${if (isAvailable()) "Свободен" else "Занят"}")
    }

    fun setHasBalcony(value: Boolean) {
        this.hasBalcony = value
    }

    override fun isAvailable(): Boolean = !isOccupied

    override fun book(guestName: String): Boolean {
        return if (!isOccupied) {
            isOccupied = true
            currentGuest = guestName
            true
        } else {
            false
        }
    }

    override fun release() {
        isOccupied = false
    }

    fun getHasBalcony(): Boolean = hasBalcony

    override fun getCancellationFee(): Double = calculatePrice() * 0.15

    override fun cancel(guestName: String): Boolean {
        currentGuest = null
        return true

    }
}