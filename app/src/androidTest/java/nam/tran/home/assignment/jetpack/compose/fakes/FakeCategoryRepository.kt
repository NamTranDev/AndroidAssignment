package nam.tran.home.assignment.jetpack.compose.fakes

import nam.tran.home.assignment.jetpack.compose.domain.repository.CategoryRepository
import nam.tran.home.assignment.jetpack.compose.model.response.CategoryResponse

class FakeCategoryRepository : CategoryRepository {
    override suspend fun getCategories(): List<CategoryResponse> {
        return listOf(
            CategoryResponse(slug = "category1", name = "Category 1"),
            CategoryResponse(slug = "category2", name = "Category 2")
        )
    }
}