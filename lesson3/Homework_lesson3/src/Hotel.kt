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
