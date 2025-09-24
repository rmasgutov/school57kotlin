class SimpleRoom(
    roomNumber: String,
    override var price: Double,
    private var hasBalcony: Boolean = false,
    private var housing: String,
    private var capacity: Double
) : Room(roomNumber, price) {

    override fun calculatePrice(): Double {
        return when (housing) {
            "Апарт" -> if (hasBalcony) 2000.0 else 1750.0
            "Спа" -> if (hasBalcony) 3000.0 else 2500.0
            "Люкс" -> if (hasBalcony) 5000.0 else 4700.0
            else -> 0.0 // или какое-то значение по умолчанию
        }
    }

    override fun info() {
        var housingNum: Int? = null
        while (housingNum == null) {
            println("Выберите корпус (введите число 1, 2 или 3):")
            try {
                housingNum = readln().toInt()
            } catch (e: NumberFormatException) {
                println("Ошибка: введено не число! Попробуйте снова.")
            }
        }
        housing = when (housingNum) {
            1 -> "Апарт"
            2 -> "Спа"
            3 -> "Люкс"
            else -> {
                println("Некорректный выбор, устанавливаем по умолчанию 'Апарт'")
                "Апарт"
            }
        }

        println("Хотите ли вы иметь балкон в своем номере? (да/нет)")
        val balcon_answer = readln().lowercase()
        hasBalcony = when (balcon_answer) {
            "да", "yes" -> true
            "нет", "no" -> false
            else -> {
                println("Некорректный ответ, по умолчанию балкон отсутствует.")
                false
            }
        }
        println("Ваша итоговая цена за одну ночь: ${calculatePrice()}")
    }

}