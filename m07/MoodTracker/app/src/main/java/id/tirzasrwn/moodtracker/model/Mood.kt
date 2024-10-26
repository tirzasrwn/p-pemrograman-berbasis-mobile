package id.tirzasrwn.moodtracker.model

// type mood that hold day, emoji, description, story, and image res
data class Mood(
    val day: Int,
    val emoji: String,
    val description: String,
    val story: String,
    val imageRes: Int
)
