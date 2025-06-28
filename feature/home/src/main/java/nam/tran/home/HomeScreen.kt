package nam.tran.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import nam.tran.home.navigation.HomeGraphNav
import nam.tran.home.navigation.Tab.Bookmark
import nam.tran.home.navigation.Tab.ProductList
import nam.tran.home.navigation.Tab.Profile
import nam.tran.resource.theme.AppTheme

@Composable
fun HomeScreen(viewModel: TabStateViewModel = hiltViewModel()) {
    val navController = rememberNavController()
    val currentTab = viewModel.currentTab.value
    Scaffold(
        bottomBar = {
            BottomNavigation(backgroundColor = MaterialTheme.colorScheme.background) {
                listOf(ProductList, Bookmark, Profile).forEach { screen ->
                    BottomNavigationItem(
                        selected = currentTab == screen.tab,
                        onClick = {
                            viewModel.selectTab(screen.tab)
                            navController.navigate(screen.tab) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                painter = painterResource(screen.icon),
                                contentDescription = screen.tab,
                                tint = colorResource(if (currentTab == screen.tab) nam.tran.resource.R.color.icon_bottom_bar_select else nam.tran.resource.R.color.icon_bottom_bar_unselect)
                            )
                        },
//                        label = { Text(LocalContext.current.getString(screen.title)) }
                    )
                }
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            HomeGraphNav(navController)
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    AppTheme {
//        HomeScreen()
    }
}