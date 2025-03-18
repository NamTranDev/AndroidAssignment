package nam.tran.home.assignment.jetpack.compose.ui.feature.onboarding

sealed class OnBoardingEvent {

    data object SaveOnBoaringEntry : OnBoardingEvent()
}