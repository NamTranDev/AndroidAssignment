package nam.tran.domain.repository

import nam.tran.domain.model.entity.CategoryEntity

interface CategoryRepository {
    suspend fun getCategories() : List<CategoryEntity>
}