package nam.tran.home.assignment.jetpack.compose.ui.feature.home.search

import android.content.res.Configuration
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
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import kotlinx.coroutines.flow.MutableStateFlow
import nam.tran.domain.model.entity.ProductEntity
import nam.tran.home.assignment.jetpack.compose.ui.common.EmptyDisplay
import nam.tran.home.assignment.jetpack.compose.ui.common.ErrorDisplay
import nam.tran.home.assignment.jetpack.compose.ui.common.LoadingDisplay
import nam.tran.home.assignment.jetpack.compose.ui.common.PullToRefresh
import nam.tran.home.assignment.jetpack.compose.ui.feature.home.product_list.components.ProductCard
import nam.tran.home.assignment.jetpack.compose.ui.feature.home.search.components.SearchBar
import nam.tran.home.assignment.jetpack.compose.ui.theme.JetpackComposeHomeAssignmentTheme

@Composable
fun SearchProductTabScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    onBack: () -> Unit
) {
    val queryState by viewModel.searchState.collectAsState()
    val products = viewModel.productState.collectAsLazyPagingItems()
    SearchProductTabScreenContent(
        query = queryState,
        products = products,
        onSearchQuery = { query ->
            viewModel.updateSearch(query)
        },
        onBack = onBack
    )
}

@Composable
fun SearchProductTabScreenContent(
    query: String,
    products: LazyPagingItems<ProductEntity>,
    onSearchQuery: (String) -> Unit = {},
    onBack: () -> Unit = {},
    isPreview: Boolean = false
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
                    when (products.loadState.refresh) {
                        is LoadState.Loading -> {}
                        is LoadState.Error -> {
                            val error =
                                (products.loadState.refresh as LoadState.Error).error
                            ErrorDisplay(
                                modifier = Modifier.testTag("load_product_error"),
                                message = error.message,
                                sizeImage = 200
                            ) {
                                products.refresh()
                            }
                        }

                        else -> {
                            val total = products.itemCount
                            if (total == 0) {
                                EmptyDisplay()
                            } else {
                                LazyVerticalGrid(
                                    modifier = Modifier.testTag("product_list"),
                                    columns = GridCells.Fixed(2),
                                    contentPadding = PaddingValues(vertical = 10.dp),
                                ) {
                                    items(total) { index ->
                                        val product = products[index]
                                        ProductCard(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(start = 10.dp, end = 10.dp),
                                            product = product,
                                            isPreview = isPreview
                                        )
                                    }

                                    products.apply {
                                        when (loadState.append) {
                                            is LoadState.Loading -> {
                                                item {
                                                    LoadingDisplay()
                                                }
                                            }

                                            is LoadState.Error -> {
                                                item {
                                                    val error =
                                                        (loadState.append as LoadState.Error).error
                                                    ErrorDisplay(
                                                        modifier = Modifier.testTag("load_product_more_error"),
                                                        message = error.message,
                                                        sizeImage = 200
                                                    ) {
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
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun SearchScreenComponentPreview() {
    val products = listOf(
        ProductEntity(
            id = 1,
            title = "Horizontal Product",
            description = "This is shown in horizontal layout.",
            brand = "Brand A",
            category = "Category A",
            price = 12.34,
            thumbnail = "https://cdn.dummyjson.com/product-image.jpg"
        ),
        ProductEntity(
            id = 2,
            title = "Horizontal Product",
            description = "This is shown in horizontal layout.",
            brand = "Brand A",
            category = "Category A",
            price = 12.34,
            thumbnail = "https://cdn.dummyjson.com/product-image.jpg"
        )
    )

    val flowPaging = MutableStateFlow(
        PagingData.from(
            data = products
        )
    )
    JetpackComposeHomeAssignmentTheme {
        SearchProductTabScreenContent(
            query = "abc", products = flowPaging.collectAsLazyPagingItems(), isPreview = true
        )
    }
}