package id.tirzasrwn.moodtracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import id.tirzasrwn.moodtracker.ui.theme.MoodTrackerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // use custom mood tracker theme
            MoodTrackerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    // use Navigation Controller for multiple pages
                    // this is also uses for managing navigation between screens
                    val navController = rememberNavController()
                    // when application start, go to mood list screen
                    NavHost(navController, startDestination = "moodList") {
                        // define composable for mood list screen
                        composable("moodList") {
                            // call mood list screen
                            MoodListScreen(navController)
                        }
                        // define composable for mood detail screen
                        composable("moodDetail/{index}/{day}/{emoji}/{description}/{story}/{imageRes}") { backStackEntry ->
                            // receive argument passed to mood detail screen
                            val index = backStackEntry.arguments?.getString("index")?.toIntOrNull()
                            val day = backStackEntry.arguments?.getString("day")
                            val emoji = backStackEntry.arguments?.getString("emoji")
                            val description = backStackEntry.arguments?.getString("description")
                            val story = backStackEntry.arguments?.getString("story")
                            val imageRes =
                                backStackEntry.arguments?.getString("imageRes")?.toIntOrNull()

                            // call mood detail screen
                            MoodDetailScreen(
                                index, day, emoji, description, story, imageRes, navController
                            )
                        }
                    }
                }
            }
        }
    }
}