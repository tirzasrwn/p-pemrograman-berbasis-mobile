package id.tirzasrwn.pokemon.ui.screens


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.tirzasrwn.pokemon.model.Pokemon
import id.tirzasrwn.pokemon.network.PokemonApi
import kotlinx.coroutines.launch
import java.io.IOException
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue


sealed interface PokemonUiState {
    data class Success(val pokemonList: List<Pokemon>) : PokemonUiState
    object Error : PokemonUiState
    object Loading : PokemonUiState
}

class PokemonViewModel : ViewModel() {

    var uiState: PokemonUiState by mutableStateOf(PokemonUiState.Loading)
        private set

    init {
        fetchPokemon(0, 100)
    }

    fun fetchPokemon(offset: Int = 0, limit: Int = 10) {
        viewModelScope.launch {
            uiState = PokemonUiState.Loading
            try {
                val response = PokemonApi.service.getPokemon(offset, limit)
                uiState = PokemonUiState.Success(response.results)
            } catch (e: IOException) {
                uiState = PokemonUiState.Error
            } catch (e: Exception) {
                uiState = PokemonUiState.Error
            }
        }
    }
}
