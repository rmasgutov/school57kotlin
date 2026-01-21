import java.awt.print.Book

class SampleRoom(
    roomNumber: String,
    override var price: Double,
    private var hasBalcony: Boolean = false,
    private var housing: String = "Апарт",
) : Room(roomNumber, price), Bookable, Cancelable {

    override var isOccupied: Boolean = false

    override fun calculatePrice(): Double {
        return when (housing) {
            "Апарт" -> if (hasBalcony) 2000.0 else 1750.0
            "Спа" -> if (hasBalcony) 3000.0 else 2500.0
            "Конгресс" -> if (hasBalcony) 4000.0 else 3700.0
            else -> 1750.0
        }
    }

    override fun info() {
        println("Общий номер $roomNumber")
        println("Где находится: $housing")
        println("Балкон: ${if (hasBalcony) "Да" else "Нет"}")
        println("Цена за ночь: ${calculatePrice()}")
        println("Статус: ${if (isAvailable()) "Свободен" else "Занят"}")
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

    fun setHasBalcony(value: Boolean) {
        this.hasBalcony = value
    }

    fun setHousing(housingType: String) {
        this.housing = housingType
    }

    fun getHasBalcony(): Boolean = hasBalcony

    fun getHousing(): String = housing

    override fun getCancellationFee(): Double = calculatePrice() * 0.15

    override fun cancel(guestName: String): Boolean {
        currentGuest = null
        return true

    }
}