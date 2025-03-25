package nam.tran.home.assignment.jetpack.compose.domain.usecase

import kotlinx.coroutines.flow.Flow
import nam.tran.home.assignment.jetpack.compose.model.response.CategoryResponse
import nam.tran.home.assignment.jetpack.compose.model.response.ProductResponse

interface HomeUseCase {
    fun loadCategories() : Flow<List<CategoryResponse>>
}