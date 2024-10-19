package id.tirzasrwn.affirmations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import id.tirzasrwn.affirmations.data.Datasource
import id.tirzasrwn.affirmations.model.Affirmation
import id.tirzasrwn.affirmations.ui.theme.AffirmationsTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AffirmationsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    // set the color background to the background color theme
                    color = MaterialTheme.colorScheme.background
                ) {
                    // call the main content
                    AffirmationsApp()
                }
            }
        }
    }
}

// main app content
@Composable
fun AffirmationsApp() {
    AffirmationList(
        // load affirmation list from Datasource
        affirmationList = Datasource().loadAffirmations(),
    )
}

// display a list of affirmation using lazy column
@Composable
fun AffirmationList(affirmationList: List<Affirmation>, modifier: Modifier = Modifier) {
    // a LazyColumn is a vertically scrolling list
    // that only composes and lays out the currently visible items
    LazyColumn(modifier = modifier) {
        // like for loop to list's length, items will iterate trough the list and
        // create a card for each affirmation
        items(affirmationList) { affirmation ->
            AffirmationCard(affirmation = affirmation, modifier = Modifier.padding(8.dp))
        }
    }
}

// create one affirmation card basic structure
@Composable
fun AffirmationCard(affirmation: Affirmation, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        // |-------|
        // | image |
        // |-------|
        // | text  |
        // |-------|
        Column {
            Image(
                painter = painterResource(affirmation.imageResourceId),
                contentDescription = stringResource(affirmation.stringResourceId),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(194.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = LocalContext.current.getString(affirmation.stringResourceId),
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}

// preview function for the AffirmationCard composable
@Preview
@Composable
private fun AffirmationCardPreview() {
    AffirmationCard(Affirmation(R.string.affirmation1, R.drawable.image1))
}

