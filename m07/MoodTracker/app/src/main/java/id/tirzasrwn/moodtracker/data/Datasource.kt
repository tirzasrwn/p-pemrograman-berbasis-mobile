package id.tirzasrwn.moodtracker.data

import id.tirzasrwn.moodtracker.model.Mood
import id.tirzasrwn.moodtracker.R

class Datasource {
    fun loadMoods(): List<Mood> {
        return listOf(
            Mood(
                day = 1,
                emoji = "😊",
                description = "Feeling Happy",
                story = "Today was a fantastic day! I had a lot of fun with friends.",
                imageRes = R.drawable.mood_image1
            ),
            Mood(
                day = 2,
                emoji = "😔",
                description = "Feeling Sad",
                story = "I felt a bit down today. Just one of those days.",
                imageRes = R.drawable.mood_image2
            ),
            Mood(
                day = 3,
                emoji = "😡",
                description = "Feeling Angry",
                story = "Had a frustrating day at work, but I managed to vent it out.",
                imageRes = R.drawable.mood_image3
            ),
            Mood(
                day = 4,
                emoji = "😴",
                description = "Feeling Tired",
                story = "I didn't sleep well last night, and it took a toll on me.",
                imageRes = R.drawable.mood_image4
            ),
            Mood(
                day = 5,
                emoji = "🤒",
                description = "Feeling Sick",
                story = "Caught a cold and spent the day resting.",
                imageRes = R.drawable.mood_image5
            ),
            Mood(
                day = 6,
                emoji = "😃",
                description = "Feeling Joyful",
                story = "Had an amazing breakfast and enjoyed a walk in the park.",
                imageRes = R.drawable.mood_image1
            ),
            Mood(
                day = 7,
                emoji = "😨",
                description = "Feeling Anxious",
                story = "I was really anxious about an upcoming presentation.",
                imageRes = R.drawable.mood_image2
            ),
            Mood(
                day = 8,
                emoji = "😌",
                description = "Feeling Calm",
                story = "Spent the day meditating and relaxing.",
                imageRes = R.drawable.mood_image3
            ),
            Mood(
                day = 9,
                emoji = "😃",
                description = "Feeling Motivated",
                story = "I set my goals for the week and felt super motivated!",
                imageRes = R.drawable.mood_image4
            ),
            Mood(
                day = 10,
                emoji = "😓",
                description = "Feeling Stressed",
                story = "So much work to do and not enough time!",
                imageRes = R.drawable.mood_image5
            ),
            Mood(
                day = 11,
                emoji = "🥳",
                description = "Feeling Celebratory",
                story = "Today was my birthday! Had a great time with family.",
                imageRes = R.drawable.mood_image1
            ),
            Mood(
                day = 12,
                emoji = "😢",
                description = "Feeling Heartbroken",
                story = "The news hit hard today, feeling emotional.",
                imageRes = R.drawable.mood_image2
            ),
            Mood(
                day = 13,
                emoji = "😁",
                description = "Feeling Grateful",
                story = "I thought about all I have in life and felt grateful.",
                imageRes = R.drawable.mood_image3
            ),
            Mood(
                day = 14,
                emoji = "😅",
                description = "Feeling Relieved",
                story = "I finally finished a project I was dreading.",
                imageRes = R.drawable.mood_image4
            ),
            Mood(
                day = 15,
                emoji = "🤔",
                description = "Feeling Confused",
                story = "Things didn’t go as planned, and I'm feeling a bit lost.",
                imageRes = R.drawable.mood_image5
            ),
            Mood(
                day = 16,
                emoji = "🥺",
                description = "Feeling Vulnerable",
                story = "Feeling sensitive after a friend shared their struggles.",
                imageRes = R.drawable.mood_image1
            ),
            Mood(
                day = 17,
                emoji = "🤗",
                description = "Feeling Loved",
                story = "Had a wonderful surprise from my best friend.",
                imageRes = R.drawable.mood_image2
            ),
            Mood(
                day = 18,
                emoji = "😏",
                description = "Feeling Playful",
                story = "Had a great time playing games with friends.",
                imageRes = R.drawable.mood_image3
            ),
            Mood(
                day = 19,
                emoji = "😵",
                description = "Feeling Overwhelmed",
                story = "So many responsibilities at once, feeling overwhelmed.",
                imageRes = R.drawable.mood_image4
            ),
            Mood(
                day = 20,
                emoji = "😌",
                description = "Feeling Content",
                story = "Relaxed day with cozy moments and good food.",
                imageRes = R.drawable.mood_image5
            ),
            Mood(
                day = 21,
                emoji = "🤩",
                description = "Feeling Excited",
                story = "Planning a trip and can't wait for the adventure!",
                imageRes = R.drawable.mood_image1
            ),
            Mood(
                day = 22,
                emoji = "😫",
                description = "Feeling Exhausted",
                story = "A long week is finally over; I'm so tired.",
                imageRes = R.drawable.mood_image2
            ),
            Mood(
                day = 23,
                emoji = "😇",
                description = "Feeling Inspired",
                story = "Read a great book that inspired new ideas.",
                imageRes = R.drawable.mood_image3
            ),
            Mood(
                day = 24,
                emoji = "🙁",
                description = "Feeling Disappointed",
                story = "Things didn’t turn out as I hoped today.",
                imageRes = R.drawable.mood_image4
            ),
            Mood(
                day = 25,
                emoji = "🥰",
                description = "Feeling Affectionate",
                story = "Received a lovely note from a friend.",
                imageRes = R.drawable.mood_image5
            ),
            Mood(
                day = 26,
                emoji = "😳",
                description = "Feeling Awkward",
                story = "Had a funny but awkward moment in a meeting.",
                imageRes = R.drawable.mood_image1
            ),
            Mood(
                day = 27,
                emoji = "😷",
                description = "Feeling Unwell",
                story = "Really wasn’t feeling good today; just rested at home.",
                imageRes = R.drawable.mood_image2
            ),
            Mood(
                day = 28,
                emoji = "😴",
                description = "Feeling Sleepy",
                story = "Long day at work, looking forward to a good night’s sleep.",
                imageRes = R.drawable.mood_image3
            ),
            Mood(
                day = 29,
                emoji = "🙌",
                description = "Feeling Accomplished",
                story = "Finally completed my personal project, feeling proud!",
                imageRes = R.drawable.mood_image4
            ),
            Mood(
                day = 30,
                emoji = "💖",
                description = "Feeling Loved",
                story = "Surrounded by wonderful people who make me feel special.",
                imageRes = R.drawable.mood_image5
            )
        )
    }
}