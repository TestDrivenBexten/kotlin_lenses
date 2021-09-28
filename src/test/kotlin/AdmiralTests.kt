import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class AdmiralTests {

    @Test
    fun should_return_null_steward_from_fleet_without_admiral() {
        val fleet = Fleet(
            "Delta",
            null,
            listOf(
                Squadron(
                    "Hope",
                    listOf(pegasus)
                )
            )
        )
        val steward = arrowGetFleetSteward(fleet)
        assertNull(steward)
    }

    @Test
    fun should_return_steward_from_fleet_with_admiral() {
        val steward = arrowGetFleetSteward(deltaFleet)
        assertEquals(steward, Steward("Tuvok"))
    }
}
