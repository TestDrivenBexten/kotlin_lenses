import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class RenameTests {

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
}
