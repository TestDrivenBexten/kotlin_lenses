import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class RefuelTests {

    @Test
    fun should_refuel_ships_in_fleet_by_set_amount() {
        val fleet = deltaFleet
        val expectedFleet = Fleet(
            "Delta",
            Admiral("Janeway"),
            listOf(
                Squadron(
                    "Hope",
                    listOf(
                        pegasus.copy(currentFuel = 70),
                        minotaur.copy(currentFuel = 90),
                    )
                )
            )
        )
        val actualFleet = arrowRefuelShipsInFleet(fleet, 20)
        assertEquals(expectedFleet, actualFleet)
    }
}