package nam.tran.home.assignment.jetpack.compose.fakes

import nam.tran.home.assignment.jetpack.compose.domain.repository.CategoryRepository
import nam.tran.home.assignment.jetpack.compose.model.response.CategoryResponse

class FakeCategoryRepository(
    private val case : CaseTest
): CategoryRepository {
    override suspend fun getCategories(): List<CategoryResponse> {
        if (case.isCategorySuccess){
            return listOf(
                CategoryResponse(slug = "category1", name = "Category 1"),
                CategoryResponse(slug = "category2", name = "Category 2")
            )
        }else{
            throw RuntimeException()
        }
    }
}