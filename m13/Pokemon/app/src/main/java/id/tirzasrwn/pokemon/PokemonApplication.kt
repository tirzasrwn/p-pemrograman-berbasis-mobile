package id.tirzasrwn.pokemon

import android.app.Application
import id.tirzasrwn.pokemon.data.PokemonRepository
import id.tirzasrwn.pokemon.network.PokemonApi

// learning material: repository - container
// learning material: mvvm architecture - model - repository - dependency injection
class PokemonApplication : Application() {
    lateinit var container: AppContainer

    // initialize AppContainer when the application is created
    override fun onCreate() {
        super.onCreate()
        container = AppContainer()
    }
}

// learning material: mvvm architecture - model - repository - container
class AppContainer {
    private val pokemonApi = PokemonApi.create() // create PokemonApi instance
    val pokemonRepository = PokemonRepository(pokemonApi) // create PokemonRepository instance
}
