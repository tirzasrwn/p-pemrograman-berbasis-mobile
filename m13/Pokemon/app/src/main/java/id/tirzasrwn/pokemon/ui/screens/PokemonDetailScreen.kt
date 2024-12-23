package id.tirzasrwn.pokemon.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import id.tirzasrwn.pokemon.model.PokemonDetail
import id.tirzasrwn.pokemon.ui.state.PokemonDetailUiState
import id.tirzasrwn.pokemon.ui.viewmodel.PokemonViewModel

@Composable
fun PokemonDetailScreen(viewModel: PokemonViewModel) {
    val uiState = viewModel.pokemonDetailUiState

    // Fetch the details of the Pokémon when the screen is displayed
    LaunchedEffect(viewModel.getPokemonId()) {
        viewModel.getPokemonDetail()
    }

    when (uiState) {
        is PokemonDetailUiState.Loading -> {
            LoadingState()
        }
        is PokemonDetailUiState.Error -> {
            ErrorState()
        }
        is PokemonDetailUiState.Success -> {
            PokemonDetailView(pokemonDetail = uiState.pokemonDetail)
        }
    }
}
@Composable
fun PokemonDetailView(pokemonDetail: PokemonDetail) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        val imageUrl = pokemonDetail.sprites.front_default
        val painter = rememberImagePainter(imageUrl)

        Image(
            painter = painter,
            contentDescription = pokemonDetail.name,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Name: ${pokemonDetail.name.capitalize()}",
            style = MaterialTheme.typography.headlineSmall,
            color = Color.Gray
        )
        Text(
            text = "ID: ${pokemonDetail.id}",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Gray
        )
        Text(
            text = "Height: ${pokemonDetail.height} decimeters",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Gray
        )
        Text(
            text = "Weight: ${pokemonDetail.weight} hectograms",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Gray
        )
    }
}
@Composable
fun LoadingState() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorState() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Failed to load Pokémon details", color = Color.Red)
    }
}
