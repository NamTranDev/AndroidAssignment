package nam.tran.home.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import nam.tran.home.HomeScreen

@Composable
fun HomeNavHost(rootNavHost : NavHostController){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = HomeRouter.Main.route
    ){
        composable(HomeRouter.Main.route){
            HomeScreen()
        }
    }
}