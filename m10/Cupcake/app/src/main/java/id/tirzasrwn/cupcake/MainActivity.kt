package id.tirzasrwn.cupcake

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import id.tirzasrwn.cupcake.ui.theme.CupcakeTheme

// MainActivity is the entry point of the cupcake app.
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge() // enable edge-to-edge display
        super.onCreate(savedInstanceState) // call superclass onCreate
        setContent { CupcakeTheme { CupcakeApp() } } // set content with cupcake theme
    }
}
