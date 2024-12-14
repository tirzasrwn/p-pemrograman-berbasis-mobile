package id.tirzasrwn.pokemon

import android.app.Application
import id.tirzasrwn.pokemon.data.PokemonRepository
import id.tirzasrwn.pokemon.network.PokemonApi

class PokemonApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainer()
    }
}

class AppContainer {
    private val pokemonApi = PokemonApi.create()
    val pokemonRepository = PokemonRepository(pokemonApi)
}
