package id.tirzasrwn.pokemon.ui.state

import id.tirzasrwn.pokemon.model.Pokemon

// learning material: ui state - data layer
sealed interface PokemonListUiState {
    data class Success(val pokemons: List<Pokemon>) : PokemonListUiState
    object Error : PokemonListUiState
    object Loading : PokemonListUiState
}
