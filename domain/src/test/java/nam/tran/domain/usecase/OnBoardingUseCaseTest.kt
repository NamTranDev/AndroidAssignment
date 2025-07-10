package nam.tran.domain.usecase

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import nam.tran.domain.manager.LocalInfoManager
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

class OnBoardingUseCaseTest {
    private lateinit var usecase: OnBoardingUseCaseImpl
    private val localManager = mock<LocalInfoManager>()

    @Before
    fun setup() {
        usecase = OnBoardingUseCaseImpl(localManager)
    }

    @Test
    fun `saveAppOnboarding should call localManager`() = runTest {
        usecase.saveAppOnboarding()
        verify(localManager).saveAppOnBoarding()
    }

    @Test
    fun `readAppOnboarding should return flow from localManager`() = runTest {
        val flow = flowOf(true)
        `when`(localManager.readAppOnBoarding()).thenReturn(flow)

        val result = usecase.readAppOnboarding().first()

        assertTrue(result)
    }
}