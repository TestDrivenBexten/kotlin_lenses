
fun decommissionShip(fleet: Fleet, squadName: String, shipName: String): Fleet {
    val newSquadList = fleet.registry.map { squadron ->
        if(squadron.squadName.equals(squadName)){
            val newShipList = squadron.registry.filter { !shipName.equals(it.shipName) }
            Squadron(squadron.squadName, newShipList)
        } else {
            squadron
        }
    }
    return Fleet(fleet.fleetName, newSquadList)
}