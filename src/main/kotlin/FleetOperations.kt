
fun decommissionShip(fleet: Fleet, squadName: String, shipName: String): Fleet {
    val newSquadList = fleet.registry.map { squadron ->
        if (squadron.squadName == squadName) {
            val newShipList = squadron.registry.filter { shipName != it.shipName }
            Squadron(squadron.squadName, newShipList)
        } else {
            squadron
        }
    }
    return Fleet(
        fleet.fleetName,
        Coordinates(100,100),
        Admiral("Janeway", Steward("Tuvok")),
        newSquadList
    )
}

fun listShipsInFleet(fleet: Fleet): List<String> {
    return fleet.registry.flatMap { it.registry.map { it.shipName } }
}

fun renameFleetSteward(fleet: Fleet, newName: String): Fleet {
    return fleet.copy(
        admiral = fleet.admiral?.copy(
            steward = fleet.admiral.steward?.copy(
                stewardName = newName
            )
        )
    )
}
