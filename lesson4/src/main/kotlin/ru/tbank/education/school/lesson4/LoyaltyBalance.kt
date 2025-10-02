interface LoyaltyBalance {
    fun getCurrentBalance(): Int
}
interface LoyaltyLevel {
    fun getNewLevel(): LevelInfo
}
interface BonusCalculator {
    fun calculateBonus(orderAmount: Double): Int
}
class RestaurantLoyaltySystem : LoyaltyBalance, LoyaltyLevel, BonusCalculator {
    private var balance: Int = 0
    private var totalSpent: Double = 0.0
    override fun getCurrentBalance(): Int = balance
    
    override fun getNewLevel(): LevelInfo {
        return when {
            totalSpent >= 10000 -> LevelInfo("PLATINUM", 1000, 0.15)
            totalSpent >= 5000 -> LevelInfo("GOLD", 500, 0.10)
            totalSpent >= 1000 -> LevelInfo("SILVER", 100, 0.05)
            else -> LevelInfo("BRONZE", 0, 0.0)
        }
    }
    override fun calculateBonus(orderAmount: Double): Int {
        val bonus = (orderAmount * 0.05).toInt()
        balance += bonus
        totalSpent += orderAmount
        return bonus
    }
}
