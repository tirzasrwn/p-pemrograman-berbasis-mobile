package id.tirzasrwn.pokemon.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import id.tirzasrwn.pokemon.PokemonApplication
import id.tirzasrwn.pokemon.data.PokemonRepository
import id.tirzasrwn.pokemon.model.Pokemon
import id.tirzasrwn.pokemon.ui.state.PokemonDetailUiState
import id.tirzasrwn.pokemon.ui.state.PokemonListUiState
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

// learning material: mvvm architecture - viewmodel
class PokemonViewModel(private val pokemonRepository: PokemonRepository) : ViewModel() {
    private var offset: Int = 0 // to track current offset
    private val limit: Int = 10 // fetch 10 more Pokemon each time
    private var pokemonId: Int = 0 // hold pokemon id for detail screen

    // ui state for pokemon list
    var pokemonListUiState: PokemonListUiState by mutableStateOf(PokemonListUiState.Loading)
        private set

    // ui state for pokemon detail
    var pokemonDetailUiState: PokemonDetailUiState by mutableStateOf(PokemonDetailUiState.Loading)
        private set

    // list of pokemon
    private var pokemonList: MutableList<Pokemon> = mutableListOf()

    // fetch pokemon list on initialization
    init {
        getPokemonList()
    }

    // returns pokemon list from repository
    fun getPokemonList() {
        viewModelScope.launch {
            pokemonListUiState = PokemonListUiState.Loading
            try {
                val newPokemons = pokemonRepository.getPokemonList(limit, offset)
                pokemonList.addAll(newPokemons)
                pokemonListUiState = PokemonListUiState.Success(pokemonList.toList())
                offset += limit // Increment offset for the next load
            } catch (e: IOException) {
                pokemonListUiState = PokemonListUiState.Error
            } catch (e: HttpException) {
                pokemonListUiState = PokemonListUiState.Error
            }
        }
    }

    // returns pokemon detail from repository
    fun getPokemonDetail() {
        viewModelScope.launch {
            pokemonDetailUiState = PokemonDetailUiState.Loading
            pokemonDetailUiState = try {
                PokemonDetailUiState.Success(pokemonRepository.getPokemonDetail(pokemonId))
            } catch (e: IOException) {
                PokemonDetailUiState.Error
            } catch (e: HttpException) {
                PokemonDetailUiState.Error
            }
        }
    }

    // set pokemon id for detail screen
    fun setPokemonId(id: Int) {
        pokemonId = id
    }

    // returns pokemon id for detail screen
    fun getPokemonId(): Int {
        return pokemonId
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]
                        as PokemonApplication)
                val pokemonRepository = application.container.pokemonRepository
                PokemonViewModel(pokemonRepository)
            }
        }
    }
}
