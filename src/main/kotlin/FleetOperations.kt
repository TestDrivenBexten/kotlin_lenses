
fun decommissionShip(fleet: Fleet, squadName: String, shipName: String): Fleet {
    val newSquadList = fleet.registry.map { squadron ->
        val newShipList = squadron.registry.filter { !shipName.equals(it.shipName) }
        Squadron(squadron.squadName, newShipList)
    }
    return Fleet(fleet.fleetName, newSquadList)
}