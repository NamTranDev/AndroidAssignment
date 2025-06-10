package nam.tran.home.assignment.jetpack.compose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import nam.tran.home.assignment.jetpack.compose.ui.feature.home.bookmark.BookmarkTabScreen
import nam.tran.home.assignment.jetpack.compose.ui.feature.home.product_list.ProductListTabScreen
import nam.tran.home.assignment.jetpack.compose.ui.feature.home.profile.ProfileScreenTab
import nam.tran.home.assignment.jetpack.compose.ui.feature.home.search.SearchProductTabScreen

@Composable
fun HomeGraphNav(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Tab.ProductList.tab,
    ) {
        composable(Tab.ProductList.tab) {
            ProductListTabScreen {
                navController.navigate(Screen.Search.route)
            }
        }
        composable(Tab.Bookmark.tab) {
            BookmarkTabScreen()
        }
        composable(Tab.Profile.tab) {
            ProfileScreenTab()
        }
        composable(Screen.Search.route) {
            SearchProductTabScreen {
                navController.popBackStack()
            }
        }
    }
}