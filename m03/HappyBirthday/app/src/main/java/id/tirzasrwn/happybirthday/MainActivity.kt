package id.tirzasrwn.happybirthday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.tirzasrwn.happybirthday.ui.theme.HappyBirthdayTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HappyBirthdayTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                ) {
                    // call the greeting image. it will display to device or emulator
                    GreetingImage(
                            message = stringResource(R.string.happy_birthday_text),
                            from = stringResource(R.string.signature_text)
                    )
                }
            }
        }
    }
}

// define greeting text component
@Composable
fun GreetingText(message: String, from: String, modifier: Modifier = Modifier) {
    Column(verticalArrangement = Arrangement.Center, modifier = modifier) {
        Text(text = message, fontSize = 100.sp, lineHeight = 116.sp, textAlign = TextAlign.Center)
        Text(
                text = from,
                fontSize = 36.sp,
                modifier = Modifier.padding(16.dp).align(alignment = Alignment.CenterHorizontally)
        )
    }
}

// enable android studio preview
@Preview(showBackground = true)
@Composable
fun BirthdayCardPreview() {
    HappyBirthdayTheme { GreetingImage(message = "Happy Birthday Danilo!", from = "From Tirza") }
}

// define greeting image component. it contains image backgound and greeting text component
@Composable
fun GreetingImage(message: String, from: String, modifier: Modifier = Modifier) {
    // take image from res/drawable-nodpi
    val image = painterResource(R.drawable.androidparty)
    Box(modifier) {
        // set background image
        Image(
                painter = image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                // set the opacity
                alpha = 0.5F
        )

        // display greeting message
        GreetingText(
                message = message,
                from = from,
                modifier = Modifier.fillMaxSize().padding(8.dp)
        )
    }
}
