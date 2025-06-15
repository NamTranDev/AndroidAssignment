package nam.tran.home.assignment.jetpack.compose.di

import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import nam.tran.di.CategoryModule
import nam.tran.domain.repository.CategoryRepository
import nam.tran.home.assignment.jetpack.compose.fakes.CaseTest
import nam.tran.home.assignment.jetpack.compose.fakes.FakeCategoryRepository
import javax.inject.Singleton

@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [CategoryModule::class]
)
@Module
class FakeCategoryModule {

    @Provides
    @Singleton
    fun provideFakeCategoryRepository(case : CaseTest): CategoryRepository {
        return FakeCategoryRepository(case)
    }
}
