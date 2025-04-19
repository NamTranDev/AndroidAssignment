package nam.tran.home.assignment.jetpack.compose.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import nam.tran.home.assignment.jetpack.compose.data.local.LocalInfoManagerImpl
import nam.tran.home.assignment.jetpack.compose.data.local.db.ProductDao
import nam.tran.home.assignment.jetpack.compose.data.network.NetModule
import nam.tran.home.assignment.jetpack.compose.data.network.api.CategoryApi
import nam.tran.home.assignment.jetpack.compose.data.network.api.ProductApi
import nam.tran.home.assignment.jetpack.compose.data.repository.BookmarkRepositoryImpl
import nam.tran.home.assignment.jetpack.compose.data.repository.CategoryRepositoryImpl
import nam.tran.home.assignment.jetpack.compose.data.repository.ProductRepositoryImpl
import nam.tran.home.assignment.jetpack.compose.domain.manager.LocalInfoManager
import nam.tran.home.assignment.jetpack.compose.domain.repository.BookmarkRepository
import nam.tran.home.assignment.jetpack.compose.domain.repository.CategoryRepository
import nam.tran.home.assignment.jetpack.compose.domain.repository.ProductRepository
import nam.tran.home.assignment.jetpack.compose.domain.usecase.HomeUseCase
import nam.tran.home.assignment.jetpack.compose.domain.usecase.HomeUseCaseImpl
import nam.tran.home.assignment.jetpack.compose.domain.usecase.OnBoardingUseCase
import nam.tran.home.assignment.jetpack.compose.domain.usecase.OnBoardingUseCaseImpl
import nam.tran.home.assignment.jetpack.compose.ui.feature.home.product_list.ProductPagingRepository
import nam.tran.home.assignment.jetpack.compose.ui.feature.home.search.ProductSearchPagingRepository
import javax.inject.Singleton

@Module(includes = [NetModule::class,DatabaseModule::class])
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideLocalInfoManager(
        application: Application,
    ): LocalInfoManager = LocalInfoManagerImpl(application)

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

    @Provides
    @Singleton
    fun provideBookmarkRepository(
        productDao: ProductDao
    ): BookmarkRepository = BookmarkRepositoryImpl(productDao)

    @Provides
    @Singleton
    fun provideOnBoardingUseCase(
        localInfoManager: LocalInfoManager,
    ): OnBoardingUseCase = OnBoardingUseCaseImpl(localInfoManager)

    @Provides
    @Singleton
    fun provideHomeUseCase(
        categoryRepository: CategoryRepository,
    ): HomeUseCase = HomeUseCaseImpl(categoryRepository)

    @Provides
    @Singleton
    fun provideProductPagingRepository(
        productRepository: ProductRepository
    ): ProductPagingRepository = ProductPagingRepository(productRepository)

    @Provides
    @Singleton
    fun provideProductSearchPagingRepository(
        productRepository: ProductRepository
    ): ProductSearchPagingRepository = ProductSearchPagingRepository(productRepository)
}