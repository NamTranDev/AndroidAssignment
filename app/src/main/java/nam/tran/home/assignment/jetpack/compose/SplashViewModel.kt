package nam.tran.home.assignment.jetpack.compose

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import nam.tran.domain.usecase.OnBoardingUseCase
import nam.tran.home.assignment.jetpack.compose.ui.navigation.Screen
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    onBoardingUseCase: OnBoardingUseCase
) : ViewModel() {

    var splashCondition by mutableStateOf(true)
    var startDestination by mutableStateOf(Screen.Loading.route)

    init {
        viewModelScope.launch {
            onBoardingUseCase.readAppOnboarding().collect { shouldStartHomeScreen ->
                startDestination = if (shouldStartHomeScreen){
                    Screen.Home.route
                }else{
                    Screen.OnBoarding.route
                }
                delay(300)
                splashCondition = false
            }
        }
    }

}