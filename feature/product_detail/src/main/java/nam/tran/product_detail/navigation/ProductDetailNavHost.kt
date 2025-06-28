package nam.tran.product_detail.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import nam.tran.product_detail.ProductDetailScreen

@Composable
fun ProductDetailNavHost(rootNavHost : NavHostController,productId: String) {
    val navController = rememberNavController()

    fun NavHostController.canNavigateBack(): Boolean {
        return this.previousBackStackEntry != null
    }

    NavHost(
        navController = navController,
        startDestination = ProductDetailRouter.ProductDetail.route
    ) {
        composable(ProductDetailRouter.ProductDetail.route) {
                ProductDetailScreen(productId) {
                    if (navController.canNavigateBack())
                        navController.popBackStack()
                    else
                        rootNavHost.popBackStack()
                }
            }
    }
}