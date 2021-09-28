import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class SummaryTests {
    @Test
    fun arrow_should_return_ship_names_for_fleet() {
        val expectedShipNameList = listOf("Pegasus", "Minotaur")
        val actualShipNameList = arrowListShipsInFleet(deltaFleet)
        assertEquals(expectedShipNameList, actualShipNameList)
    }
}
