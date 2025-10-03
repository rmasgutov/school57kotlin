class SpaService(private val initialPrice: Double = 2500.0): BaseHotelService() {
    override val serviceName: String = "СПА"
    override val basePrice: Double = initialPrice

    private data class TimeSlot(val hour: Int, var guestName: String = "")

    private val schedule: Map<String, List<TimeSlot>> by lazy {
        mapOf(
            "Понедельник" to (9..20).map { TimeSlot(it) },
            "Вторник" to (8..20).map { TimeSlot(it) },
            "Среда" to (9..21).map { TimeSlot(it) },
            "Четверг" to (8..19).map { TimeSlot(it) },
            "Пятница" to (9..21).map { TimeSlot(it) },
            "Суббота" to (10..19).map { TimeSlot(it) },
            "Воскресенье" to (10..17).map { TimeSlot(it) }
        )
    }

    override fun sheduleInformation(): String {
        return schedule.entries.joinToString("\n") { (day, slots) ->
            val busySlots = slots.filter { it.guestName.isNotEmpty() }
            val status = if (busySlots.isEmpty()) "Все время свободно"
            else busySlots.joinToString { "${it.hour}:00 - ${it.guestName}" }
            "$day: $status"
        }
    }

    override fun guestState(guestName: String): MutableList<String> {
        return schedule.flatMap { (day, slots) ->
            slots.filter { it.guestName == guestName }
                .map { "$day ${it.hour}:00" }
        }.toMutableList()
    }


    fun checkAvailability(weekDay: String, time: Int, guestName: String): String {
        val daySlots = schedule[weekDay]

        if (daySlots == null) {
            return "День не найден"
        }

        val slot = daySlots.find { it.hour == time }

        if (slot == null) {
            return "Время не найдено"
        }

        return when {
            slot.guestName.isEmpty() -> "Бронь свободна"
            slot.guestName == guestName -> "Бронь уже вами занята"
            else -> "Место занято"
        }
    }

    fun bookTime(weekDay: String, time: Int, guestName: String): Boolean {
        val daySlots = schedule[weekDay] ?: return false
        val slot = daySlots.find { it.hour == time } ?: return false

        if (slot.guestName.isEmpty()) {
            slot.guestName = guestName
            return true
        }

        return false
    }
    fun cancelBooking(weekDay: String, time: Int, guestName: String): Boolean {
        val daySlots = schedule[weekDay] ?: return false
        val slot = daySlots.find { it.hour == time } ?: return false

        if (slot.guestName == guestName) {
            slot.guestName = ""
            return true
        }

        return false
    }

    fun handleSpaService(spaService: SpaService) {
        println("1. Просмотр свободных записей")
        println("2. Забронировать время")
        println("3. Отменить бронь")
        println("4. Посмотреть цены")
        println("5. Посмотреть запись по имени")

        when (readln()) {
            "1" -> println(spaService.sheduleInformation())
            "2" -> {
                println("Введите ваше имя:")
                val guestName = readln()
                println("Выберите день недели:")
                val weekDay = readln()
                println("Выберите время (например: 16):")
                val time = readln().toIntOrNull()

                if (time != null) {
                    val availability = spaService.checkAvailability(weekDay, time, guestName)

                    if (availability == "Бронь свободна") {
                        if (spaService.bookTime(weekDay, time, guestName)) {
                            println("Запись забронирована на $guestName")
                        } else {
                            println("Ошибка бронирования")
                        }
                    } else {
                        println(availability)
                    }
                } else {
                    println("Некорректное время")
                }
            }
            "3" -> {
                println("Введите ваше имя:")
                val guestName = readln()
                println("Выберите день недели:")
                val weekDay = readln()
                println("Выберите время для отмены:")
                val time = readln().toIntOrNull()

                if (time != null && spaService.cancelBooking(weekDay, time, guestName)) {
                    println("Бронь отменена")
                } else {
                    println("Не удалось отменить бронь")
                }
            }
            "4" -> println(spaService.getPrice())
            "5" -> {
                println("Введите ваше имя:")
                val guestName = readln()
                val bookings = spaService.guestState(guestName)
                if (bookings.isEmpty()) {
                    println("Записей не найдено")
                } else {
                    println("Ваши записи:")
                    bookings.forEach { println(it) }
                }
            }
            else -> println("Некорректный ввод")
        }
    }
}






//    override fun getPrice(): String {
//        return "$price RUB"
//    }
//
//    private var price = 0.0
//
//    constructor(price: Double) : this() {
//        this.price = price
//    }
//
//    override fun sheduleInformation(): String {
//        val result = StringBuilder()
//        for (day in shedule.keys) {
//            result.append("$day: ")
//            val busyTimes = mutableListOf<String>()
//            for (timeSlot in shedule[day]!!) {
//                if (timeSlot[1] != "") {
//                    busyTimes.add("${timeSlot[0]}:00 - ${timeSlot[1]}")
//                }
//            }
//            if (busyTimes.isEmpty()) {
//                result.append("Все время свободно")
//            } else {
//                result.append(busyTimes.joinToString(", "))
//            }
//            result.append("\n")
//        }
//        return result.toString()
//    }
//
//    override fun guestState(guestName: String): MutableList<String> {
//        val guestVisits = mutableListOf<String>()
//
//        for ((day, timeSlots) in shedule) {
//            for (timeSlot in timeSlots) {
//                if (timeSlot[1] == guestName) {
//                    guestVisits.add("$day ${timeSlot[0]}:00")
//                }
//            }
//        }
//
//        return guestVisits
//    }
//
//    fun isAvailable(weekDay: String, time: Int, name: String): String {
//        val timeSlots = shedule[weekDay] ?: return "День не найден"
//
//        for (timeSlot in timeSlots) {
//            if (timeSlot[0] == time) {
//                return when {
//                    timeSlot[1] == "" -> "Бронь свободна"
//                    timeSlot[1] == name -> "Бронь уже вами занята"
//                    else -> "Место занято"
//                }
//            }
//        }
//        return "Время не найдено"
//    }
//
//    fun bookTime(weekDay: String, time: Int, guestName: String): Boolean {
//        val timeSlots = shedule[weekDay] ?: return false
//
//        for (timeSlot in timeSlots) {
//            if (timeSlot[0] == time && timeSlot[1] == "") {
//                timeSlot[1] = guestName
//                return true
//            }
//        }
//        return false
//    }
//
//    fun cancelBooking(weekDay: String, time: Int, name: String): Boolean {
//        val timeSlots = shedule[weekDay] ?: return false
//        for (timeSlot in timeSlots) {
//            if (timeSlot[0] == time && timeSlot[1] == name) {
//                timeSlot[1] =  ""
//                return true
//
//            }
//        }
//        return false
//    }
//}
