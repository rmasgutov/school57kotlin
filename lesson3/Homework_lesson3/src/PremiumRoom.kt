class PremiumRoom(
    roomNumber: String,
    override var price: Double,
    private var hasBalcony: Boolean = false,
    private var housing: String = "Люкс",
    private var capacity: Double
) : Room(roomNumber, price) {

    override fun calculatePrice(): Double {
        return when (hasBalcony) {
            true -> 5500.0
            false -> 5000.0
            else -> 5000.0
        }
    }

    override fun info() {

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