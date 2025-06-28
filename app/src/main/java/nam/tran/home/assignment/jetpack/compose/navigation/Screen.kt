package nam.tran.home.assignment.jetpack.compose.navigation

sealed class Screen(
    val route : String
){
    data object Loading : Screen(route = "loading")
    data object OnBoarding : Screen(route = "onboard")
    data object Home : Screen(route = "home")
    data object ProductDetail : Screen(route = "detail/{productId}") {
        fun routeWithArgs(productId: Int) = "detail/$productId"
    }
}