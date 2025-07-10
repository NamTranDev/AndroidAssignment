package nam.tran.data.network.api

import nam.tran.data.model.response.ProductDetailResponse
import nam.tran.data.model.response.ProductListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductApi {
    @GET("products/category/{category}")
    suspend fun getProductByCategory(
        @Path("category") category: String?,
        @Query("skip") offset: Int,
        @Query("limit") limit: Int = 10,
    ): ProductListResponse

    @GET("products/search")
    suspend fun searchProduct(
        @Query("q") query: String?,
        @Query("skip") offset: Int,
        @Query("limit") limit: Int = 10,
    ): ProductListResponse

    @GET("products/{id}")
    suspend fun productDetail(
        @Path("id") id: String?,
    ): ProductDetailResponse
}