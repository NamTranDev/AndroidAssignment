package nam.tran.home.assignment.jetpack.compose.data.network.api

import nam.tran.home.assignment.jetpack.compose.model.response.ProductListResponse
import nam.tran.home.assignment.jetpack.compose.model.response.ProductResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductApi {
    @GET("products/category/{category}")
    suspend fun getProductByCategory(
        @Path("category") category: String?,
        @Query("skip") skip: Int,
        @Query("limit") limit: Int = 10,
    ): ProductListResponse
}