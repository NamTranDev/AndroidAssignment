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
import nam.tran.home.assignment.jetpack.compose.domain.usecase.LoadBookmarkProductUseCase
import nam.tran.home.assignment.jetpack.compose.domain.usecase.LoadBookmarkProductUseCaseImpl
import nam.tran.home.assignment.jetpack.compose.domain.usecase.LoadCategoryUseCase
import nam.tran.home.assignment.jetpack.compose.domain.usecase.LoadCategoryUseCaseImpl
import nam.tran.home.assignment.jetpack.compose.domain.usecase.LoadProductByCategoryUseCaseImpl
import nam.tran.home.assignment.jetpack.compose.domain.usecase.LoadProductByCategoryUsecase
import nam.tran.home.assignment.jetpack.compose.domain.usecase.LoadProductByQueryUseCaseImpl
import nam.tran.home.assignment.jetpack.compose.domain.usecase.LoadProductByQueryUsecase
import nam.tran.home.assignment.jetpack.compose.domain.usecase.OnBoardingUseCase
import nam.tran.home.assignment.jetpack.compose.domain.usecase.OnBoardingUseCaseImpl
import nam.tran.home.assignment.jetpack.compose.domain.usecase.ProductDetailUseCase
import nam.tran.home.assignment.jetpack.compose.domain.usecase.ProductDetailUseCaseImpl
import nam.tran.home.assignment.jetpack.compose.domain.usecase.ToggleBookmarkProductUseCase
import nam.tran.home.assignment.jetpack.compose.domain.usecase.ToggleBookmarkProductUseCaseImpl
import nam.tran.home.assignment.jetpack.compose.ui.feature.home.product_list.ProductPagingRepository
import nam.tran.home.assignment.jetpack.compose.ui.feature.home.product_list.ProductPagingRepositoryImpl
import nam.tran.home.assignment.jetpack.compose.ui.feature.home.search.ProductSearchPagingRepository
import nam.tran.home.assignment.jetpack.compose.ui.feature.home.search.ProductSearchPagingRepositoryImpl
import javax.inject.Singleton

@Module(includes = [NetModule::class, DatabaseModule::class, ProductModule::class])
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideLocalInfoManager(
        application: Application,
    ): LocalInfoManager = LocalInfoManagerImpl(application)

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
    fun provideLoadCategoryUseCase(
        categoryRepository: CategoryRepository,
    ): LoadCategoryUseCase = LoadCategoryUseCaseImpl(categoryRepository)

    @Provides
    @Singleton
    fun provideLoadProductByCategoryUseCase(
        productRepository: ProductRepository
    ): LoadProductByCategoryUsecase = LoadProductByCategoryUseCaseImpl(productRepository)

    @Provides
    @Singleton
    fun provideLoadProductByQueryUseCase(
        productRepository: ProductRepository
    ): LoadProductByQueryUsecase = LoadProductByQueryUseCaseImpl(productRepository)

    @Provides
    @Singleton
    fun provideLoadBookmarkProductUseCase(
        bookmarRepository : BookmarkRepository
    ): LoadBookmarkProductUseCase = LoadBookmarkProductUseCaseImpl(bookmarRepository)

    @Provides
    @Singleton
    fun provideToggleBookmarkProductUseCase(
        bookmarRepository : BookmarkRepository
    ): ToggleBookmarkProductUseCase = ToggleBookmarkProductUseCaseImpl(bookmarRepository)

    @Provides
    @Singleton
    fun provideProductDetailUseCase(
        productRepository: ProductRepository
    ): ProductDetailUseCase = ProductDetailUseCaseImpl(productRepository)

    @Provides
    @Singleton
    fun provideProductPagingRepository(
        loadProductByCategoryUsecase: LoadProductByCategoryUsecase
    ): ProductPagingRepository = ProductPagingRepositoryImpl(loadProductByCategoryUsecase)

    @Provides
    @Singleton
    fun provideProductSearchPagingRepository(
        loadProductByQueryUsecase: LoadProductByQueryUsecase
    ): ProductSearchPagingRepository = ProductSearchPagingRepositoryImpl(loadProductByQueryUsecase)
}