import arrow.optics.Lens
import arrow.optics.Traversal

val starshipName: Lens<Starship, String> = Lens(
    get = { it.shipName },
    set = { starship, shipName -> starship.copy(shipName = shipName)}
)

val starhips: Lens<Squadron, List<Starship>> = Lens(
    get = { it.registry },
    set = { squadron, registry -> squadron.copy(registry = registry)}
)

val everyStarship = Traversal.list<Starship>()

fun arrowRenameStarship(starship: Starship, shipName: String): Starship {
    val shipLambda = { _: String -> shipName}
    return starshipName.modify(starship, shipLambda)
}

fun renameShipBySerialNumber(starship: Starship, serialNumber: String, shipName: String): Starship{
    return if (starship.serialNumber.equals(serialNumber)){
        starship.copy(shipName = shipName)
    } else {
        starship
    }
}

fun arrowRenameShipInSquadron(squadron: Squadron, serialNumber: String, shipName: String) : Squadron {
    return squadron
}

fun arrowDecommissionShip(fleet: Fleet, squadName: String, shipName: String): Fleet {
    return fleet
}