import arrow.core.Option
import arrow.core.Some
import arrow.core.toOption
import arrow.optics.Every
import arrow.optics.Lens
import arrow.optics.Optional
import kotlin.math.abs

val starshipName: Lens<Starship, String> = Lens(
    get = { it.shipName },
    set = { starship, shipName -> starship.copy(shipName = shipName) }
)

val starshipRefuel: Lens<Starship, Int> = Lens(
    get = { it.currentFuel },
    set = { ship, fuel -> ship.copy(currentFuel = ship.currentFuel + fuel) }
)

val starshipConsumeFuel: Lens<Starship, Int> = Lens(
    get = { it.currentFuel },
    set = { ship, fuel -> ship.copy(currentFuel = ship.currentFuel - fuel) }
)

val squadShips: Lens<Squadron, List<Starship>> = Lens(
    get = { it.registry },
    set = { squadron, registry -> squadron.copy(registry = registry) }
)

val fleetSquads: Lens<Fleet, List<Squadron>> = Lens(
    get = { it.registry },
    set = { fleet, registry -> fleet.copy(registry = registry) }
)

val stewardName: Lens<Steward, String> = Lens(
    get = { it.stewardName },
    set = { steward, name -> steward.copy(stewardName = name) }
)

val optionalAdmiral: Optional<Fleet, Admiral> = Optional(
    getOption = { fleet -> fleet.admiral.toOption() },
    set = { fleet, admiral -> fleet.copy(admiral = admiral) }
)

val optionalSteward: Optional<Admiral, Steward> = Optional(
    getOption = { admiral -> admiral.steward.toOption() },
    set = { admiral, steward -> admiral.copy(steward = steward) }
)

val everySquadron = Every.list<Squadron>()
val everyStarship = Every.list<Starship>()

val fleetShips = fleetSquads compose everySquadron compose squadShips

fun arrowRenameStarship(starship: Starship, shipName: String): Starship {
    return starshipName.modify(starship) { shipName }
}

fun arrowRemoveShipBySerialNumber(squadron: Squadron, serialNumber: String): Squadron {
    val hasSerialNumber = { ship: Starship -> ship.serialNumber == serialNumber }
    return squadShips.modify(squadron) { it.filter { ship -> !hasSerialNumber(ship) } }
}

fun renameShipBySerialNumber(starship: Starship, serialNumber: String, shipName: String): Starship {
    return if (starship.serialNumber == serialNumber) {
        starship.copy(shipName = shipName)
    } else {
        starship
    }
}

fun arrowRenameShipInSquadron(squadron: Squadron, serialNumber: String, shipName: String): Squadron {
    val squadronMap = squadShips compose everyStarship
    val squadUpdate = { starship: Starship -> renameShipBySerialNumber(starship, serialNumber, shipName) }
    return squadronMap.modify(squadron, squadUpdate)
}

fun arrowDecommissionShip(fleet: Fleet, squadName: String, serialNumber: String): Fleet {
    val fleetMap = fleetSquads compose everySquadron
    fun squadRemove(squadron: Squadron): Squadron {
        return if (squadron.squadName == squadName) {
            arrowRemoveShipBySerialNumber(squadron, serialNumber)
        } else {
            squadron
        }
    }
    return fleetMap.modify(fleet) { squadron: Squadron -> squadRemove(squadron) }
}

fun arrowRefuelShipsInFleet(fleet: Fleet, refuelAmount: Int): Fleet {
    val fleetRefuel = fleetShips compose everyStarship compose starshipRefuel
    return fleetRefuel.modify(fleet) { refuelAmount }
}

fun arrowListShipsInFleet(fleet: Fleet): List<String> {
    val fleetShipNames = fleetShips compose everyStarship compose starshipName
    return fleetShipNames.getAll(fleet)
}

fun arrowGetFleetSteward(fleet: Fleet): Steward? {
    return fleet.admiral?.steward
}

fun arrowRenameFleetSteward(fleet: Fleet, newName: String): Fleet {
    val fleetStewardName = optionalAdmiral compose optionalSteward compose stewardName
    return fleetStewardName.modify(fleet) { newName }
}

fun arrowWarpToDestination(fleet: Fleet, destination: Coordinates): Option<Fleet> {
    val originalCoordinates = fleet.coordinates
    val (x1, y1) = originalCoordinates
    val (x2, y2) = destination
    val travelDistance = abs(x1 - x2) + abs(y1 - y2)
    val fleetConsumeFuel = fleetShips compose everyStarship compose starshipConsumeFuel
    val movedFleet = fleet.copy(coordinates = destination)
    return Some(fleetConsumeFuel.modify(movedFleet) { travelDistance })
}