import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class DecommissionTest {
    private val pegasus = Starship("Pegasus", "XP130", 50, 100)
    private val minotaur = Starship("Minotaur", "YR023", 70, 100)

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
    fun arrow_starship_should_be_renamed() {
        val expectedStarship = pegasus.copy(shipName = "Minotaur")
        val renamedStarship = arrowRenameStarship(pegasus, "Minotaur")
        assertEquals(expectedStarship, renamedStarship)
    }

    @Test
    fun arrow_squadron_should_rename_ship_by_serial_number() {
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
                pegasus,
                minotaur.copy(shipName = "Icarus")
            )
        )
        val modifiedSquadron = arrowRenameShipInSquadron(squadron, "YR023", "Icarus")
        assertEquals(expectedSquadron, modifiedSquadron)
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
