package nam.tran.home.assignment.jetpack.compose.ui.navigation

sealed class Route(
    val route : String
){

    data object OnBoardingScreen : Route(route = "onBoardingScreen")
    data object HomeScreen : Route(route = "homeScreen")
    data object SearchScreen : Route(route = "searchScreen")
    data object BookmarkScreen : Route(route = "bookmarkScreen")
    data object DetailScreen : Route(route = "detailScreen")
    data object AppStartNavigation : Route(route = "appStartNavigation")
    data object NewNavigation : Route(route = "newNavigation")
}