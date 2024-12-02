package id.tirzasrwn.pokemon.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import id.tirzasrwn.pokemon.model.Pokemon

@Composable
fun PokemonScreen(
    uiState: PokemonUiState,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    when (uiState) {
        is PokemonUiState.Loading -> LoadingScreen(modifier)
        is PokemonUiState.Error -> ErrorScreen(modifier)
        is PokemonUiState.Success -> PokemonList(uiState.pokemonList, modifier)
    }
}

@Composable
fun LoadingScreen(modifier: Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Loading...")
    }
}

@Composable
fun ErrorScreen(modifier: Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Error loading data. Please try again.")
    }
}

@Composable
fun PokemonList(pokemonList: List<Pokemon>, modifier: Modifier) {
    LazyColumn(modifier = modifier) {
        items(pokemonList) { pokemon ->
            Text(text = pokemon.name)
        }
    }
}
