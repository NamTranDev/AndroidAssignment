package nam.tran.onboarding.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import nam.tran.onboarding.OnBoardingScreen

fun NavGraphBuilder.OnBoardingGraph(){
    composable(OnBoardingRouter.OnBoarding.route){
        OnBoardingScreen()
    }
}