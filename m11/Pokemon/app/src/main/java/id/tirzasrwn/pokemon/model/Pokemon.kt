package id.tirzasrwn.pokemon.model

import kotlinx.serialization.Serializable

/**
 * Represents a Pokemon retrieved from the API.
 */
@Serializable
data class Pokemon(
    val name: String,
    val url: String
)

/**
 * Represents the API response structure.
 */
@Serializable
data class PokemonResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Pokemon>
)
