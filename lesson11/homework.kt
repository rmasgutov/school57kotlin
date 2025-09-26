package ru.tbank.education.school.lesson3

sealed class OrderStatus {
    object Pending : OrderStatus()
    object Preparing : OrderStatus()
    object Ready : OrderStatus()
    object Served : OrderStatus()
    data class Cancelled(val reason: String) : OrderStatus()
}

abstract class MenuItem(
    open val id: Int,
    open val name: String,
    open val price: Double
) {
    abstract val cookingTime: Int
    open fun res(): String {
        return "$name - $price руб."
    }
}

data class Ingredient(
    val name: String,
    val quantity: Double,
    val unit: String,
    val Vegetarian: Boolean = false
) {
    val printname: String
        get() = if (Vegetarian) "$name" else name
}

class MainCourse(
    override val id: Int,
    override val name: String,
    override val price: Double,
    val weight: Int,
    private val _ingredients: MutableList<Ingredient> = mutableListOf()
) : MenuItem(id, name, price) {
    
 
    constructor(id: Int, name: String, price: Double) : 
            this(id, name, price, 300)
    
    override val cookingTime: Int = 25
    
    val category: String = "Основное блюдо"
    
    protected val ingredients: List<Ingredient>
        get() = _ingredients.toList()
    
    fun addIngredient(ingredient: Ingredient) {
        _ingredients.add(ingredient)
    }
  
    override fun res(): String {
        return "${super.res()} | ${weight}г"
    }
} 

class Order(
    val id: Int,
    val tableNumber: Int,
    private val _items: MutableList<MenuItem> = mutableListOf()
) {
    val items: List<MenuItem>
        get() = _items.toList()
    
    private var status: OrderStatus = OrderStatus.Pending
    
    private val totalPrice: Double
        get() = _items.sumOf { it.price }
    
    fun addItem(item: MenuItem) {
        _items.add(item)
        println("Добавлено: ${item.name}")
    }
    
    fun updateStatus(newStatus: OrderStatus) {
        status = newStatus
        println("Заказ $id: статус изменен на ${status.javaClass.simpleName}")
    }

    fun processOrder() {
        when (status) {
            is OrderStatus.Pending -> {
                updateStatus(OrderStatus.Preparing)
                println("Начали готовить заказ $id")
            }
            is OrderStatus.Preparing -> {
                updateStatus(OrderStatus.Ready)
                println("Заказ $id готов к подаче")
            }
            is OrderStatus.Ready -> {
                updateStatus(OrderStatus.Served)
                println("Заказ $id подан на стол $tableNumber")
            }
            else -> println("Невозможно обработать заказ в текущем статусе")
        }
    }
} 

fun main() {
    val steak = MainCourse(
        id = 1,
        name = "Стейк",
        price = 1200.0,
        weight = 350
    )
    
    steak.addIngredient(Ingredient("Говядина", 300.0, "г"))
    steak.addIngredient(Ingredient("Соль", 5.0, "г"))
    
    val order = Order(id = 101, tableNumber = 5)
    
    order.addItem(steak)
    order.processOrder()
    
    println(steak.res())
}
