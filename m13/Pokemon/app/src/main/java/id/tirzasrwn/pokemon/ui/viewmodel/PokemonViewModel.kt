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


class PokemonViewModel(private val pokemonRepository: PokemonRepository) : ViewModel() {
    private var offset: Int = 0 // To track current offset
    private val limit: Int = 10 // Fetch 10 more Pokemon each time
    private var pokemonId: Int = 0

    var pokemonListUiState: PokemonListUiState by mutableStateOf(PokemonListUiState.Loading)
        private set

    var pokemonDetailUiState: PokemonDetailUiState by mutableStateOf(PokemonDetailUiState.Loading)
        private set

    private var pokemonList: MutableList<Pokemon> = mutableListOf()

    init {
        getPokemonList()
    }

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

    fun setPokemonId(id: Int) {
        pokemonId = id
    }

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
