package nam.tran.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import nam.tran.data.network.api.CategoryApi
import nam.tran.data.repository.CategoryRepositoryImpl
import nam.tran.domain.repository.CategoryRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CategoryModule {
    @Provides
    @Singleton
    fun provideCategoryRepository(
        categoryApi: CategoryApi,
    ): CategoryRepository = CategoryRepositoryImpl(categoryApi)
}