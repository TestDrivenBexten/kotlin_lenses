
data class Steward(val stewardName: String)
data class Admiral(val admiralName: String, val steward: Steward?)
data class Fleet(
    val fleetName: String,
    val coordinates: Coordinates,
    val admiral: Admiral?,
    val registry: List<Squadron>
)
data class Coordinates(val x: Int, val y: Int)
data class Squadron(val squadName: String, val registry: List<Starship>)
data class Starship(
    val shipName: String,
    val serialNumber: String,
    val currentFuel: Int,
    val maxFuel: Int
)
