package ru.tbank.education.school.lesson3.seminar

import java.time.LocalDateTime




class Posts {

    val THERAPIST = "Иванов Иван"
    val PEDIATRICIAN = "Олег Олегович"
    val SURGEON = "Александр Александрович"
    val NEUROLOGIST = "Константин Константинович"
    val RHEUMATOLOGIST = "Матвей Матвеевич"
    val OPTOMETRIST = "Олег Окулистов"
}

class Hospital: People() {
    fun info() {
        println("Доступные методы:\nadd - добавить пользователя\nremove find logIn logOut")
    }
}
data class Appointment(
    val time: LocalDateTime,
    val doc_name: String,
    val doc_post: String?,
    val pat_name: String
)

data class Person(
    val name: String,
    val work_here: Boolean,
    val password: String,
    var appointments: List<Appointment>
)
open class People {
    val posts: Map<String, String> = mapOf("Иванов Иван" to "THERAPIST", "Олег Олегович" to "PEDIATRICIAN",
        "Александр Александрович" to "SURGEON",
        "Константин Константинович" to "NEUROLOGIST",
        "Матвей Матвеевич" to "RHEUMATOLOGIST",
        "Олег Окулистов" to "OPTOMETRIST")
    protected val all = mutableListOf(
        Person(
            name = "Иван Иванов",
            work_here = false,
            password = "qwerty54321",
            appointments = listOf(
                Appointment(
                    time = LocalDateTime.parse("2025-03-15T10:30:00"),
                    doc_name = "Олег Окулистов",
                    doc_post = posts["Олег Окулистов"],
                    pat_name = "Иван Младший"
                )
            )
        ),
        Person(
            name = "Олег Окулистов",
            work_here = true,
            password = "12345qwerty",
            appointments = listOf(
                Appointment(
                    time = LocalDateTime.parse("2025-03-15T10:30:00"),
                    doc_name = "Олег Окулистов",
                    doc_post = posts["Олег Окулистов"],
                    pat_name = "Иван Младший"
                )
            )
        )
    )
    protected var log_in: List<String> = listOf()
    fun add(name: String, password: String): Boolean {
        if (name == "" || password == "") {
            println("No name or password is specified")
            return false
        }
        for (i in all) {
            if (name == i.name) {
                println("A person with this name is already in the database.\nTry to specify a different name or clarify it.\nIf it is you, log in")
                return false
            }
        }
        all.add(Person(
            name = name,
            work_here = false,
            password = password,
            appointments = listOf()
        ))
        println("The user has been successfully added!")
        return true
    }
    fun remove(name: String, password: String): Boolean {
        if (name == "" || password == "") {
            println("No name or password is specified")
            return false
        }
        for (i in all) {
            if ((name == i.name) && (password == i.password)) {
                println("The user has been successfully deleted.")
                return true
            }
        }
        return false
    }
    fun find(name: String, password: String): Person {
        if (name == "" || password == "") {
            println("No name or password is specified")
            return error("No name or password is specified")
        }
        for (i in all) {
            if ((name == i.name) && (password == i.password)) {
                println(i)
                return i
            }
        }
        print("Non-existent name or password")
        return error("Non-existent name or password")
    }
    fun logIn(name: String, password: String): Boolean {
        if (name == "" || password == "") {
            println("No name or password is specified")
            return false
        }
        for (i in all) {
            if ((name == i.name) && (password == i.password)) {
                log_in = listOf(i.name, i.password)
                println("You have logged in as ${log_in[0]}")
                return true
            }
        }
        print("Non-existent name or password")
        return false
    }
    fun logOut(password: String): Boolean {
        if (log_in[0] == "") {
            println("You must log in to log out.")
            return false
        }
        if (password == "") {
            println("The password is not specified")
            return false
        }
        for (i in all) {
            if ((log_in[0] == i.name) && (password == i.password)) {
                log_in = listOf()
                println("You have logged in as $log_in")
                return true
            }
        }
        print("Non-existent name or password\n")
        return false
    }
    fun addAppointment(doc_post: String, com_time: LocalDateTime = LocalDateTime.now()): Boolean {
        var optimal_time = com_time
        for (i in all) {
            for (j in i.appointments) {
                if (j.doc_post == doc_post) {
                    optimal_time = if (optimal_time > j.time.plusMinutes(15) || optimal_time < j.time.minusMinutes(15)) optimal_time else j.time.plusMinutes(15)
                }
            }
        }
        try {
            var doc_name = ""
            for (i in posts) {if (i.value == doc_post) doc_name = i.key}
            for (i in all) {
                if (i.name == log_in[0]) {
                    i.appointments += Appointment(
                        time = optimal_time,
                        doc_name = doc_name,
                        doc_post = posts[doc_name],
                        pat_name = log_in[0]
                    )
                    println("You are scheduled for $optimal_time with Dr. $doc_name")
                    return true
                }
            }
        }
        catch (e: Exception) {
            println("Register first.")
        }
        return false

    }

}







fun main() {
    val hospital = Hospital()
    val name = "Somebody"
    val password = "1234554321"
    hospital.add(name, password)
    hospital.logIn(name, password)
    hospital.addAppointment("THERAPIST")
    hospital.logIn("Иван Иванов", "qwerty54321")
    hospital.addAppointment("THERAPIST", )
    hospital.find("Олег Окулистов", "12345qwerty")
}