package ru.tbank.education.school.lesson3.transport_system

interface Info {
    fun info()
}

interface Passengers {
    fun add_passangers(new: Int = 0)
    fun delete_passangers(deleted: Int = 0)
}

interface Routeable {
    var route: Routes?
}


fun interface PassengersNotifier {
    fun change(newCount: Int)
}


fun interface RouteNotifier {
    fun routeChanged(route: Routes?)
}