package nam.tran.home.assignment.jetpack.compose.di

import androidx.test.espresso.core.internal.deps.dagger.Provides
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import nam.tran.home.assignment.jetpack.compose.domain.repository.CategoryRepository
import nam.tran.home.assignment.jetpack.compose.domain.repository.ProductRepository
import nam.tran.home.assignment.jetpack.compose.fakes.FakeCategoryRepository
import nam.tran.home.assignment.jetpack.compose.fakes.FakeProductRepository
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [ProductModule::class]
)
class FakeProductListModule {

    @Provides
    @Singleton
    fun provideFakeCategoryRepository(): CategoryRepository {
        return FakeCategoryRepository()
    }

    @Provides
    @Singleton
    fun provideFakeProductRepository(): ProductRepository {
        return FakeProductRepository()
    }
}
