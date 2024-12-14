package id.tirzasrwn.pokemon.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import id.tirzasrwn.pokemon.model.Pokemon
import id.tirzasrwn.pokemon.ui.state.PokemonListUiState
import id.tirzasrwn.pokemon.ui.viewmodel.PokemonViewModel

@Composable
fun PokemonListScreen(viewModel: PokemonViewModel, navController: NavHostController) {
    val uiState = viewModel.pokemonListUiState

    when (uiState) {
        is PokemonListUiState.Loading -> {
            LoadingState()
        }
        is PokemonListUiState.Error -> {
            ErrorState()
        }
        is PokemonListUiState.Success -> {
            PokemonListView(
                pokemons = uiState.pokemons,
                onLoadMore = {
                    viewModel.getPokemonList()
                },
                onItemClick = { pokemonId ->
                    Log.d("PokemonListScreen", "Clicked on Pokémon with ID: $pokemonId")
                    navController.navigate("pokemon_detail/$pokemonId")
                }
            )
        }
    }
}


@Composable
fun PokemonListView(
    pokemons: List<Pokemon>,
    onLoadMore: () -> Unit,
    onItemClick: (Int) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(pokemons) { pokemon ->
            PokemonListItem(pokemon = pokemon, onItemClick = onItemClick)
        }

        item {
            Button(
                onClick = onLoadMore,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text("Load more Pokémon")
            }
        }
    }
}
@Composable
fun PokemonListItem(pokemon: Pokemon, onItemClick: (Int) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onItemClick(pokemon.id) } // Make the list item clickable
    ) {
        Text(
            text = pokemon.name.capitalize(),
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.align(Alignment.CenterStart)
        )
    }
}
