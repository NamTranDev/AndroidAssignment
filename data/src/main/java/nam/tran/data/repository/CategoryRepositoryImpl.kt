package nam.tran.data.repository

import nam.tran.data.network.api.CategoryApi
import nam.tran.domain.model.entity.CategoryEntity
import nam.tran.domain.repository.CategoryRepository


class CategoryRepositoryImpl(
    private val categoryApi: CategoryApi
): CategoryRepository {
    override suspend fun getCategories(): List<CategoryEntity> {
        return categoryApi.getCategoryRemote().map {
            CategoryEntity(slug = it.slug, name = it.name)
        }
    }

}