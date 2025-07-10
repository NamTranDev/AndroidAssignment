package nam.tran.home.assignment.jetpack.compose.viewmodel

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import nam.tran.home.assignment.jetpack.compose.domain.usecase.OnBoardingUseCase
import nam.tran.home.assignment.jetpack.compose.ui.feature.onboarding.OnBoardingViewModel
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

@OptIn(ExperimentalCoroutinesApi::class)
class OnBoardingViewModelTest {
    private lateinit var viewmodel : OnBoardingViewModel
    private var usecase = Mockito.mock<OnBoardingUseCase>()

    @Before
    fun setup(){
        viewmodel = OnBoardingViewModel(usecase)
    }

    @Test
    fun `saveOnBoaringEntry should call usecase`() = runTest {
        viewmodel.saveOnBoaringEntry()

        advanceUntilIdle()

        Mockito.verify(usecase).saveAppOnboarding()
    }
}