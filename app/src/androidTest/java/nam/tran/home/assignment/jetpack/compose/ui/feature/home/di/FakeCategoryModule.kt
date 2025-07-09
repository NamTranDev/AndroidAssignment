package nam.tran.home.assignment.jetpack.compose.ui.feature.home.di

import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import nam.tran.di.CategoryModule
import nam.tran.domain.repository.CategoryRepository
import nam.tran.fake.CaseTest
import nam.tran.fake.FakeCategoryRepository
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
