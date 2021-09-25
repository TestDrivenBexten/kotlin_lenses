

data class Fleet(val fleetName: String, val registry: List<Squadron>)
data class Squadron(val squadName: String, val registry: List<Starship>)
data class Starship(val shipName: String, val serialNumber: String, val currentFuel: Int, val maxFuel: Int)
