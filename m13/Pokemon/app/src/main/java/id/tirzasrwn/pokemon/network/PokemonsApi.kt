package id.tirzasrwn.pokemon.network

import id.tirzasrwn.pokemon.model.PokemonListResponse
import id.tirzasrwn.pokemon.model.PokemonDetail
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

// learning material: repository - service api
// PokemonApi is an interface that defines the API endpoints for retrieving Pokemon data
interface PokemonApi {
    // getPokemonList returns a list of Pokemon
    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): PokemonListResponse

    // getPokemonDetail returns the details of a specific Pokemon
    @GET("pokemon/{id}")
    suspend fun getPokemonDetail(@Path("id") id: Int): PokemonDetail

    companion object {
        fun create(): PokemonApi {
            // learning material: architecture mvvm - retrofit
            val retrofit = Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(PokemonApi::class.java)
        }
    }
}
