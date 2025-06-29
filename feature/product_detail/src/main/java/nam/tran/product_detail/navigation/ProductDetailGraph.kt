package nam.tran.product_detail.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import nam.tran.product_detail.ProductDetailScreen

fun NavGraphBuilder.ProductDetailGraph(navController: NavHostController){
    composable(ProductDetailRouter.ProductDetail.route){
        ProductDetailScreen{
            navController.popBackStack()
        }
    }
}