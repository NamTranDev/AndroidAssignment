package nam.tran.home.assignment.jetpack.compose.domain.repository

import nam.tran.home.assignment.jetpack.compose.model.response.ProductResponse

interface ProductRepository {
    suspend fun getProductByCategory(category : String?,idFrom : Int) : List<ProductResponse>
}