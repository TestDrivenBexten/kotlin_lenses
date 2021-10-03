import arrow.core.Some
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class WarpTests {

    @Test
    fun fleet_should_warp_to_coordinates() {
        val destination = Coordinates(125, 75)
        val expectedFleet = Some(Fleet(
            "Delta",
            Coordinates(125, 75),
            Admiral("Janeway", Steward("Tuvok")),
            listOf(
                Squadron(
                    "Hope",
                    listOf(
                        pegasus.copy(currentFuel = 0),
                        minotaur.copy(currentFuel = 20),
                    )
                )
            )
        ))
        val actualFleet = arrowWarpToDestination(deltaFleet, destination)
        assertEquals(expectedFleet, actualFleet)
    }
}