package nam.tran.navigation

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavigationDispatcher @Inject constructor() {
    private val _navigationEvents = MutableSharedFlow<NavigationEvent>(
        replay = 1,
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val navigationEvents = _navigationEvents.asSharedFlow()

    suspend fun emit(event: NavigationEvent) {
        _navigationEvents.emit(event)
    }

    fun tryEmit(event: NavigationEvent) {
        _navigationEvents.tryEmit(event)
    }
}