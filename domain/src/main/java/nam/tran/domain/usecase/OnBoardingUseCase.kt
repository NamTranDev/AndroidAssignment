package nam.tran.domain.usecase

import kotlinx.coroutines.flow.Flow
import nam.tran.domain.manager.LocalInfoManager

interface OnBoardingUseCase {
    suspend fun saveAppOnboarding()
    suspend fun readAppOnboarding() : Flow<Boolean>
}

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