package id.tirzasrwn.pokemon.model

// learning material: mvvm architecture - model - data object
// PokemonListResponse is a data object that represents a list of Pokemon
data class PokemonListResponse(
    val count: Int,
    val results: List<Pokemon>
)
