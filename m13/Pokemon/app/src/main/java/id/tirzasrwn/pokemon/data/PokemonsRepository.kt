package id.tirzasrwn.pokemon.data

import id.tirzasrwn.pokemon.model.Pokemon
import id.tirzasrwn.pokemon.model.PokemonDetail
import id.tirzasrwn.pokemon.network.PokemonApi

class PokemonRepository(private val pokemonApi: PokemonApi) {

    suspend fun getPokemonList(limit: Int, offset: Int): List<Pokemon> {
        return pokemonApi.getPokemonList(limit, offset).results
    }

    suspend fun getPokemonDetail(id: Int): PokemonDetail {
        return pokemonApi.getPokemonDetail(id)
    }
}
