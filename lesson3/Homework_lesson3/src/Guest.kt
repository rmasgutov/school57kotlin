class Guest(val name: String, val bookedRooms: MutableList<Room> = mutableListOf()) {
    fun bookRoom(room: Room) {
        if (room.isAvailable()) {
            room.book(name)
            bookedRooms.add(room)
            println("Комната ${room.roomNumber} успешно забронирована для $name.")
        } else {
            println("Комната ${room.roomNumber} уже занята.")
        }
    }
}