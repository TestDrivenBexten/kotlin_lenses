
fun decommissionShip(fleet: Fleet, squadName: String, shipName: String): Fleet {
    val newSquadList = fleet.registry.map { squadron ->
        if (squadron.squadName == squadName) {
            val newShipList = squadron.registry.filter { shipName != it.shipName }
            Squadron(squadron.squadName, newShipList)
        } else {
            squadron
        }
    }
    return Fleet(fleet.fleetName, newSquadList)
}

fun listShipsInFleet(fleet: Fleet): List<String> {
    return fleet.registry.flatMap { it.registry.map { it.shipName } }
}
