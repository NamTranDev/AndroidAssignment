package nam.tran.home.assignment.jetpack.compose.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import nam.tran.home.assignment.jetpack.compose.fakes.CaseTest
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CaseTestModule {

    @Singleton
    @Provides
    fun provideCaseTest(): CaseTest {
        return CaseTest()
    }
}