package nam.tran.home.assignment.jetpack.compose.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import nam.tran.home.assignment.jetpack.compose.data.local.LocalInfoManagerImpl
import nam.tran.home.assignment.jetpack.compose.data.local.db.ProductDao
import nam.tran.home.assignment.jetpack.compose.data.network.NetModule
import nam.tran.home.assignment.jetpack.compose.data.repository.BookmarkRepositoryImpl
import nam.tran.home.assignment.jetpack.compose.domain.manager.LocalInfoManager
import nam.tran.home.assignment.jetpack.compose.domain.repository.BookmarkRepository
import nam.tran.home.assignment.jetpack.compose.domain.usecase.LoadProductByCategoryUsecase
import nam.tran.home.assignment.jetpack.compose.domain.usecase.LoadProductByQueryUsecase
import nam.tran.home.assignment.jetpack.compose.ui.feature.home.product_list.ProductPagingRepository
import nam.tran.home.assignment.jetpack.compose.ui.feature.home.product_list.ProductPagingRepositoryImpl
import nam.tran.home.assignment.jetpack.compose.ui.feature.home.search.ProductSearchPagingRepository
import nam.tran.home.assignment.jetpack.compose.ui.feature.home.search.ProductSearchPagingRepositoryImpl
import javax.inject.Singleton

@Module(includes = [NetModule::class, DatabaseModule::class])
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
    fun provideProductPagingRepository(
        loadProductByCategoryUsecase: LoadProductByCategoryUsecase
    ): ProductPagingRepository = ProductPagingRepositoryImpl(loadProductByCategoryUsecase)

    @Provides
    @Singleton
    fun provideProductSearchPagingRepository(
        loadProductByQueryUsecase: LoadProductByQueryUsecase
    ): ProductSearchPagingRepository = ProductSearchPagingRepositoryImpl(loadProductByQueryUsecase)
}