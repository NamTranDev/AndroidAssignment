package nam.tran.home.assignment.jetpack.compose.domain.usecase

import kotlinx.coroutines.flow.Flow
import nam.tran.home.assignment.jetpack.compose.domain.manager.LocalInfoManager

class OnBoardingUseCaseImpl(
    private val localManager : LocalInfoManager
) : OnBoardingUseCase{
    override suspend fun saveAppOnboarding() {
        localManager.saveAppOnBoarding()
    }

    override suspend fun readAppOnboarding(): Flow<Boolean> {
        return localManager.readAppOnBoarding()
    }

}