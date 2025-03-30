package nam.tran.home.assignment.jetpack.compose.ui.feature.home.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import nam.tran.home.assignment.jetpack.compose.model.ui.StatusState
import nam.tran.home.assignment.jetpack.compose.ui.feature.home.HomeViewModel
import nam.tran.home.assignment.jetpack.compose.ui.feature.home.screen.components.CategoryErrorDisplay
import nam.tran.home.assignment.jetpack.compose.ui.feature.home.screen.components.CategorySurface
import nam.tran.home.assignment.jetpack.compose.ui.feature.home.screen.components.ProductCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val categories by viewModel.categoriesDataState.collectAsState()
    val selectedCategory by viewModel.selectedCategoryState.collectAsState()
    val statusStateCategory by viewModel.statusStateCategory.collectAsState()
    val products = viewModel.productsState.collectAsLazyPagingItems()
    val scrollState by viewModel.scrollState.collectAsState()

    Scaffold { paddingValue ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValue)
        ) {
            when(statusStateCategory){
                is StatusState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                is StatusState.Error -> {
                    CategoryErrorDisplay((statusStateCategory as StatusState.Error).error.message){
                        viewModel.loadCategories()
                    }
                }
                is StatusState.Success -> {
                    Column(modifier = Modifier.fillMaxSize()) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            contentAlignment = Alignment.Center
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
                                    LazyRow(modifier = Modifier.fillMaxSize(), state = scrollState) {
                                        items(products.itemCount) { index ->
                                            val product = products[index]
                                            ProductCard(product)
                                        }

                                        products.apply {
                                            when (loadState.append) {
                                                is LoadState.Loading -> {
                                                    item {
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
                                                    val error =
                                                        (loadState.append as LoadState.Error).error
                                                    item { Text("Lỗi tải thêm dữ liệu: ${error.localizedMessage}") }
                                                }

                                                else -> {}
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        CategorySurface(categories, selectedCategory) { category ->
                            viewModel.selectCategory(category)
                        }
                    }
                }
            }
        }
    }
}