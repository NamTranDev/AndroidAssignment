package nam.tran.home.assignment.jetpack.compose.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.EntryPointAccessors
import io.lifestyle.plus.utils.Logger
import nam.tran.home.assignment.jetpack.compose.ui.feature.detail.ProductDetailScreen
import nam.tran.home.assignment.jetpack.compose.ui.feature.home.HomeScreen
import nam.tran.home.assignment.jetpack.compose.ui.feature.onboarding.OnBoardingViewModel
import nam.tran.home.assignment.jetpack.compose.ui.feature.onboarding.OnBoardingScreen

@Composable
fun AppGraphNav(
    startDestination : String
) {

    val navController = rememberNavController()

    val dispatcher = EntryPointAccessors.fromApplication(
        LocalContext.current.applicationContext,
        NavigationDispatcherEntryPoint::class.java
    ).navigationDispatcher()

    LaunchedEffect(Unit) {
        dispatcher.navigationEvents.collect { event ->

            when (event) {

                is NavigationEvent.NavigateToProductDetail -> {
                    navController.navigate(Screen.ProductDetail.routeWithArgs(event.productId))
                }
            }

        }
    }

    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = Screen.Loading.route) {
            Box(modifier = Modifier.fillMaxSize()){
                CircularProgressIndicator()
            }
        }
        composable(route = Screen.OnBoarding.route) {
            OnBoardingScreen()
        }

        composable(route = Screen.Home.route) {
            HomeScreen()
        }

        composable(route = Screen.ProductDetail.route) {
            ProductDetailScreen{
                navController.popBackStack()
            }
        }
    }
}