package nam.tran.home.assignment.jetpack.compose.ui.feature.home.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import nam.tran.home.assignment.jetpack.compose.ui.common.ErrorDisplay
import nam.tran.home.assignment.jetpack.compose.ui.feature.home.product_list.components.ProductCard
import nam.tran.home.assignment.jetpack.compose.ui.feature.home.search.components.SearchBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    viewModel: SearchViewModel,
    onBack : () -> Unit
) {
    val queryState by viewModel.searchState.collectAsState()
    val products = viewModel.productState.collectAsLazyPagingItems()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 15.dp, start = 20.dp, end = 20.dp)
                .statusBarsPadding()
        ) {
            SearchBar(text = queryState, onValueChange = {
                viewModel.updateSearch(it)
            }, onBack = {
                onBack.invoke()
            })

            Spacer(modifier = Modifier.height(20.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
            ) {
                val refreshing = products.loadState.refresh is LoadState.Loading
                val pullRefreshState = rememberPullToRefreshState()
                PullToRefreshBox(
                    modifier = Modifier.fillMaxSize(),
                    isRefreshing = refreshing,
                    onRefresh = {
                        products.refresh()
                    },
                    state = pullRefreshState,
                    indicator = {
                        Indicator(
                            modifier = Modifier.align(Alignment.TopCenter),
                            isRefreshing = refreshing,
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            state = pullRefreshState
                        )
                    }
                ) {
                    if (products.loadState.refresh !is LoadState.Loading) {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            contentPadding = PaddingValues(vertical = 10.dp),
                        ) {
                            items(products.itemCount) { index ->
                                val product = products[index]
                                ProductCard(
                                    modifier = Modifier.fillMaxWidth().padding(start = 10.dp,end = 10.dp),
                                    product = product,
                                )
                            }

                            products.apply {
                                when (loadState.append) {
                                    is LoadState.Loading -> {
                                        item(span = { GridItemSpan(maxLineSpan) }) {
                                            Box(
                                                modifier = Modifier.fillMaxWidth(),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                CircularProgressIndicator(
                                                    modifier = Modifier.padding(
                                                        16.dp
                                                    )
                                                )
                                            }
                                        }
                                    }

                                    is LoadState.Error -> {
                                        item {
                                            val error =
                                                (loadState.append as LoadState.Error).error
                                            ErrorDisplay(message = error.message) {
                                                products.refresh()
                                            }
                                        }
                                    }

                                    else -> {}
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}