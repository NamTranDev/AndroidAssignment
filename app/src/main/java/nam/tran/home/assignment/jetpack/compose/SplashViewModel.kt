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
import nam.tran.home.navigation.HomeRouter
import nam.tran.onboarding.navigation.OnBoardingRouter
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    onBoardingUseCase: OnBoardingUseCase
) : ViewModel() {

    var splashCondition by mutableStateOf(true)
    var startDestination by mutableStateOf("loading")

    init {
        viewModelScope.launch {
            onBoardingUseCase.readAppOnboarding().collect { shouldStartHomeScreen ->
                startDestination = if (shouldStartHomeScreen){
                    HomeRouter.Main.route
                }else{
                    OnBoardingRouter.OnBoarding.route
                }
                delay(300)
                splashCondition = false
            }
        }
    }

}