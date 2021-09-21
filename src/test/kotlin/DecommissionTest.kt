import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class DecommissionTest {

    @Test
    fun decommission_should_remove_ship(){
        val fleet = Fleet("Delta",
            listOf(Squadron("Hope",
                listOf(
                    Starship("Pegasus", "XP130"),
                    Starship("Minotaur", "YR023")
                )
                )
            )
        )
        val expectedFleet = Fleet("Delta",
            listOf(Squadron("Hope",
                listOf(
                    Starship("Minotaur", "YR023")
                )
            )
            )
        )
        val actualFleet = decommissionShip(fleet, "Hope", "Pegasus")
        assertEquals(expectedFleet, actualFleet)
    }

    @Test
    fun arrow_starship_should_be_renamed(){
        val starship = Starship("Pegasus", "POX351")
        val expectedStarship = Starship("Minotaur", "POX351")
        val renamedStarship = arrowRenameStarship(starship, "Minotaur")
        assertEquals(expectedStarship, renamedStarship)
    }

    @Test
    fun arrow_squadron_should_rename_ship_by_serial_number(){
        val squadron = Squadron("Hope",
            listOf(
                Starship("Pegasus", "XP130"),
                Starship("Minotaur", "YR023")
            )
        )
        val expectedSquadron = Squadron("Hope",
            listOf(
                Starship("Pegasus", "XP130"),
                Starship("Icarus", "YR023")
            )
        )
        val modifiedSquadron = arrowRenameShipInSquadron(squadron, "YR023", "Icarus")
        assertEquals(expectedSquadron, modifiedSquadron)
    }

    @Test
    fun arrow_squadron_should_remove_ship_by_serial_number(){
        val squadron = Squadron("Hope",
            listOf(
                Starship("Pegasus", "XP130"),
                Starship("Minotaur", "YR023")
            )
        )
        val expectedSquadron = Squadron("Hope",
            listOf(
                Starship("Pegasus", "XP130")
            )
        )
        val modifiedSquadron = arrowRemoveShipBySerialNumber(squadron, "YR023")
        assertEquals(expectedSquadron, modifiedSquadron)
    }

    @Test
    fun arrow_decommission_should_remove_ship(){
        val fleet = Fleet("Delta",
            listOf(Squadron("Hope",
                listOf(
                    Starship("Pegasus", "XP130"),
                    Starship("Minotaur", "YR023")
                )
            )
            )
        )
        val expectedFleet = Fleet("Delta",
            listOf(Squadron("Hope",
                listOf(
                    Starship("Minotaur", "YR023")
                )
            )
            )
        )
        val actualFleet = arrowDecommissionShip(fleet, "Hope", "Pegasus")
        assertEquals(expectedFleet, actualFleet)
    }
}