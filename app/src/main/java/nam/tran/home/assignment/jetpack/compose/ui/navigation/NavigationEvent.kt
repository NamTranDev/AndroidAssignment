package nam.tran.home.assignment.jetpack.compose.ui.navigation

sealed class NavigationEvent {
//    data object NavigateNone : NavigationEvent()
    data class NavigateToProductDetail(val productId: Int) : NavigationEvent()
}