package id.tirzasrwn.pokemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import id.tirzasrwn.pokemon.ui.PokemonApp
import id.tirzasrwn.pokemon.ui.theme.PokemonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge() // For edge-to-edge display support
        super.onCreate(savedInstanceState)
        setContent {
            PokemonTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    PokemonApp() // Start the main Pokemon app UI
                }
            }
        }
    }
}
