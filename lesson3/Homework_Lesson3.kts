abstract class Room(
    val roomNumber: String,
    open var price: Double
) {
    protected var isOccupied: Boolean = false
    abstract fun calculatePrice(): Double
    open fun info() {
        println("Room $roomNumber, Цена: ${calculatePrice()}")
    }
}

class PremiumRoom(
    roomNumber: String,
    override var price: Double,
    private var hasBalcony: Boolean = false,
    private var housing: String = "Люкс",
) : Room(roomNumber, price) {

    override fun calculatePrice(): Double {
        return if (hasBalcony) 5500.0 else 5000.0
    }

    override fun info() {
        println("Премиум номер $roomNumber")
        println("Балкон: ${if (hasBalcony) "Да" else "Нет"}")
        println("Цена за ночь: ${calculatePrice()}")
    }

    fun setHasBalcony(value: Boolean) {
        this.hasBalcony = value
    }
}

class SampleRoom(
    roomNumber: String,
    override var price: Double,
    private var hasBalcony: Boolean = false,
    private var housing: String = "Апарт",
) : Room(roomNumber, price) {

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
    }

    fun setHasBalcony(value: Boolean) {
        this.hasBalcony = value
    }

    fun setHousing(housingType: String) {
        this.housing = housingType
    }
}

class Hotel(
    val name: String,
) {
    private val rooms = mutableListOf<Room>()

    constructor(name: String, initialRooms: List<Room>) : this(name) {
        rooms.addAll(initialRooms)
    }

    fun addRoom(room: Room) {
        rooms += room
    }

    fun showRooms() {
        println("\nДоступные номера в отеле '$name':")
        if (rooms.isEmpty()) {
            println("Нет доступных номеров.")
        } else {
            rooms.forEachIndexed { index, room ->
                println("${index + 1}. Номер: ${room.roomNumber}, Цена за ночь: ${room.calculatePrice()}")
            }
        }
    }

    fun findRoomByNumber(roomNumber: String): Room? {
        return rooms.find { it.roomNumber == roomNumber }
    }
}

data class BookingInfo(
    val roomNumber: String,
    val guestName: String,
    val nights: Int
)

sealed class BookingState {
    object Pending : BookingState()
    object Confirmed : BookingState()
    object Cancelled : BookingState()
}

class Guest(val name: String, val bookedRooms: MutableList<Room> = mutableListOf()) {
    fun bookRoom(room: Room) {
        bookedRooms.add(room)
    }
}

fun menu() {
    val hotel = Hotel("Grand Plaza")
    hotel.addRoom(SampleRoom("101", 3000.0))
    hotel.addRoom(PremiumRoom("201", 5500.0))
    hotel.addRoom(SampleRoom("102", 2500.0))
    hotel.addRoom(PremiumRoom("202", 5500.0))
    while (true) {
        println("\n--- Меню ---")
        println("1. Просмотр доступных номеров")
        println("2. Забронировать номер")
        println("3. Выйти")
        print("Выберите действие: ")
        when (readln()) {
            "1" -> hotel.showRooms()
            "2" -> {
                println("Введите номер номера для бронирования:")
                val roomNumber = readln()
                val room = hotel.findRoomByNumber(roomNumber)
                if (room != null) {
                    println("Введите ваше имя:")
                    val guestName = readln()
                    val guest = Guest(guestName)
                    guest.bookRoom(room)
                    println("Комната $roomNumber успешно забронирована для $guestName.")
                } else {
                    println("Номер не найден.")
                }
            }
            "3" -> {
                println("Выход из программы.")
                break
            }
            else -> println("Некорректный ввод. Попробуйте снова.")
        }
    }
}

fun main() {
    menu()
    val currentState: BookingState = BookingState.Pending
    when (currentState) {
        is BookingState.Pending -> println("Бронирование ожидает подтверждения.")
        is BookingState.Confirmed -> println("Бронирование подтверждено.")
        is BookingState.Cancelled -> println("Бронирование отменено.")
    }
}