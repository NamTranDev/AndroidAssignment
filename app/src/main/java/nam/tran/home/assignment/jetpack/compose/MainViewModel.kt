package nam.tran.home.assignment.jetpack.compose

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import nam.tran.home.assignment.jetpack.compose.domain.usecase.OnBoardingUseCase
import nam.tran.home.assignment.jetpack.compose.ui.navigation.Route
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    onBoardingUseCase: OnBoardingUseCase
) : ViewModel() {

    var splashCondition by mutableStateOf(true)
        private set

    var startDestination by mutableStateOf(Route.AppStartNavigation.route)
        private set

    init {
        viewModelScope.launch {
            onBoardingUseCase.readAppOnboarding().collect { shouldStartHomeScreen ->
                startDestination = if (shouldStartHomeScreen){
                    Route.NewNavigation.route
                }else{
                    Route.AppStartNavigation.route
                }
                delay(300)
                splashCondition = false
            }
        }
    }

}