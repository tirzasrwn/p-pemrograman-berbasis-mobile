package id.tirzasrwn.amphibians.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import id.tirzasrwn.amphibians.AmphibiansApplication
import id.tirzasrwn.amphibians.data.AmphibiansRepository
import id.tirzasrwn.amphibians.model.Amphibian
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

/**
 * UI state for the Home screen
 */
// View
// UI state
sealed interface AmphibiansUiState {
    data class Success(val amphibians: List<Amphibian>) : AmphibiansUiState
    object Error : AmphibiansUiState
    object Loading : AmphibiansUiState
}

/**
 * ViewModel containing the app data and method to retrieve the data
 */
// ViewModel
class AmphibiansViewModel(private val amphibiansRepository: AmphibiansRepository) : ViewModel() {

    var amphibiansUiState: AmphibiansUiState by mutableStateOf(AmphibiansUiState.Loading)
        private set

    // 1
    init {
        getAmphibians()
    }

    // 2
    fun getAmphibians() {
        viewModelScope.launch {
            // initiate with loading state
            amphibiansUiState = AmphibiansUiState.Loading
            // if success to fetch the api set ui state to success
            // 5
            amphibiansUiState = try {
                // 4
                AmphibiansUiState.Success(amphibiansRepository.getAmphibians())
                // if error, set ui state to error
            } catch (e: IOException) {
                AmphibiansUiState.Error
            } catch (e: HttpException) {
                AmphibiansUiState.Error
            }
        }
    }

    /**
     * Factory for [AmphibiansViewModel] that takes [AmphibiansRepository] as a dependency
     */
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]
                        as AmphibiansApplication)
                val amphibiansRepository = application.container.amphibiansRepository
                AmphibiansViewModel(amphibiansRepository = amphibiansRepository)
            }
        }
    }
}
