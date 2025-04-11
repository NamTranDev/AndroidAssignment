package nam.tran.home.assignment.jetpack.compose.ui.feature.home

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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import nam.tran.home.assignment.jetpack.compose.R
import nam.tran.home.assignment.jetpack.compose.ui.feature.home.bookmark.BookmarkScreenTab
import nam.tran.home.assignment.jetpack.compose.ui.feature.home.product_list.ProductListScreenTab
import nam.tran.home.assignment.jetpack.compose.ui.feature.home.product_list.ProductListViewModel
import nam.tran.home.assignment.jetpack.compose.ui.feature.home.search.SearchScreenTab
import nam.tran.home.assignment.jetpack.compose.ui.navigation.Tab
import nam.tran.home.assignment.jetpack.compose.ui.navigation.Tab.Companion.bottomNavHomeItems
import nam.tran.home.assignment.jetpack.compose.ui.theme.JetpackComposeHomeAssignmentTheme

@Composable
fun HomeScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigation(backgroundColor = MaterialTheme.colorScheme.background) {
                val currentRoute =
                    navController.currentBackStackEntryAsState().value?.destination?.route
                bottomNavHomeItems.forEach { screen ->
                    BottomNavigationItem(
                        selected = currentRoute == screen.tab,
                        onClick = {
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
                                tint =  colorResource(if (currentRoute == screen.tab) R.color.icon_bottom_bar_select else R.color.icon_bottom_bar_unselect)
                            )
                        },
//                        label = { Text(LocalContext.current.getString(screen.title)) }
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Tab.ProductList.tab,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Tab.ProductList.tab) {
                val viewModel: ProductListViewModel = hiltViewModel()
                ProductListScreenTab(viewModel)
            }
            composable(Tab.Search.tab) {
                SearchScreenTab()
            }
            composable(Tab.Bookmark.tab) {
                BookmarkScreenTab()
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    JetpackComposeHomeAssignmentTheme {
        HomeScreen()
    }
}