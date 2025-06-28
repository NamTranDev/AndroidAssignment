package nam.tran.home.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import nam.tran.home.bookmark.BookmarkTabScreen
import nam.tran.home.product_list.ProductListTabScreen
import nam.tran.home.profile.ProfileScreenTab
import nam.tran.home.search.SearchProductTabScreen

@Composable
fun HomeGraphNav(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Tab.ProductList.tab,
    ) {
        composable(Tab.ProductList.tab) {
            ProductListTabScreen {
                navController.navigate(HomeRouter.Search.route)
            }
        }
        composable(Tab.Bookmark.tab) {
            BookmarkTabScreen()
        }
        composable(Tab.Profile.tab) {
            ProfileScreenTab()
        }
        composable(HomeRouter.Search.route) {
            SearchProductTabScreen {
                navController.popBackStack()
            }
        }
    }
}