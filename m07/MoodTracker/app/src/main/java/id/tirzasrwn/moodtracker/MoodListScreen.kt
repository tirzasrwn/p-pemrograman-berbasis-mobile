package id.tirzasrwn.moodtracker

// moodListScreen.kt
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import id.tirzasrwn.moodtracker.data.Datasource
import id.tirzasrwn.moodtracker.model.Mood

@Composable
fun MoodListScreen(navController: NavController) {
    Scaffold(
        topBar = {
            // Simple Text for top bar with padding
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Mood Tracker",
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            val moodList = Datasource().loadMoods()
            items(moodList.size) { index ->
                MoodCard(mood = moodList[index], index = index, navController = navController)
            }
        }
    }
}

@Composable
fun MoodCard(mood: Mood, index: Int, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable {
                // Navigate to mood detail with index
                navController.navigate("moodDetail/$index/${mood.day}/${mood.emoji}/${mood.description}/${mood.story}/${mood.imageRes}")
            },
        elevation = CardDefaults.cardElevation(2.dp) // Reduced elevation for minimalist look
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Display emoji with reduced size
            Text(text = mood.emoji, style = MaterialTheme.typography.headlineMedium)

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                // Simpler text styling
                Text(text = "Day ${mood.day}", style = MaterialTheme.typography.bodyLarge)
                Text(text = mood.description, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MoodCardPreview() {
    val navController = rememberNavController()
    MoodCard(
        mood = Mood(
            day = 1,
            emoji = "😊",
            description = "Feeling Happy",
            story = "Today was a fantastic day! I had a lot of fun with friends.",
            imageRes = R.drawable.mood_image1
        ),
        navController = navController,
        index = 1,
    )
}