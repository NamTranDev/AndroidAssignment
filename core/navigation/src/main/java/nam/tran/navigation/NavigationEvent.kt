package nam.tran.navigation

sealed class NavigationEvent {
    data class NavigateToProductDetail(val productId: Int) : NavigationEvent()
}