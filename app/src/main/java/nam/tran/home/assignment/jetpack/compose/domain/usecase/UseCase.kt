package nam.tran.home.assignment.jetpack.compose.domain.usecase

import kotlinx.coroutines.flow.Flow

interface UseCase<in P, out R> {
    suspend fun execute(params: P): R
}

interface FlowUseCase<in P, out R> {
    fun execute(params: P): Flow<R>
}