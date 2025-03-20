package nam.tran.home.assignment.jetpack.compose.data.network.api

import nam.tran.home.assignment.jetpack.compose.model.response.CategoryResponse
import retrofit2.http.GET

interface CategoryApi {
    @GET("products/categories")
    suspend fun getCategoryRemote() : List<CategoryResponse>
}