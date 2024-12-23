package id.tirzasrwn.pokemon

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import id.tirzasrwn.pokemon.ui.screens.PokemonDetailScreen
import id.tirzasrwn.pokemon.ui.screens.PokemonListScreen
import id.tirzasrwn.pokemon.ui.viewmodel.PokemonViewModel

/** enum values that represent the screens in the app */
enum class PokemonScreen(@StringRes val title: Int) {
    List(title = R.string.list_screen),
    Detail(title = R.string.detail_screen),
}

@Composable
fun PokemonAppBar(
    currentScreen: PokemonScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        colors =
        TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                // show back button when navigation is possible
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@Composable
fun PokemonApp(
    navController: NavHostController = rememberNavController()
) {
    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()

    // Get the name of the current screen
    val currentScreen = PokemonScreen.valueOf(backStackEntry?.destination?.route ?: PokemonScreen.List.name)

    Scaffold(
        topBar = {
            PokemonAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        val viewModel: PokemonViewModel = viewModel(factory = PokemonViewModel.Factory)

        NavHost(
            navController = navController,
            startDestination = PokemonScreen.List.name,
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable(PokemonScreen.List.name) {
                PokemonListScreen(viewModel = viewModel, navController = navController)
            }
            composable(PokemonScreen.Detail.name) {
                PokemonDetailScreen(viewModel = viewModel)
            }
        }
    }
}
