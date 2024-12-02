package id.tirzasrwn.pokemon.network


import id.tirzasrwn.pokemon.model.PokemonResponse
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://pokeapi.co/api/v2/"

/**
 * Retrofit service to fetch Pokemon data.
 */
interface PokemonApiService {
    @GET("pokemon")
    suspend fun getPokemon(
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 50
    ): PokemonResponse
}

object PokemonApi {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(Json { ignoreUnknownKeys = true }.asConverterFactory("application/json".toMediaType()))
        .build()

    val service: PokemonApiService by lazy {
        retrofit.create(PokemonApiService::class.java)
    }
}
