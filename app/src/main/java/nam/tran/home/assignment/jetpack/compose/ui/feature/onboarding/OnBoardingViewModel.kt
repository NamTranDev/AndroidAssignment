package nam.tran.home.assignment.jetpack.compose.ui.feature.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import nam.tran.domain.usecase.OnBoardingUseCase
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val onBoardingUseCase: OnBoardingUseCase
) : ViewModel() {

    fun saveOnBoaringEntry() {
        viewModelScope.launch {
            onBoardingUseCase.saveAppOnboarding()
        }
    }
}