package nam.tran.home.navigation

import nam.tran.home.R

sealed class HomeRouter(val route : String){
    data object Main : HomeRouter(route = "home/main")
    data object Search : HomeRouter(route = "home/search")
}

sealed class Tab(val tab: String, val title: Int, val icon: Int) {
    data object ProductList : Tab("${HomeRouter.Main.route}/product_list", R.string.product_list_tab_title, R.drawable.ic_home)
    data object Bookmark : Tab("${HomeRouter.Main.route}/bookmark", R.string.bookmark_tab_title, R.drawable.ic_bookmarks)
    data object Profile : Tab("${HomeRouter.Main.route}/profile", R.string.profile_tab_title, R.drawable.ic_profile)
}
