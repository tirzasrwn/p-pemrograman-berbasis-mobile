package id.tirzasrwn.moodtracker


// moodListScreen.kt
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
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
            Text("Mood Tracker")
        }
    ) { paddingValues ->  // the padding values provided by Scaffold
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues) // apply the padding from the Scaffold
                .padding(16.dp) // your custom padding
        ) {
            val moodList = Datasource().loadMoods();
            items(moodList) { mood ->
                MoodCard(mood, navController)
            }
        }
    }
}

@Composable
fun MoodCard(mood: Mood, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                // navigate to the detail page with mood details
                navController.navigate("moodDetail/${mood.day}/${mood.emoji}/${mood.description}/${mood.story}/${mood.imageRes}")
            },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = mood.emoji, style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1f) // allow the column to take available space
            ) {
                Text(text = "Day ${mood.day}", style = MaterialTheme.typography.headlineSmall)
                Text(text = mood.description, style = MaterialTheme.typography.bodySmall)
            }
            // add a button with an arrow icon to navigate to the detail screen
            Button(
                onClick = {
                    // navigate to the detail page with mood details
                    navController.navigate("moodDetail/${mood.day}/${mood.emoji}/${mood.description}/${mood.story}/${mood.imageRes}")
                },
                modifier = Modifier.padding(start = 8.dp) // add some padding to the button
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight, // use the arrow icon
                    contentDescription = "Navigate to details",
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MoodCardPreview() {
    val navController = rememberNavController() // create a NavController instance
    MoodCard(
        mood = Mood(
            day = 1,
            emoji = "😊",
            description = "Feeling Happy",
            story = "Today was a fantastic day! I had a lot of fun with friends.",
            imageRes = R.drawable.mood_image
        ),
        navController = navController
    )
}