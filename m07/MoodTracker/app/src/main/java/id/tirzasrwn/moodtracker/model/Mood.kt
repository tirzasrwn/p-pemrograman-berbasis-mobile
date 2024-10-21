package id.tirzasrwn.moodtracker.model

data class Mood(
    val day: Int,
    val emoji: String,
    val description: String,
    val story: String,
    val imageRes: Int
)
