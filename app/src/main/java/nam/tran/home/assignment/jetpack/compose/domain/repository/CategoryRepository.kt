package nam.tran.home.assignment.jetpack.compose.domain.repository

import nam.tran.home.assignment.jetpack.compose.model.response.CategoryResponse

interface CategoryRepository {
    suspend fun getCategories() : List<CategoryResponse>
}