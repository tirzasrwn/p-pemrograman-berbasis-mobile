package id.tirzasrwn.pokemon.ui.state

import id.tirzasrwn.pokemon.model.PokemonDetail

sealed interface PokemonDetailUiState {
    data class Success(val pokemonDetail: PokemonDetail) : PokemonDetailUiState
    object Error : PokemonDetailUiState
    object Loading : PokemonDetailUiState
}
