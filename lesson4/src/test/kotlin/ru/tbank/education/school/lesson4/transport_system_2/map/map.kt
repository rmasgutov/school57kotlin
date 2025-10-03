package ru.tbank.education.school.lesson3.transport_system

class Map {
    private val routes = mutableListOf<Routes>()
    private var routeNotifier: RouteNotifier? = null

    fun setRouteNotifier(notifier: RouteNotifier?) {
        routeNotifier = notifier
    }

    fun addRoute(route: Routes) {
        routes.add(route)
        println("Route '${route.name}' added to map.")
        routeNotifier?.routeChanged(route)
    }

    fun removeRoute(route: Routes) {
        if (routes.remove(route)) {
            println("Route '${route.name}' removed from map.")
            routeNotifier?.routeChanged(null)
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
