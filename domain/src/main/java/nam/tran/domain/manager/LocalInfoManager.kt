package nam.tran.domain.manager

import kotlinx.coroutines.flow.Flow

interface LocalInfoManager{
    suspend fun saveAppOnBoarding()
    fun readAppOnBoarding() : Flow<Boolean>
}