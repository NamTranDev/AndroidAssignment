package nam.tran.home.assignment.jetpack.compose.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import nam.tran.home.assignment.jetpack.compose.data.local.LocalInfoManagerImpl
import nam.tran.home.assignment.jetpack.compose.data.network.NetModule
import nam.tran.home.assignment.jetpack.compose.data.network.api.CategoryApi
import nam.tran.home.assignment.jetpack.compose.data.network.api.ProductApi
import nam.tran.home.assignment.jetpack.compose.data.repository.CategoryRepositoryImpl
import nam.tran.home.assignment.jetpack.compose.data.repository.ProductRepositoryImpl
import nam.tran.home.assignment.jetpack.compose.domain.manager.LocalInfoManager
import nam.tran.home.assignment.jetpack.compose.domain.repository.CategoryRepository
import nam.tran.home.assignment.jetpack.compose.domain.repository.ProductRepository
import nam.tran.home.assignment.jetpack.compose.domain.usecase.HomeUseCase
import nam.tran.home.assignment.jetpack.compose.domain.usecase.HomeUseCaseImpl
import nam.tran.home.assignment.jetpack.compose.domain.usecase.OnBoardingUseCase
import nam.tran.home.assignment.jetpack.compose.domain.usecase.OnBoardingUseCaseImpl
import javax.inject.Singleton

@Module(includes = [NetModule::class])
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
        categoryApi: CategoryApi
    ): CategoryRepository = CategoryRepositoryImpl(categoryApi)

    @Provides
    @Singleton
    fun provideProductRepository(
        productApi: ProductApi
    ): ProductRepository = ProductRepositoryImpl(productApi)

    @Provides
    @Singleton
    fun provideOnBoardingUseCase(
        localInfoManager: LocalInfoManager,
    ): OnBoardingUseCase = OnBoardingUseCaseImpl(localInfoManager)

    @Provides
    @Singleton
    fun provideHomeUseCase(
        categoryRepository: CategoryRepository,
        productRepository: ProductRepository,
    ): HomeUseCase = HomeUseCaseImpl(categoryRepository, productRepository)
}