package nam.tran.home.assignment.jetpack.compose.ui.navigation

import nam.tran.home.assignment.jetpack.compose.R

sealed class Screen(
    val route : String
){

    data object Loading : Screen(route = "loading")
    data object OnBoarding : Screen(route = "onboard")
    data object Home : Screen(route = "home")
    data object Search : Screen(route = "search")
    data object About : Screen(route = "about")
    data class ProductDetail(val productId: String) : Screen("detail/$productId")
}

sealed class Tab(val tab: String, val title: Int, val icon: Int) {
    data object ProductList : Tab("${Screen.Home.route}/product_list", R.string.product_list_tab_title, R.drawable.ic_home)
    data object Bookmark : Tab("${Screen.Home.route}/bookmark", R.string.bookmark_tab_title, R.drawable.ic_bookmarks)
    data object Profile : Tab("${Screen.Home.route}/profile", R.string.profile_tab_title, R.drawable.ic_profile)
}