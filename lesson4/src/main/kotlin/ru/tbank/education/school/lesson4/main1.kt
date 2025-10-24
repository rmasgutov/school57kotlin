package ru.tbank.education.school
data class LevelInfo(val levelName: String, val requiredPoints: Int, val discount: Double)
data class MenuItem(val name: String, val price: Double)

fun main() {
    val loyaltySystem = RestaurantLoyaltySystem()
    println("1. СИСТЕМА ЛОЯЛЬНОСТИ:")
    println("Начальный баланс: ${loyaltySystem.getCurrentBalance()}")
    println("Текущий уровень: ${loyaltySystem.getNewLevel().levelName}")

    println("\n2. ОНЛАЙН-ЗАКАЗ:")
    val onlineOrder = OnlineOrderImpl(1, "ул. Ленина, д. 10")
    onlineOrder.addItem(MenuItem("Пицца Пепперони", 550.0))
    println("Итоговая сумма: ${onlineOrder.totalPrice} руб.")

    val bonus = loyaltySystem.calculateBonus(onlineOrder.totalPrice)
    println("Начислено бонусов: $bonus")
    println("Текущий баланс: ${loyaltySystem.getCurrentBalance()}")
    println("Новый уровень: ${loyaltySystem.getNewLevel().levelName}")
 
    println("\n3. КУХОННЫЙ ДИСПЛЕЙ:")
    val kitchenScreen = KitchenScreen()
    val itemNames = onlineOrder.items.map { it.name }
    kitchenScreen.showOrder(onlineOrder.id, itemNames)
    kitchenScreen.updateStatus(onlineOrder.id, "в процессе приготовления")
    kitchenScreen.updateStatus(onlineOrder.id, "готов к выдаче")

    println("\n4. ДОСТАВКА:")
    val deliveryTime = onlineOrder.calculateDeliveryTime(5.0, 40.0)
    println("Время доставки: ${"%.0f".format(deliveryTime)} минут")
}
