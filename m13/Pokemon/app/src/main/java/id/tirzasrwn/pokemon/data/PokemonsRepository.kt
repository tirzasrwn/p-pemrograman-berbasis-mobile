package id.tirzasrwn.pokemon.data

import id.tirzasrwn.pokemon.model.Pokemon
import id.tirzasrwn.pokemon.model.PokemonDetail
import id.tirzasrwn.pokemon.network.PokemonApi

// class PokemonRepository is a repository for retrieving Pokemon data from the API
class PokemonRepository(private val pokemonApi: PokemonApi) {

    // getPokemonList returns a list of Pokemon from the API
    suspend fun getPokemonList(limit: Int, offset: Int): List<Pokemon> {
        return pokemonApi.getPokemonList(limit, offset).results
    }

    // getPokemonDetail returns the details of a specific Pokemon from the API
    suspend fun getPokemonDetail(id: Int): PokemonDetail {
        return pokemonApi.getPokemonDetail(id)
    }
}
