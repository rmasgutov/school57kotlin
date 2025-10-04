package ru.tbank.education.school.lesson3.seminar

abstract class Person(val name: String, val age: Int) {
    abstract fun getInfo(): String
}

class Customer(name: String, age: Int, val customerId: Int) : Person(name, age) {
    override fun getInfo(): String {
        return "Покупатель: $name, возраст: $age, ID: $customerId"
    }
}

open class Employee : Person {
    private var position: String

    constructor(name: String, age: Int, position: String) : super(name, age) {
        this.position = position
    }

    constructor(name: String, position: String) : this(name, 18, position)

    open fun work() {
        println("$name работает как $position")
    }

    override fun getInfo(): String {
        return "Сотрудник: $name, возраст: $age, должность: $position"
    }
}

data class Movie(val title: String, val duration: Int) {
    val isLongMovie: Boolean
        get() = duration > 120
}

sealed class ScreeningStatus {
    object Ongoing : ScreeningStatus()
    object Cancelled : ScreeningStatus()
    object Finished : ScreeningStatus()
}

class Ticket(val customer: Customer, val movie: Movie) {
    fun printTicket() {
        println("🎟️ Билет выдан для ${customer.name} на фильм '${movie.title}' (${movie.duration} мин)")
    }
}

class Cinema(private val name: String) {
    private val movies = mutableListOf<Movie>()
    private val employees = mutableListOf<Employee>()

    fun addMovie(movie: Movie) {
        movies.add(movie)
    }

    fun addEmployee(employee: Employee) {
        employees.add(employee)
    }

    fun listMovies() {
        println("📽️ Фильмы в кинотеатре '$name':")
        for (movie in movies) {
            println("- ${movie.title} (${movie.duration} мин)")
        }
    }

    fun listEmployees() {
        println("👥 Сотрудники кинотеатра '$name':")
        for (e in employees) {
            println("- ${e.getInfo()}")
        }
    }
}
fun main() {
    val cinema = Cinema("Синема Парк")

    val movie1 = Movie("Интерстеллар", 169)
    val movie2 = Movie("Король Лев", 88)
    cinema.addMovie(movie1)
    cinema.addMovie(movie2)

    val employee1 = Employee("Анна", 25, "Менеджер")
    val employee2 = Employee("Дмитрий", "Кассир") // использован доп. конструктор
    cinema.addEmployee(employee1)
    cinema.addEmployee(employee2)

    val customer = Customer("Иван", 30, 1002)

    val ticket = Ticket(customer, movie1)

    // Выводим информацию
    println("\n--- Информация ---")
    println(customer.getInfo())
    ticket.printTicket()
    println("Длинный фильм? ${movie1.isLongMovie}")

    println("\n--- Список фильмов ---")
    cinema.listMovies()

    println("\n--- Список сотрудников ---")
    cinema.listEmployees()

    println("\n--- Работа сотрудников ---")
    employee1.work()
    employee2.work()

    println("\n--- Статус показа ---")
    val status: ScreeningStatus = ScreeningStatus.Ongoing
    when (status) {
        is ScreeningStatus.Ongoing -> println("Сеанс идет сейчас")
        is ScreeningStatus.Cancelled -> println("Сеанс отменен")
        is ScreeningStatus.Finished -> println("Сеанс завершен")
    }
}