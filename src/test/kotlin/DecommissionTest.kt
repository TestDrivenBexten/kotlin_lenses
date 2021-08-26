import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class DecommissionTest {

    @Test
    fun decommision_should_remove_ship(){
        val fleet = Fleet("Delta",
            listOf(Squadron("Hope",
                listOf(
                    Starship("Pegasus", "XP130"),
                    Starship("Minotaur", "YR023")
                )
                )
            )
        )
        val expectedfleet = Fleet("Delta",
            listOf(Squadron("Hope",
                listOf(
                    Starship("Minotaur", "YR023")
                )
            )
            )
        )
        val actualFleet = DecommissionShip(fleet, "Hope", "Pegasus")
        assertEquals(expectedfleet, actualFleet)
    }
}