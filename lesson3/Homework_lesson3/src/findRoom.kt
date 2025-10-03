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