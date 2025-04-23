package nam.tran.home.assignment.jetpack.compose.ui.feature.home.search.components

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import nam.tran.home.assignment.jetpack.compose.model.response.ProductResponse
import nam.tran.home.assignment.jetpack.compose.ui.common.ErrorDisplay
import nam.tran.home.assignment.jetpack.compose.ui.common.LoadingDisplay
import nam.tran.home.assignment.jetpack.compose.ui.common.PullToRefresh
import nam.tran.home.assignment.jetpack.compose.ui.feature.home.product_list.components.ProductCard

@Composable
fun SearchScreenComponent(
    query: String,
    products: LazyPagingItems<ProductResponse>,
    onSearchQuery: (String) -> Unit = {},
    onBack: () -> Unit = {}
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 15.dp, start = 20.dp, end = 20.dp)
                .statusBarsPadding()
        ) {
            SearchBar(text = query, onValueChange = {
                onSearchQuery(it)
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
                PullToRefresh(refreshing = refreshing, onRefresh = {
                    products.refresh()
                }) {
                    if (products.loadState.refresh !is LoadState.Loading) {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            contentPadding = PaddingValues(vertical = 10.dp),
                        ) {
                            items(products.itemCount) { index ->
                                val product = products[index]
                                ProductCard(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(start = 10.dp, end = 10.dp),
                                    product = product,
                                )
                            }

                            products.apply {
                                when (loadState.append) {
                                    is LoadState.Loading -> {
                                        item(span = { GridItemSpan(maxLineSpan) }) {
                                            LoadingDisplay()
                                        }
                                    }

                                    is LoadState.Error -> {
                                        item {
                                            val error = (loadState.append as LoadState.Error).error
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