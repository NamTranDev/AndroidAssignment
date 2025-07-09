package nam.tran.home.assignment.jetpack.compose.ui.feature.home.di

import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import nam.tran.di.ProductModule
import nam.tran.domain.repository.ProductRepository
import nam.tran.fake.CaseTest
import nam.tran.fake.FakeProductRepository
import javax.inject.Singleton


@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [ProductModule::class]
)
@Module
class FakeProductListModule {

    @Provides
    @Singleton
    fun provideFakeProductRepository(case : CaseTest): ProductRepository {
        return FakeProductRepository(case)
    }
}
