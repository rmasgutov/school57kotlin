class Aircraft(val model: String, val capacity: Int) {
    constructor(capacity: Int) : this("Хз что это, но летает", capacity)
}

data class Passenger(val passport: String, val personName: String):
        Person(personName) {
    override fun getRole(): String = "Пассажир"
}
abstract class Person(val name: String) {
    open fun getRole(): String = "Человекоподобное животное"
}

class Pilot(name: String, val license: String) : Person(name) {
    override fun getRole(): String {
        return super.getRole() + " -> Пилот"
    }
    fun fly(aircraft: Aircraft) {
        println("Пилот $name с лицензией $license управляет самолётом ${aircraft.model}")
    }
}

class Flight(val flightNumber: String, val aircraft: Aircraft){
    private val passengers = mutableListOf<Passenger>()

    val availableSeats: Int get() = aircraft.capacity - passengers.size

    fun addPassenger(passenger: Passenger): Boolean {
        return if (passengers.size < aircraft.capacity) {
            passengers.add(passenger)
            println("Пассажир ${passenger.name} добавлен на рейс $flightNumber")
            true
        } else {
            println("Рейс $flightNumber переполнен!")
            false
        }
    }
    fun listPassengers(){
        println("Пассажиры рейса $flightNumber:")
        for (p in passengers) {
            println("${p.name} (${p.passport})")
        }
    }
    var status: FlightStatus = FlightStatus.Boarding
}

sealed class FlightStatus {
    object Boarding : FlightStatus() {
        override fun toString() = "Посадка"
    }
    object Departed : FlightStatus() {
        override fun toString() = "Вылетел"
        }
    object Cancelled : FlightStatus() {
        override fun toString() = "Отменён"
        }
}

fun main(){
    val plane = Aircraft("Cessna172", 2)
    val p1 = Passenger("52052052", "Шрек")
    val p2 = Passenger("42042042", "фиона")
    val p3 = Passenger("10753464", "осёл")
    val pilot = Pilot("Кот в сапогах", "Fake20202")
    val flight = Flight("Болото123", plane)
    flight.addPassenger(p1)
    flight.addPassenger(p2)
    flight.addPassenger(p3)
    println("Самолёт: ${plane.model}, мест: ${plane.capacity}")
    flight.listPassengers()
    println("Свободных мест: ${flight.availableSeats}")
    val people: List<Person> = listOf(p1, p2, pilot)
    for (person in people) {
        println("${person.name} - ${person.getRole()}")
    }
    pilot.fly(plane)
    println("Статус рейса: ${flight.status}")
    flight.status = FlightStatus.Departed
    println("Статус рейса изменён: ${flight.status}")
}