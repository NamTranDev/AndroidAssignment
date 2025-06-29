package nam.tran.home.assignment.jetpack.compose.viewmodel

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import nam.tran.domain.usecase.OnBoardingUseCase
import nam.tran.home.assignment.jetpack.compose.MainDispatcherRule
import nam.tran.home.assignment.jetpack.compose.SplashViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

@OptIn(ExperimentalCoroutinesApi::class)
class SplashViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private var usecase = Mockito.mock<OnBoardingUseCase>()

    @Before
    fun setup() {}

    @Test
    fun `init should set startDestination to Home when readAppOnboarding is true`() = runTest() {
        Mockito.`when`(usecase.readAppOnboarding()).thenReturn(flowOf(true))
        val viewmodel = SplashViewModel(usecase)
        advanceUntilIdle()
        advanceTimeBy(300)
        assertEquals(Screen.Home.route, viewmodel.startDestination)
        assertFalse(viewmodel.splashCondition)
    }

    @Test
    fun `init should set startDestination to OnBoarding when readAppOnboarding is false`() = runTest {
        Mockito.`when`(usecase.readAppOnboarding()).thenReturn(flowOf(false))

        val viewmodel = SplashViewModel(usecase)
        advanceUntilIdle()
        advanceTimeBy(300)
        assertEquals(Screen.OnBoarding.route, viewmodel.startDestination)
        assertFalse(viewmodel.splashCondition)
    }
}