package nam.tran.product_detail.navigation

sealed class ProductDetailRouter(val route : String){
    data object ProductDetail : ProductDetailRouter(route = "productDetail/main/{productId}")
}
