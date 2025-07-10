package nam.tran.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import nam.tran.data.network.api.ProductApi
import nam.tran.data.repository.ProductRepositoryImpl
import nam.tran.domain.repository.ProductRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ProductModule {
    @Provides
    @Singleton
    fun provideProductRepository(
        productApi: ProductApi,
    ): ProductRepository = ProductRepositoryImpl(productApi)
}