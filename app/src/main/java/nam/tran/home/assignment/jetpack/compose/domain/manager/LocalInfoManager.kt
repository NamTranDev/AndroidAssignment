package nam.tran.home.assignment.jetpack.compose.domain.manager

import kotlinx.coroutines.flow.Flow

interface LocalInfoManager{
    suspend fun saveAppOnBoarding()
    fun readAppOnBoarding() : Flow<Boolean>
}