package nam.tran.home.assignment.jetpack.compose.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.EntryPointAccessors
import nam.tran.home.navigation.HomeGraph
import nam.tran.navigation.NavigationDispatcherEntryPoint
import nam.tran.navigation.NavigationEvent
import nam.tran.onboarding.navigation.OnBoardingGraph
import nam.tran.product_detail.navigation.ProductDetailGraph
import nam.tran.product_detail.navigation.ProductDetailRouter

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
                    navController.navigate(ProductDetailRouter.ProductDetail.routeWithArgs(event.productId))
                }
            }

        }
    }

    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = "loading") {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator()
            }
        }

        OnBoardingGraph()

        HomeGraph()

        ProductDetailGraph(navController)
    }
}