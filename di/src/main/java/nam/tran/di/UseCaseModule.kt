package nam.tran.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import nam.tran.domain.manager.LocalInfoManager
import nam.tran.domain.repository.BookmarkRepository
import nam.tran.domain.repository.CategoryRepository
import nam.tran.domain.repository.ProductRepository
import nam.tran.domain.usecase.LoadBookmarkProductUseCase
import nam.tran.domain.usecase.LoadBookmarkProductUseCaseImpl
import nam.tran.domain.usecase.LoadCategoryUseCase
import nam.tran.domain.usecase.LoadCategoryUseCaseImpl
import nam.tran.domain.usecase.LoadProductByCategoryUseCaseImpl
import nam.tran.domain.usecase.LoadProductByCategoryUsecase
import nam.tran.domain.usecase.LoadProductByQueryUseCaseImpl
import nam.tran.domain.usecase.LoadProductByQueryUsecase
import nam.tran.domain.usecase.OnBoardingUseCase
import nam.tran.domain.usecase.OnBoardingUseCaseImpl
import nam.tran.domain.usecase.ProductDetailUseCase
import nam.tran.domain.usecase.ProductDetailUseCaseImpl
import nam.tran.domain.usecase.ToggleBookmarkProductUseCase
import nam.tran.domain.usecase.ToggleBookmarkProductUseCaseImpl

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