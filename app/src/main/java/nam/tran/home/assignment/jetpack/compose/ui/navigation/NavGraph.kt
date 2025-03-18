package nam.tran.home.assignment.jetpack.compose.ui.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import nam.tran.home.assignment.jetpack.compose.ui.feature.onboarding.OnBoardingViewModel
import nam.tran.home.assignment.jetpack.compose.ui.feature.onboarding.screen.OnBoardingScreen

@Composable
fun NavGraph(
    startDestination : String
) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        navigation(route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route){
            composable(route = Route.OnBoardingScreen.route) {
                val viewModel : OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(viewModel::onEvent)
            }
        }

        navigation(route = Route.NewNavigation.route,
            startDestination = Route.HomeScreen.route){
            composable(route = Route.HomeScreen.route) {

                Text(text = "This is HomeScreen")

            }
        }
    }
}