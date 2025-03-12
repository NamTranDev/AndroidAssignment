package nam.tran.home.assignment.jetpack.compose.domain.usecase

import kotlinx.coroutines.flow.Flow

interface OnBoardingUseCase {
    suspend fun saveAppOnboarding()
    suspend fun readAppOnboarding() : Flow<Boolean>
}