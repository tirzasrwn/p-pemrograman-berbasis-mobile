package id.tirzasrwn.artspace

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.tirzasrwn.artspace.ui.theme.ArtSpaceTheme

// a data class to represent each piece of art, with an image and description
// like struct in go/c
data class ArtPiece(val imageResId: Int, val description: String)

// artworks contains list of ArtPiece
val artworks = listOf(
    ArtPiece(R.drawable.starry_night_ballance1, "The Starry Night Ballance"),
    ArtPiece(R.drawable.windmills_on_montmartre, "Windmills On Montmartre"),
    ArtPiece(R.drawable.red_vineyards, "Red Vineyards"),
    ArtPiece(R.drawable.garten_des_hospitals_in_arles1, "Garten Des Hospitals in Arles"),
    ArtPiece(R.drawable.almond_blossom, "Almond Blossom"),
    ArtPiece(R.drawable.the_church_in_auvers, "The Church in Auvers"),
)

class MainActivity : ComponentActivity() {
    // called when the app starts
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // background surface of the app
                Surface(
                    modifier = Modifier.fillMaxSize(), color = Color.White
                ) {
                    // call the art space component
                    ArtSpaceApp()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceApp() {
    // remembering the current index of the artwork being shown, across rotations
    var currentIndex by rememberSaveable { mutableStateOf(0) }

    // get the current artwork based on the current index
    val currentArt = artworks[currentIndex]

    // get current device orientation (portrait/landscape)
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    if (isLandscape) {
        // if in landscape mode, display the image and text side by side
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically, // align content vertically in the center
        ) {
            // image section
            Image(
                painter = painterResource(id = currentArt.imageResId),
                contentDescription = currentArt.description,
                modifier = Modifier
                    .fillMaxHeight() // fill the vertical space
                    .weight(2f) // set weight for resizing relative to other elements
                    .padding(end = 16.dp) // add padding to the right
            )

            // text section
            Column(
                modifier = Modifier.weight(1f), // set weight for resizing
                horizontalAlignment = Alignment.CenterHorizontally, // align text to the center
                verticalArrangement = Arrangement.Center // arrange vertically in the center
            ) {
                // title of the artwork collection
                BasicText(
                    text = "Vincent Willem van Gogh's Arts",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontSize = 22.sp, // text size
                        fontWeight = FontWeight.Bold // make text bold
                    ),
                    modifier = Modifier.padding(vertical = 10.dp) // padding above and below
                )

                Spacer(modifier = Modifier.height(20.dp)) // add vertical space

                // description of the current artwork
                BasicText(
                    text = currentArt.description,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontSize = 20.sp,
                    ),
                    modifier = Modifier.padding(vertical = 10.dp)
                )

                Spacer(modifier = Modifier.height(20.dp)) // add vertical space

                // row of buttons for "Previous" and "Next"
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // previous button (decrease index)
                    Button(
                        onClick = { if (currentIndex > 0) currentIndex-- }, // ensure it doesn't go below 0
                        enabled = currentIndex > 0, // disable if at the first artwork
                        colors = ButtonDefaults.buttonColors(disabledContainerColor = Color.LightGray)
                    ) {
                        BasicText("Previous")
                    }

                    // next button (increase index)
                    Button(
                        onClick = { if (currentIndex < artworks.size - 1) currentIndex++ }, // ensure it doesn't exceed the list size
                        enabled = currentIndex < artworks.size - 1, // disable if at the last artwork
                        colors = ButtonDefaults.buttonColors(disabledContainerColor = Color.LightGray)
                    ) {
                        BasicText("Next")
                    }
                }
            }
        }
    } else {
        // if in portrait mode, stack everything vertically
        Column(
            modifier = Modifier
                .fillMaxSize() // fill the entire screen
                .padding(16.dp), // padding around the layout
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            // title of the collection
            BasicText(
                text = "Vincent Willem van Gogh's Arts",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(vertical = 10.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            // image of the current artwork
            Image(
                painter = painterResource(id = currentArt.imageResId),
                contentDescription = currentArt.description,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )

            Spacer(modifier = Modifier.height(20.dp))

            // description of the artwork
            BasicText(
                text = currentArt.description,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 20.sp,
                ),
                modifier = Modifier.padding(vertical = 10.dp)
            )

            Spacer(modifier = Modifier.height(100.dp))

            // row of buttons for "Previous" and "Next"
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // previous button
                Button(
                    onClick = { if (currentIndex > 0) currentIndex-- },
                    enabled = currentIndex > 0,
                    colors = ButtonDefaults.buttonColors(disabledContainerColor = Color.LightGray)
                ) {
                    BasicText("Previous")
                }

                // next button
                Button(
                    onClick = { if (currentIndex < artworks.size - 1) currentIndex++ },
                    enabled = currentIndex < artworks.size - 1,
                    colors = ButtonDefaults.buttonColors(disabledContainerColor = Color.LightGray)
                ) {
                    BasicText("Next")
                }
            }
        }
    }
}

// a preview function to visualize the app in android studio
@Preview(showBackground = true)
@Composable
fun PreviewArtSpaceApp() {
    ArtSpaceTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            ArtSpaceApp()
        }
    }
}
