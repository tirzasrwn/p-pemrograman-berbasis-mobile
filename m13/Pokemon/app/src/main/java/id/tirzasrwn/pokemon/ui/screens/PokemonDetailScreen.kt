package id.tirzasrwn.pokemon.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import id.tirzasrwn.pokemon.model.PokemonDetail
import id.tirzasrwn.pokemon.ui.state.PokemonDetailUiState
import id.tirzasrwn.pokemon.ui.viewmodel.PokemonViewModel

@Composable
fun PokemonDetailScreen(viewModel: PokemonViewModel) {
    val uiState = viewModel.pokemonDetailUiState

    // Fetch details when the screen is displayed
    LaunchedEffect(viewModel.getPokemonId()) {
        viewModel.getPokemonDetail()
    }

    when (uiState) {
        is PokemonDetailUiState.Loading -> LoadingState()
        is PokemonDetailUiState.Error -> ErrorState()
        is PokemonDetailUiState.Success -> PokemonDetailView(pokemonDetail = uiState.pokemonDetail)
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
        // Display Pokemon image
        SubcomposeAsyncImage(
            model = pokemonDetail.sprites.front_default,
            contentDescription = pokemonDetail.name,
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp),
            contentScale = ContentScale.Fit,
            loading = {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            },
            error = {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "Image not available",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Details card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = pokemonDetail.name.capitalize(),
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextDetail(label = "ID", value = pokemonDetail.id.toString())
                TextDetail(label = "Height", value = "${pokemonDetail.height} decimeters")
                TextDetail(label = "Weight", value = "${pokemonDetail.weight} hectograms")
                TextDetail(label = "Base Experience", value = pokemonDetail.base_experience.toString())
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Types section
        DetailSection(
            title = "Types",
            items = pokemonDetail.types.map { it.type.name.capitalize() },
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Abilities section
        DetailSection(
            title = "Abilities",
            items = pokemonDetail.abilities.map {
                it.ability.name.capitalize() + if (it.is_hidden) " (Hidden)" else ""
            },
            color = MaterialTheme.colorScheme.tertiary
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Stats section
        DetailSection(
            title = "Stats",
            items = pokemonDetail.stats.map { "${it.stat.name.capitalize()}: ${it.base_stat}" },
            color = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
fun TextDetail(label: String, value: String) {
    Text(
        text = "$label: $value",
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onSurface
    )
}

@Composable
fun DetailSection(title: String, items: List<String>, color: Color) {
    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = color,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        items.forEach { item ->
            Text(
                text = item,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
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
        Text(
            text = "Failed to load Pokemon details",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.error,
            textAlign = TextAlign.Center
        )
    }
}
