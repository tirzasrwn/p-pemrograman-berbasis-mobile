package id.tirzasrwn.moodtracker

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

@Composable
fun MoodDetailScreen(
    day: String?,
    emoji: String?,
    description: String?,
    story: String?,
    imageRes: Int?,
    navController: NavController
) {
    Scaffold(
        topBar = {
            Text("Mood Detail")
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
                // display the emoji
                Text(text = emoji ?: "", style = MaterialTheme.typography.headlineLarge)

                Spacer(modifier = Modifier.height(16.dp))

                // display the day and description
                Text(text = "Day $day", style = MaterialTheme.typography.headlineSmall)
                Text(text = description ?: "", style = MaterialTheme.typography.bodySmall)

                Spacer(modifier = Modifier.height(24.dp))

                // display the image
                imageRes?.let {
                    Image(
                        painter = painterResource(id = it),
                        contentDescription = "Mood image for the day",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp), // adjust the height as needed
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // display the story
                Text(text = "Story of the day:", style = MaterialTheme.typography.headlineSmall)
                Text(
                    text = story ?: "No story available.",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            // back button
            Button(
                onClick = {
                    navController.popBackStack() // navigate back to the previous screen
                },
                modifier = Modifier
                    .align(Alignment.BottomStart) // align the button to the bottom left
                    .padding(16.dp) // add some padding to the button
            ) {
                // back button with an icon
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = "Back",
                )
            }
        }
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
        imageRes = R.drawable.mood_image1, // replace with your image resource
        navController = navController,
    )
}