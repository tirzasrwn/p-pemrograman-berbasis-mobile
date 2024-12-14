package id.tirzasrwn.pokemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import id.tirzasrwn.pokemon.ui.screens.PokemonDetailScreen
import id.tirzasrwn.pokemon.ui.screens.PokemonListScreen
import id.tirzasrwn.pokemon.ui.viewmodel.PokemonViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController: NavHostController = rememberNavController()
            val viewModel: PokemonViewModel = viewModel(factory = PokemonViewModel.Factory)

            NavHost(navController = navController, startDestination = "pokemon_list") {
                composable("pokemon_list") {
                    PokemonListScreen(viewModel = viewModel, navController = navController)
                }
                composable("pokemon_detail/{pokemonId}") { backStackEntry ->
                    val pokemonId = backStackEntry.arguments?.getString("pokemonId")?.toIntOrNull()
                    if (pokemonId != null) {
                        PokemonDetailScreen(viewModel = viewModel, pokemonId = pokemonId)
                    }
                }
            }
        }
    }
}
