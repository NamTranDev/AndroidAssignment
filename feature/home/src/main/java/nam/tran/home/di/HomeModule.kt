package nam.tran.home.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import nam.tran.di.DataModule
import nam.tran.domain.usecase.LoadProductByCategoryUsecase
import nam.tran.domain.usecase.LoadProductByQueryUsecase
import nam.tran.home.product_list.ProductPagingRepository
import nam.tran.home.product_list.ProductPagingRepositoryImpl
import nam.tran.home.search.ProductSearchPagingRepository
import nam.tran.home.search.ProductSearchPagingRepositoryImpl
import javax.inject.Singleton

@Module(includes = [DataModule::class])
@InstallIn(SingletonComponent::class)
class HomeModule {

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