package id.tirzasrwn.pokemon.model

data class PokemonDetail(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val sprites: PokemonSprites
)

data class PokemonSprites(
    val front_default: String
)
