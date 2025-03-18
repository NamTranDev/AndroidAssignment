package nam.tran.home.assignment.jetpack.compose.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import nam.tran.home.assignment.jetpack.compose.data.manager.LocalInfoManagerImpl
import nam.tran.home.assignment.jetpack.compose.domain.manager.LocalInfoManager
import nam.tran.home.assignment.jetpack.compose.domain.usecase.OnBoardingUseCase
import nam.tran.home.assignment.jetpack.compose.domain.usecase.OnBoardingUseCaseImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideLocalInfoManager(
        application : Application
    ) : LocalInfoManager = LocalInfoManagerImpl(application)

    @Provides
    @Singleton
    fun provideOnBoardingUseCase(
        localInfoManager: LocalInfoManager
    ) : OnBoardingUseCase = OnBoardingUseCaseImpl(localInfoManager)
}