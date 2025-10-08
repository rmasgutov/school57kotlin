package ru.tbank.education.school.lesson3.transport_system

fun main() {
    val map = Map()
    map.setRouteNotifier { changedRoute ->
        if (changedRoute != null) println("New route added: ${changedRoute.name}")
        else println("A route was removed")
    }

    val route = Routes("AA", 10)
    map.addRoute(route)
    map.ShowRoutes()

    val bus = Bus(123, 30, 100, route)
    bus.setPassengersNotifier { newCount -> println("Bus now has $newCount passengers") }

    bus.add_passangers(100)
    bus.Passengers()
    bus.info()
    bus.delete_passangers(100)
    bus.Passengers()
    bus.add_passangers(5)
    bus.Passengers()

    val tube_route = Routes("Red Line", 350)
    map.addRoute(tube_route)
    val tube = Tube(123, 200, 350, tube_route)
    tube.setPassengersNotifier { newCount -> println("Tube now has $newCount passengers") }

    tube.info()
    tube.add_passangers(100)
    tube.Passengers()
    tube.add_passangers(350)
    tube.Passengers()
    tube.delete_passangers(50)
    tube.Passengers()
    tube.delete_passangers(151)
    tube.Passengers()
    map.ShowRoutes()
    map.removeRoute(tube_route)
    map.ShowRoutes()
    map.removeRoute(route)
}
