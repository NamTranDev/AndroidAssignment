package nam.tran.onboarding.navigation

sealed class OnBoardingRouter(val route : String){
    data object OnBoarding : OnBoardingRouter(route = "onboard/main")
}
