package ru.tbank.education.school.lesson3


abstract class TourismOffer(
    val id: Int,
    protected open var name: String,
    private val price: Double
) {

    val formattedPrice: String
        get() = "%.2f ₽".format(price)

    abstract fun description(): String

    open fun getDetails(): String = "Предложение #$id: $name, цена: $formattedPrice"
}


data class Country(val name: String, val visaRequired: Boolean)


sealed class TourType {
    object Excursion : TourType()
    object Beach : TourType()
    object Adventure : TourType()
}


open class Tour(
    id: Int,
    name: String,
    price: Double,
    val country: Country,
    val type: TourType,
    private var available: Boolean = true
) : TourismOffer(id, name, price) {


    var isAvailable: Boolean
        get() = available
        set(value) {
            println("Статус доступности тура '$name' изменён на ${if (value) "доступен" else "не доступен"}")
            available = value
        }

    override fun description(): String {
        return "Тур в ${country.name} (${type.javaClass.simpleName}): $name"
    }

    override fun getDetails(): String {
        return "${super.getDetails()}, ${if (country.visaRequired) "требуется виза" else "виза не требуется"}"
    }
}

class ExclusiveTour(
    id: Int,
    name: String,
    price: Double,
    country: Country,
    type: TourType,
    private val vipService: String
) : Tour(id, name, price, country, type) {

    override fun description(): String {
        return "${super.description()} с VIP-услугой: $vipService"
    }
}

class TravelAgency(val name: String) {
    private val tours = mutableListOf<TourismOffer>()

    fun addTour(tour: TourismOffer) {
        tours.add(tour)
    }

    fun showAvailableTours() {
        println("Доступные туры в компании $name:")
        tours.filterIsInstance<Tour>().filter { it.isAvailable }.forEach {
            println(it.getDetails())
        }
    }
}

fun main() {
    val italy = Country("Италия", visaRequired = true)
    val turkey = Country("Турция", visaRequired = false)

    val romeTour = Tour(1, "Римские каникулы", 65000.0, italy, TourType.Excursion)
    val beachTour = Tour(2, "Анталия - все включено", 45000.0, turkey, TourType.Beach)
    val exclusiveTour = ExclusiveTour(3, "Эксклюзивная Венеция", 120000.0, italy, TourType.Excursion, "Персональный гид")

    val agency = TravelAgency("Путешествия мечты")
    agency.addTour(romeTour)
    agency.addTour(beachTour)
    agency.addTour(exclusiveTour)

    println(romeTour.description())
    println("Цена: ${romeTour.formattedPrice}")

    beachTour.isAvailable = false

    agency.showAvailableTours()
}