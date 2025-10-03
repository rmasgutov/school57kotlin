
fun menu() {
    val hotel = Hotel("Grand Plaza")
    val Gym = GymService(2500.0)
    val Spa = SpaService(2750.0)

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
        println("5. Услуги отеля")
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
            "5" -> {
                println("Выберете услугу:")
                println("1. СПА")
                println("2. Спортивный зал")
                when (readln()) {
                    "1" -> { Spa.handleSpaService(Spa)
                    }
                    "2" -> {
                        Gym.handleGymService(Gym)
                    }

                    else -> println("Некорректный ввод. Попробуйте снова.")
                }
            }
        }
    }
}