package id.tirzasrwn.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.tirzasrwn.businesscard.ui.theme.BusinessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusinessCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                        modifier = Modifier.fillMaxSize(),
                        // use user color from res/values/colors.xml
                        color = colorResource(R.color.background)
                ) {
                    // call the BusinessCard component. it will display to device or emulator
                    BusinessCard()
                }
            }
        }
    }
}

@Composable
fun BusinessCard() {
    Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
    ) {
        // take image from res/drawable-nodpi
        val image = painterResource(R.drawable.android_logo)
        Image(painter = image, contentDescription = null, Modifier.fillMaxWidth(0.5f))

        Text(
                // use user string constant from res/values/strings.xml
                // sp stands for scale-independent pixels
                text = stringResource(R.string.my_name),
                fontSize = 50.sp,
                color = colorResource(R.color.text)
        )
        Text(
                text = stringResource(R.string.my_title),
                fontSize = 25.sp,
                color = colorResource(R.color.text)
        )

        // add spacer (blank space)
        // dp stands for density-independent pixels
        Spacer(modifier = Modifier.padding(bottom = 200.dp))

        // add horizontal line
        HorizontalDivider(modifier = Modifier.fillMaxWidth(), color = colorResource(R.color.line))

        // call ContactRow component with text blur
        ContactRow(
                text = stringResource(R.string.my_phone_number),
                icon = Icons.Rounded.Phone,
                // set blur radius
                textBlur = 5.dp,
        )
        HorizontalDivider(modifier = Modifier.fillMaxWidth(), color = colorResource(R.color.line))
        // call ContactRow component without text blur
        ContactRow(text = stringResource(R.string.twitter_handle), icon = Icons.Rounded.Share)
        HorizontalDivider(modifier = Modifier.fillMaxWidth(), color = colorResource(R.color.line))
        ContactRow(text = stringResource(R.string.my_email), icon = Icons.Rounded.Email)
        HorizontalDivider(modifier = Modifier.fillMaxWidth(), color = colorResource(R.color.line))
    }
}

// define ContactRow component
// default value for textBlur is 0.dp
@Composable
fun ContactRow(text: String, icon: ImageVector, textBlur: Dp = 0.dp) {
    Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.padding(16.dp)) {
        Icon(
                imageVector = icon,
                contentDescription = null,
                tint = colorResource(R.color.text),
                modifier = Modifier.weight(1f)
        )
        Text(
                text = text,
                color = colorResource(R.color.text),
                modifier = Modifier.weight(3f).blur(textBlur)
        )
    }
}

// enable android preview display
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    Surface(modifier = Modifier.fillMaxSize(), color = colorResource(R.color.background)) {
        BusinessCardTheme { BusinessCard() }
    }
}
