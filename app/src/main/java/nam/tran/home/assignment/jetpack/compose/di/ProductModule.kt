package nam.tran.home.assignment.jetpack.compose.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import nam.tran.home.assignment.jetpack.compose.data.network.api.CategoryApi
import nam.tran.home.assignment.jetpack.compose.data.network.api.ProductApi
import nam.tran.home.assignment.jetpack.compose.data.repository.CategoryRepositoryImpl
import nam.tran.home.assignment.jetpack.compose.data.repository.ProductRepositoryImpl
import nam.tran.home.assignment.jetpack.compose.domain.repository.CategoryRepository
import nam.tran.home.assignment.jetpack.compose.domain.repository.ProductRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ProductModule {
    @Provides
    @Singleton
    fun provideCategoryRepository(
        categoryApi: CategoryApi,
    ): CategoryRepository = CategoryRepositoryImpl(categoryApi)

    @Provides
    @Singleton
    fun provideProductRepository(
        productApi: ProductApi,
    ): ProductRepository = ProductRepositoryImpl(productApi)
}