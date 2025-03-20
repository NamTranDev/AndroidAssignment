package nam.tran.home.assignment.jetpack.compose.data.repository

import nam.tran.home.assignment.jetpack.compose.data.network.api.CategoryApi
import nam.tran.home.assignment.jetpack.compose.domain.repository.ProductRepository
import nam.tran.home.assignment.jetpack.compose.model.response.CategoryResponse
import nam.tran.home.assignment.jetpack.compose.data.network.api.ProductApi
import nam.tran.home.assignment.jetpack.compose.domain.repository.CategoryRepository

class CategoryRepositoryImpl(
    private val categoryApi: CategoryApi
): CategoryRepository{
    override suspend fun getCategories(): List<CategoryResponse> {
        return categoryApi.getCategoryRemote()
    }

}