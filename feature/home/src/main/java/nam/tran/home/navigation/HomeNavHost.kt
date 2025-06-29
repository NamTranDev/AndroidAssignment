package nam.tran.home.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import nam.tran.home.HomeScreen

fun NavGraphBuilder.HomeGraph(){
    composable(HomeRouter.Main.route){
        HomeScreen()
    }
}