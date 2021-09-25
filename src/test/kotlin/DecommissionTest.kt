import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class DecommissionTest {

    @Test
    fun decommission_should_remove_ship() {
        val fleet = Fleet(
            "Delta",
            listOf(
                Squadron(
                    "Hope",
                    listOf(
                        pegasus,
                        minotaur
                    )
                )
            )
        )
        val expectedFleet = Fleet(
            "Delta",
            listOf(
                Squadron(
                    "Hope",
                    listOf(
                        minotaur
                    )
                )
            )
        )
        val actualFleet = decommissionShip(fleet, "Hope", "Pegasus")
        assertEquals(expectedFleet, actualFleet)
    }

    @Test
    fun arrow_squadron_should_remove_ship_by_serial_number() {
        val squadron = Squadron(
            "Hope",
            listOf(
                pegasus,
                minotaur
            )
        )
        val expectedSquadron = Squadron(
            "Hope",
            listOf(
                pegasus
            )
        )
        val modifiedSquadron = arrowRemoveShipBySerialNumber(squadron, "YR023")
        assertEquals(expectedSquadron, modifiedSquadron)
    }

    @Test
    fun arrow_decommission_should_remove_ship() {
        val fleet = Fleet(
            "Delta",
            listOf(
                Squadron(
                    "Hope",
                    listOf(
                        pegasus,
                        minotaur
                    )
                )
            )
        )
        val expectedFleet = Fleet(
            "Delta",
            listOf(
                Squadron(
                    "Hope",
                    listOf(
                        minotaur
                    )
                )
            )
        )
        val actualFleet = arrowDecommissionShip(fleet, "Hope", "XP130")
        assertEquals(expectedFleet, actualFleet)
    }
}
