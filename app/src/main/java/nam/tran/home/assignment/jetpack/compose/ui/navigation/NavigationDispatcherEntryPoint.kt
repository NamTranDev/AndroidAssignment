package nam.tran.home.assignment.jetpack.compose.ui.navigation

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface NavigationDispatcherEntryPoint {
    fun navigationDispatcher(): NavigationDispatcher
}