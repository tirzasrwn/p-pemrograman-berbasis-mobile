package id.tirzasrwn.pokemon.model

data class PokemonListResponse(
    val count: Int,
    val results: List<Pokemon>
)
