package nam.tran.home.assignment.jetpack.compose.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.EntryPointAccessors
import nam.tran.home.navigation.HomeNavHost
import nam.tran.navigation.NavigationDispatcherEntryPoint
import nam.tran.navigation.NavigationEvent
import nam.tran.onboarding.navigation.OnboardingNavHost
import nam.tran.product_detail.navigation.ProductDetailNavHost
import nam.tran.utils.Logger

@Composable
fun AppGraphNav(
    startDestination: String
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
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator()
            }
        }
        composable(route = Screen.OnBoarding.route) {
            OnboardingNavHost(navController)
        }

        composable(route = Screen.Home.route) {
            HomeNavHost(navController)
        }

        composable(
            route = Screen.ProductDetail.route,
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId") ?: return@composable
            Logger.debug(productId)
            ProductDetailNavHost(rootNavHost = navController, productId = productId.toString())
        }
    }
}