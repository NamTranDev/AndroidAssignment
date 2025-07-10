package nam.tran.home.assignment.jetpack.compose.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
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
}