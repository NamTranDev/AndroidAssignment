package nam.tran.home.assignment.jetpack.compose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import nam.tran.home.assignment.jetpack.compose.ui.feature.home.bookmark.BookmarkScreenTab
import nam.tran.home.assignment.jetpack.compose.ui.feature.home.product_list.ProductListTabScreen
import nam.tran.home.assignment.jetpack.compose.ui.feature.home.product_list.ProductListViewModel
import nam.tran.home.assignment.jetpack.compose.ui.feature.home.profile.ProfileScreenTab
import nam.tran.home.assignment.jetpack.compose.ui.feature.home.search.SearchScreen
import nam.tran.home.assignment.jetpack.compose.ui.feature.home.search.SearchViewModel

@Composable
fun HomeGraphNav(navController : NavHostController){
    NavHost(
        navController = navController,
        startDestination = Tab.ProductList.tab,
    ) {
        composable(Tab.ProductList.tab) {
            val viewModel: ProductListViewModel = hiltViewModel()
            ProductListTabScreen(viewModel,{
                navController.navigate(Screen.Search.route)
            })
        }
        composable(Tab.Bookmark.tab) {
            BookmarkScreenTab()
        }
        composable(Tab.Profile.tab) {
            ProfileScreenTab()
        }
        composable(Screen.Search.route) {
            val viewModel: SearchViewModel = hiltViewModel()
            SearchScreen(viewModel)
        }
    }
}