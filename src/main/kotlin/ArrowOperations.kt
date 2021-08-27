import arrow.optics.Lens

val starshipName: Lens<Starship, String> = Lens(
    get = { it.shipName },
    set = { starship, shipName -> starship.copy(shipName = shipName)}
)

fun arrowRenameStarship(starship: Starship, shipName: String): Starship {
    val shipLambda = { _: String -> shipName}
    return starshipName.modify(starship, shipLambda)
}

fun arrowDecommissionShip(fleet: Fleet, squadName: String, shipName: String): Fleet {
    return fleet
}