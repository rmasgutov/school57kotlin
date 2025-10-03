abstract class Room(
    val roomNumber: String,
    open var price: Double
) {
    protected var isOccupied: Boolean = false
    abstract fun calculatePrice(): Double
    open fun info() {
        println("Room $roomNumber, Цена: ${calculatePrice()}")
    }

    fun book() {
        isOccupied = true
    }

    fun release() {
        isOccupied = false
    }

    fun isAvailable(): Boolean = !isOccupied
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
        println("Статус: ${if (isAvailable()) "Свободен" else "Занят"}")
    }

    fun setHasBalcony(value: Boolean) {
        this.hasBalcony = value
    }

    fun getHasBalcony(): Boolean = hasBalcony
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
        println("Статус: ${if (isAvailable()) "Свободен" else "Занят"}")
    }

    fun setHasBalcony(value: Boolean) {
        this.hasBalcony = value
    }

    fun setHousing(housingType: String) {
        this.housing = housingType
    }

    fun getHasBalcony(): Boolean = hasBalcony
    fun getHousing(): String = housing
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
                println("${index + 1}. Номер: ${room.roomNumber}, Цена за ночь: ${room.calculatePrice()}, Статус: ${if (room.isAvailable()) "Свободен" else "Занят"}")
            }
        }
    }

    fun findRoomByNumber(roomNumber: String): Room? {
        return rooms.find { it.roomNumber == roomNumber && it.isAvailable() }
    }

    fun findAvailableRooms(): List<Room> {
        return rooms.filter { it.isAvailable() }
    }

    fun findRoomsByCriteria(
        isPremium: Boolean? = null,
        maxPrice: Double? = null,
        hasBalcony: Boolean? = null
    ): List<Room> {
        return rooms.filter { room ->
            var matches = true

            if (isPremium != null) {
                matches = matches && (room is PremiumRoom) == isPremium
            }

            if (maxPrice != null) {
                matches = matches && room.calculatePrice() <= maxPrice
            }

            if (hasBalcony != null) {
                when (room) {
                    is PremiumRoom -> matches = matches && room.getHasBalcony() == hasBalcony
                    is SampleRoom -> matches = matches && room.getHasBalcony() == hasBalcony
                }
            }

            matches && room.isAvailable()
        }
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
        if (room.isAvailable()) {
            room.book()
            bookedRooms.add(room)
            println("Комната ${room.roomNumber} успешно забронирована для $name.")
        } else {
            println("Комната ${room.roomNumber} уже занята.")
        }
    }
}

fun findRoom(hotel: Hotel) {
    println("\n--- Подбор номера ---")

    println("Вы хотите премиум номер или обычный?")
    print("Введите 'премиум' или 'обычный' (или нажмите Enter для поиска всех): ")
    val typeInput = readln().lowercase()
    val isPremium = when {
        typeInput.contains("премиум") -> true
        typeInput.contains("обычный") -> false
        else -> null
    }

    println("Хотите ли вы номер с балконом?")
    print("Введите 'да', 'нет' (или нажмите Enter для любого): ")
    val balconyInput = readln().lowercase()
    val hasBalcony = when {
        balconyInput.contains("да") -> true
        balconyInput.contains("нет") -> false
        else -> null
    }

    println("Укажите максимальную цену за ночь (или нажмите Enter для любой цены): ")
    val priceInput = readln()
    val maxPrice = if (priceInput.isNotEmpty()) priceInput.toDoubleOrNull() else null

    val suitableRooms = hotel.findRoomsByCriteria(isPremium, maxPrice, hasBalcony)

    if (suitableRooms.isEmpty()) {
        println("\nК сожалению, нет подходящих номеров по вашим критериям.")
        println("Возможно, стоит изменить параметры поиска.")
    } else {
        println("\nНайдено ${suitableRooms.size} подходящих номеров:")
        suitableRooms.forEachIndexed { index, room ->
            println("\n${index + 1}.")
            room.info()
        }

        println("\nХотите забронировать один из этих номеров? (да/нет)")
        if (readln().equals("да", ignoreCase = true)) {
            println("Введите номер комнаты для бронирования:")
            val roomNumber = readln()
            val selectedRoom = suitableRooms.find { it.roomNumber == roomNumber }

            if (selectedRoom != null) {
                println("Введите ваше имя:")
                val guestName = readln()
                val guest = Guest(guestName)
                guest.bookRoom(selectedRoom)
            } else {
                println("Номер $roomNumber не найден среди подходящих вариантов.")
            }
        }
    }
}

fun menu() {
    val hotel = Hotel("Grand Plaza")

    hotel.addRoom(SampleRoom("101", 3000.0, hasBalcony = true, housing = "Апарт"))
    hotel.addRoom(SampleRoom("102", 2500.0, hasBalcony = false, housing = "Апарт"))
    hotel.addRoom(SampleRoom("103", 3500.0, hasBalcony = true, housing = "Спа"))
    hotel.addRoom(SampleRoom("104", 3000.0, hasBalcony = false, housing = "Спа"))
    hotel.addRoom(SampleRoom("105", 4500.0, hasBalcony = true, housing = "Конгресс"))
    hotel.addRoom(PremiumRoom("201", 5500.0, hasBalcony = true))
    hotel.addRoom(PremiumRoom("202", 5000.0, hasBalcony = false))
    hotel.addRoom(PremiumRoom("203", 5500.0, hasBalcony = true))

    while (true) {
        println("\n--- Меню ---")
        println("1. Просмотр доступных номеров")
        println("2. Забронировать номер")
        println("3. Выйти")
        println("4. Подбор номера")
        print("Выберите действие: ")

        when (readln()) {
            "1" -> hotel.showRooms()
            "2" -> {
                println("Введите номер комнаты для бронирования:")
                val roomNumber = readln()
                val room = hotel.findRoomByNumber(roomNumber)
                if (room != null) {
                    println("Введите ваше имя:")
                    val guestName = readln()
                    val guest = Guest(guestName)
                    guest.bookRoom(room)
                } else {
                    println("Номер не найден или уже занят.")
                }
            }
            "3" -> {
                println("Выход из программы.")
                break
            }
            "4" -> findRoom(hotel)
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
main()