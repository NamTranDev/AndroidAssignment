package nam.tran.home.assignment.jetpack.compose.di

import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import nam.tran.home.assignment.jetpack.compose.domain.repository.ProductRepository
import nam.tran.home.assignment.jetpack.compose.fakes.CaseTest
import nam.tran.home.assignment.jetpack.compose.fakes.FakeProductRepository
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
