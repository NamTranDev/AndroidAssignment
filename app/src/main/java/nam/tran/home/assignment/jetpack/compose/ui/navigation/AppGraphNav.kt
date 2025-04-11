package nam.tran.home.assignment.jetpack.compose.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import nam.tran.home.assignment.jetpack.compose.ui.feature.home.HomeScreen
import nam.tran.home.assignment.jetpack.compose.ui.feature.onboarding.OnBoardingViewModel
import nam.tran.home.assignment.jetpack.compose.ui.feature.onboarding.OnBoardingScreen

@Composable
fun AppGraphNav(
    startDestination : String
) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = Screen.Loading.route) {
            Box(modifier = Modifier.fillMaxSize()){
                CircularProgressIndicator()
            }
        }
        composable(route = Screen.OnBoarding.route) {
            val viewModel : OnBoardingViewModel = hiltViewModel()
            OnBoardingScreen(viewModel::onEvent)
        }

        composable(route = Screen.Home.route) {
            HomeScreen()
        }
    }
}