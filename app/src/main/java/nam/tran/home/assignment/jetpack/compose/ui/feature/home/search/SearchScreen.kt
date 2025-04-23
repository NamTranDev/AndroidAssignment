package nam.tran.home.assignment.jetpack.compose.ui.feature.home.search

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import nam.tran.home.assignment.jetpack.compose.ui.feature.home.search.components.SearchScreenComponent

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    onBack: () -> Unit
) {
    val queryState by viewModel.searchState.collectAsState()
    val products = viewModel.productState.collectAsLazyPagingItems()
    SearchScreenComponent(query = queryState, products = products, onSearchQuery = { query ->
        viewModel.updateSearch(query)
    }, onBack = onBack)
}