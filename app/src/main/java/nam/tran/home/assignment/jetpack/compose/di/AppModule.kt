package nam.tran.home.assignment.jetpack.compose.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import nam.tran.di.DataModule
import nam.tran.domain.usecase.LoadProductByCategoryUsecase
import nam.tran.domain.usecase.LoadProductByQueryUsecase
import nam.tran.home.assignment.jetpack.compose.ui.feature.home.product_list.ProductPagingRepository
import nam.tran.home.assignment.jetpack.compose.ui.feature.home.product_list.ProductPagingRepositoryImpl
import nam.tran.home.assignment.jetpack.compose.ui.feature.home.search.ProductSearchPagingRepository
import nam.tran.home.assignment.jetpack.compose.ui.feature.home.search.ProductSearchPagingRepositoryImpl
import javax.inject.Singleton

@Module(includes = [DataModule::class])
@InstallIn(SingletonComponent::class)
class AppModule {

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