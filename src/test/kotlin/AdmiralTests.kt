import org.junit.jupiter.api.Test

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
    }
}
