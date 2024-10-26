package id.tirzasrwn.moodtracker

import android.content.res.Configuration
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import id.tirzasrwn.moodtracker.data.Datasource

@Composable
fun MoodDetailScreen(
    index: Int?, // index for navigation
    day: String?,
    emoji: String?,
    description: String?,
    story: String?,
    imageRes: Int?,
    navController: NavController // nav controller context
) {
    // handle the Android back button
    BackHandler {
        navController.navigate("moodList") {
            popUpTo("moodList") { inclusive = true }
        }
    }

    // sample mood list from Datasource
    val moodList = Datasource().loadMoods()

    // get the current orientation
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    // scaffold provides custom top definition
    Scaffold(topBar = {
        // call custom top bar
        CustomTopBar(navController)
    }) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (isLandscape) {
                //  +-------------------+------------------------------+
                //  |                   |                              |
                //  |      image        |          text content        |
                //  |                   |                              |
                //  |                   |  emoji                       |
                //  |                   |  day 1                       |
                //  |                   |  description of the day...   |
                //  |                   |                              |
                //  |                   |  story of the day            |
                //  |                   |  this is the story...        |
                //  |                   |                              |
                //  +-------------------+------------------------------+

                // layout for landscape mode
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // image on the left side
                    // if imageRes not null, let block will execute
                    imageRes?.let {
                        Image(
                            painter = painterResource(id = it),
                            contentDescription = "Mood image for the day",
                            modifier = Modifier
                                .fillMaxHeight()
                                .weight(1f)
                                .padding(end = 16.dp),
                            contentScale = ContentScale.Crop
                        )
                    }

                    // text content on the right side
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Top
                    ) {
                        // display emoji
                        Text(
                            text = emoji ?: "",
                            style = MaterialTheme.typography.displayMedium,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        // display day and description
                        Text(text = "Day $day", style = MaterialTheme.typography.titleSmall)
                        Text(text = description ?: "", style = MaterialTheme.typography.bodyMedium)

                        // add spacer for blank space
                        Spacer(modifier = Modifier.height(16.dp))

                        // display story
                        Text(text = "Story of the day", style = MaterialTheme.typography.titleSmall)
                        Text(
                            text = story ?: "No story available.",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }
            } else {
                // layout for portrait mode
                // +--------------------------------------------------+
                // |                  [emoji]                         |
                // |                  day X                           |
                // |                  [description]                   |
                // |                  [image display]                 |
                // |                  story of the day                |
                // |                  [story content]                 |
                // +--------------------------------------------------+
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    // display emoji
                    Text(
                        text = emoji ?: "",
                        style = MaterialTheme.typography.displayMedium,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    // display day and description
                    Text(text = "Day $day", style = MaterialTheme.typography.titleSmall)
                    Text(text = description ?: "", style = MaterialTheme.typography.bodyMedium)

                    Spacer(modifier = Modifier.height(16.dp))

                    // display image
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

                    // display story
                    Text(text = "Story of the day", style = MaterialTheme.typography.titleSmall)
                    Text(
                        text = story ?: "No story available.",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(top = 8.dp)
                    )

                    // spacer to push content up
                    Spacer(modifier = Modifier.weight(1f)) // this will push the buttons to the bottom
                }
            }

            // add back and next buttons at the bottom
            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // define back button
                Button(
                    onClick = {
                        // if button click, go to previous mood
                        if (index != null && index > 0) {
                            // Navigate to the previous mood
                            val previousMood = moodList[index - 1]
                            navController.navigate("moodDetail/${index - 1}/${previousMood.day}/${previousMood.emoji}/${previousMood.description}/${previousMood.story}/${previousMood.imageRes}")
                        }
                    }, enabled = index != null && index > 0 // Disable if first mood
                ) {
                    Text("Back")
                }

                // define next button
                Button(
                    // if button click, go to next mood
                    onClick = {
                        if (index != null && index < moodList.size - 1) {
                            // navigate to the next mood
                            val nextMood = moodList[index + 1]
                            navController.navigate("moodDetail/${index + 1}/${nextMood.day}/${nextMood.emoji}/${nextMood.description}/${nextMood.story}/${nextMood.imageRes}")
                        }
                    }, enabled = index != null && index < moodList.size - 1 // disable if last mood
                ) {
                    Text("Next")
                }
            }
        }
    }
}

@Composable
fun CustomTopBar(navController: NavController) {
    // custom row-based top bar with back button and title
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // back button that explicitly navigates to MoodListScreen
        IconButton(onClick = {
            navController.navigate("moodList") {
                popUpTo("moodList") { inclusive = true } // clear the backstack up to MoodListScreen
            }
        }) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                contentDescription = "Back"
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        // title in the center
        Text(
            text = "Mood Detail", style = MaterialTheme.typography.titleMedium
        )
    }
}

// create preview for portrait mode
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

// create preview for landscape mode
@Preview(
    showBackground = true, widthDp = 720, heightDp = 360
)
@Composable
fun MoodDetailScreenLandscapePreview() {
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
