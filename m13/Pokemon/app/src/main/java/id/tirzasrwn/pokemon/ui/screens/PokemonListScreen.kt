package id.tirzasrwn.pokemon.ui.screens

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import id.tirzasrwn.pokemon.PokemonScreen
import id.tirzasrwn.pokemon.model.Pokemon
import id.tirzasrwn.pokemon.ui.state.PokemonListUiState
import id.tirzasrwn.pokemon.ui.viewmodel.PokemonViewModel

@Composable
fun PokemonListScreen(viewModel: PokemonViewModel, navController: NavHostController) {
    val uiState = viewModel.pokemonListUiState

    when (uiState) {
        is PokemonListUiState.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is PokemonListUiState.Error -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Something went wrong. Please try again.",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center
                )
            }
        }

        is PokemonListUiState.Success -> {
            PokemonListView(
                pokemons = uiState.pokemons,
                onLoadMore = { viewModel.getPokemonList() },
                onItemClick = { pokemonId ->
                    Log.d("PokemonListScreen", "Clicked on Pokemon with ID: $pokemonId")
                    viewModel.setPokemonId(pokemonId)
                    navController.navigate(PokemonScreen.Detail.name)
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
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 8.dp, horizontal = 16.dp)
    ) {
        items(pokemons) { pokemon ->
            PokemonListItem(pokemon = pokemon, onItemClick = onItemClick)
        }

        item {
            Text(
                text = "Load More",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onLoadMore() }
                    .padding(vertical = 16.dp),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
fun PokemonListItem(pokemon: Pokemon, onItemClick: (Int) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onItemClick(pokemon.id) }
            .height(60.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = "${pokemon.id}. ${pokemon.name.capitalize()}",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}
