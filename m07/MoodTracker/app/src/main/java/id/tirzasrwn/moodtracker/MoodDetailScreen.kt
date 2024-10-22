package id.tirzasrwn.moodtracker

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import id.tirzasrwn.moodtracker.data.Datasource

@Composable
fun MoodDetailScreen(
    index: Int?, // Add index for navigation
    day: String?,
    emoji: String?,
    description: String?,
    story: String?,
    imageRes: Int?,
    navController: NavController
) {
    // Handle the Android back button
    BackHandler {
        navController.navigate("moodList") {
            popUpTo("moodList") { inclusive = true }
        }
    }

    // Sample mood list from Datasource
    val moodList = Datasource().loadMoods()

    Scaffold(
        topBar = {
            CustomTopBar(navController)
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                // Display emoji
                Text(
                    text = emoji ?: "",
                    style = MaterialTheme.typography.displayMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                // Display day and description
                Text(text = "Day $day", style = MaterialTheme.typography.titleSmall)
                Text(text = description ?: "", style = MaterialTheme.typography.bodyMedium)

                Spacer(modifier = Modifier.height(16.dp))

                // Display image
                imageRes?.let {
                    Image(
                        painter = painterResource(id = it),
                        contentDescription = "Mood image for the day",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp),
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Display story
                Text(text = "Story of the day", style = MaterialTheme.typography.titleSmall)
                Text(
                    text = story ?: "No story available.",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 8.dp)
                )

                // Spacer to push content up
                Spacer(modifier = Modifier.weight(1f)) // This will push the buttons to the bottom
            }

            // Add Back and Next buttons at the bottom
            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        if (index != null && index > 0) {
                            // Navigate to the previous mood
                            val previousMood = moodList[index - 1]
                            navController.navigate("moodDetail/${index - 1}/${previousMood.day}/${previousMood.emoji}/${previousMood.description}/${previousMood.story}/${previousMood.imageRes}")
                        }
                    },
                    enabled = index != null && index > 0 // Disable if first mood
                ) {
                    Text("Back")
                }

                Button(
                    onClick = {
                        if (index != null && index < moodList.size - 1) {
                            // Navigate to the next mood
                            val nextMood = moodList[index + 1]
                            navController.navigate("moodDetail/${index + 1}/${nextMood.day}/${nextMood.emoji}/${nextMood.description}/${nextMood.story}/${nextMood.imageRes}")
                        }
                    },
                    enabled = index != null && index < moodList.size - 1 // Disable if last mood
                ) {
                    Text("Next")
                }
            }
        }
    }
}

@Composable
fun CustomTopBar(navController: NavController) {
    // Custom Row-based top bar with back button and title
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Back button that explicitly navigates to MoodListScreen
        IconButton(onClick = {
            navController.navigate("moodList") {
                popUpTo("moodList") { inclusive = true } // Clear the backstack up to MoodListScreen
            }
        }) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                contentDescription = "Back"
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        // Title in the center
        Text(
            text = "Mood Detail",
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MoodDetailScreenPreview() {
    val navController = rememberNavController()
    MoodDetailScreen(
        day = "1",
        emoji = "😊",
        description = "Feeling Happy",
        story = "Today was a fantastic day! I had a lot of fun with friends.",
        imageRes = R.drawable.mood_image1,
        navController = navController,
        index = 1,
    )
}