class GymService(private val initialPrice: Double = 2000.0) : BaseHotelService() {
    override val serviceName = "Спортивный зал"
    override val basePrice = initialPrice

    private data class Subscription(
        val type: String,
        val price: Double,
        val visits: Int,
        val validityDays: Int = 30
    )

    private data class GuestSubscription(
        val subscription: Subscription,
        var remainingVisits: Int,
        val purchaseDate: String,
        val expiryDate: String
    )

    private val availableSubscriptions = listOf(
        Subscription("Разовый", 500.0, 1, 1),
        Subscription("На 5 посещений", 2000.0, 5, 30),
        Subscription("На 10 посещений", 3500.0, 10, 60),
        Subscription("Месячный", 5000.0, Int.MAX_VALUE, 30)
    )

    private val guestSubscriptions = mutableMapOf<String, GuestSubscription>()

    override fun sheduleInformation(): String {
        return """
            Режим работы:
            Пн-Пт: 7:00-23:00
            Сб-Вс: 9:00-21:00
            
            Зоны:
            - Кардио
            - Силовые тренировки  
            - Групповые занятия
            - Бассейн
        """.trimIndent()
    }

    override fun getPrice(): String {
        return "Абонементы:\n" + availableSubscriptions.joinToString("\n") {
            "- ${it.type}: ${it.price} RUB (${it.visits} посещений, ${it.validityDays} дней)"
        }
    }

    override fun guestState(guestName: String): MutableList<String> {
        val subscription = guestSubscriptions[guestName]
        return if (subscription != null) {
            mutableListOf(
                "Абонемент: ${subscription.subscription.type}",
                "Осталось посещений: ${subscription.remainingVisits}",
                "Действует до: ${subscription.expiryDate}"
            )
        } else {
            mutableListOf("Абонемент не приобретен")
        }
    }

    fun buySubscription(guestName: String, subscriptionIndex: Int): Boolean {
        // 1. Проверка корректности индекса
        if (subscriptionIndex !in availableSubscriptions.indices) return false

        val subscription = availableSubscriptions[subscriptionIndex]

        val expiryDate = java.time.LocalDate.now().plusDays(subscription.validityDays.toLong())

        guestSubscriptions[guestName] = GuestSubscription(
            subscription = subscription,
            remainingVisits = subscription.visits,
            purchaseDate = java.time.LocalDate.now().toString(),
            expiryDate = expiryDate.toString()
        )

        return true
    }

    fun getRemainingVisits(guestName: String): Int {
        val subscription = guestSubscriptions[guestName]
        return subscription?.remainingVisits ?: 0
    }

    // Если операция прошла успешно - true
    fun useVisit(guestName: String): Boolean {

        val subscription = guestSubscriptions[guestName] ?: return false

        if (subscription.remainingVisits <= 0) return false

        val expiryDate = java.time.LocalDate.parse(subscription?.expiryDate)

        if (!java.time.LocalDate.now().isAfter(expiryDate)) return false

        subscription.remainingVisits--
        return true
    }

    fun handleGymService(gymService: GymService) {
        println("1. Посмотреть цены на абонементы")
        println("2. Купить абонемент")
        println("3. Использовать посещение")
        println("4. Информация о спортзале")
        println("5. Проверить мой абонемент")
        when (readln()) {
            "1" -> println(gymService.getPrice())
            "2" -> {
                println("Выберите абонемент:")
                println(gymService.getPrice())
                println("Введите номер абонемента (1-4):")
                val subIndex = readln().toIntOrNull()?.minus(1)
                println("Введите ваше имя:")
                val guestName = readln()

                if (subIndex != null && gymService.buySubscription(guestName, subIndex)) {
                    println("Абонемент успешно приобретен!")
                } else {
                    println("Ошибка при покупке абонемента")
                }
            }
            "3" -> {
                println("Введите ваше имя:")
                val guestName = readln()
                if (gymService.useVisit(guestName)) {
                    println("Посещение использовано. Хорошей тренировки!")
                    println("Осталось посещений: ${gymService.getRemainingVisits(guestName)}")
                } else {
                    println("Не удалось использовать посещение")
                }
            }
            "4" -> println(gymService.sheduleInformation())
            "5" -> {
                println("Введите ваше имя:")
                val guestName = readln()
                val subscriptionInfo = gymService.guestState(guestName)
                subscriptionInfo.forEach { println(it) }
            }
            else -> println("Некорректный ввод")
        }
    }
}

