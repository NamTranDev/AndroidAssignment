package nam.tran.data.network.api

import nam.tran.data.model.response.CategoryResponse
import retrofit2.http.GET

interface CategoryApi {
    @GET("products/categories")
    suspend fun getCategoryRemote() : List<CategoryResponse>
}