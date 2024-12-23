package id.tirzasrwn.pokemon.model

data class PokemonDetail(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val base_experience: Int,
    val sprites: PokemonSprites,
    val types: List<PokemonType>, // List of types
    val abilities: List<PokemonAbility>, // List of abilities
    val stats: List<PokemonStat>
)

data class PokemonSprites(
    val front_default: String,
    val front_shiny: String? // Optional shiny sprite
)

data class PokemonType(
    val slot: Int,
    val type: TypeInfo
)

data class TypeInfo(
    val name: String,
    val url: String
)

data class PokemonAbility(
    val is_hidden: Boolean,
    val slot: Int,
    val ability: AbilityInfo
)

data class AbilityInfo(
    val name: String,
    val url: String
)

data class PokemonStat(
    val base_stat: Int,
    val effort: Int,
    val stat: StatInfo
)

data class StatInfo(
    val name: String,
    val url: String
)

