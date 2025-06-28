package nam.tran.onboarding.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import nam.tran.onboarding.OnBoardingScreen

@Composable
fun OnboardingNavHost(rootNavHost : NavHostController,){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = OnBoardingRouter.OnBoarding.route
    ){
        composable(OnBoardingRouter.OnBoarding.route){
            OnBoardingScreen()
        }
    }
}