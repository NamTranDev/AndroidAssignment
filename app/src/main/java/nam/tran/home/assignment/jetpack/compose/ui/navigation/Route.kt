package nam.tran.home.assignment.jetpack.compose.ui.navigation

sealed class Route(
    val route : String
){

    data object OnLoadingScreen : Route(route = "OnLoadingScreen")
    data object OnBoardingScreen : Route(route = "onBoardingScreen")
    data object HomeScreen : Route(route = "homeScreen")
    data object SearchScreen : Route(route = "searchScreen")
    data object BookmarkScreen : Route(route = "bookmarkScreen")
    data object DetailScreen : Route(route = "detailScreen")
}