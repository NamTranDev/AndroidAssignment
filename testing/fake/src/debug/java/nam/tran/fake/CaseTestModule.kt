package nam.tran.fake

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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