import arrow.optics.Lens
import arrow.optics.Traversal

val starshipName: Lens<Starship, String> = Lens(
    get = { it.shipName },
    set = { starship, shipName -> starship.copy(shipName = shipName)}
)

val starships: Lens<Squadron, List<Starship>> = Lens(
    get = { it.registry },
    set = { squadron, registry -> squadron.copy(registry = registry)}
)

val everyStarship = Traversal.list<Starship>()

fun arrowRenameStarship(starship: Starship, shipName: String): Starship {
    val shipLambda = { _: String -> shipName}
    return starshipName.modify(starship, shipLambda)
}

fun arrowRemoveShipBySerialNumber(squadron: Squadron, serialNumber: String): Squadron {
    val removeShip = { shipList: List<Starship> -> shipList.filter { !it.serialNumber.equals(serialNumber) } }
    return starships.modify(squadron, removeShip)
}

fun renameShipBySerialNumber(starship: Starship, serialNumber: String, shipName: String): Starship{
    return if (starship.serialNumber.equals(serialNumber)){
        starship.copy(shipName = shipName)
    } else {
        starship
    }
}

fun arrowRenameShipInSquadron(squadron: Squadron, serialNumber: String, shipName: String) : Squadron {
    val squadronMap = starships compose everyStarship
    val squadUpdate = { starship: Starship -> renameShipBySerialNumber(starship, serialNumber, shipName)}
    return squadronMap.modify(squadron, squadUpdate)
}

fun arrowDecommissionShip(fleet: Fleet, squadName: String, shipName: String): Fleet {
    return fleet
}