package ru.tbank.education.school.lesson3.transport_system

class Map {
    private val routes = mutableListOf<Route>()
    fun addRoute(route: Route) {
        routes.add(route)
        println("Route '${route.name}' added to map.")
    }
    fun removeRoute(route: Route) {
        if (routes.remove(route)) {
            println("Route '${route.name}' removed from map.")
        } else {
            println("Route '${route.name}' not found on map.")
        }
    }
    fun ShowRoutes() {
        println("Routes:")
        for (route in routes) {
            println("${route.name}, ${route.cost}$")
        }
    }
}