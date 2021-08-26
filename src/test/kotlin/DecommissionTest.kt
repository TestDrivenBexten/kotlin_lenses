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