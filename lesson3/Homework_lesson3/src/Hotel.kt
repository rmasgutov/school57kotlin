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

    fun printListRooms() {
        for (room in rooms) {
            if (!room.isOccupied) {
                println("${room.housing} ${room.number} ${room.capacity} ${if (room.hasBalcony) "Есть" else "Нет"}")
            }
        }
    }
}