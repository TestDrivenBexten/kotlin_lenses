
val pegasus = Starship("Pegasus", "XP130", 50, 100)
val minotaur = Starship("Minotaur", "YR023", 70, 100)
val deltaFleet = Fleet(
    "Delta",
    Coordinates(100, 100),
    Admiral("Janeway", Steward("Tuvok")),
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
