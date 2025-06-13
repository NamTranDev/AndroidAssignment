package nam.tran.home.assignment.jetpack.compose.ui.feature.home.product_list

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import kotlinx.coroutines.flow.MutableStateFlow
import nam.tran.domain.model.entity.CategoryEntity
import nam.tran.domain.model.entity.ProductEntity
import nam.tran.home.assignment.jetpack.compose.R
import nam.tran.home.assignment.jetpack.compose.model.ui.StatusState
import nam.tran.home.assignment.jetpack.compose.ui.common.EmptyDisplay
import nam.tran.home.assignment.jetpack.compose.ui.common.ErrorDisplay
import nam.tran.home.assignment.jetpack.compose.ui.common.LoadingDisplay
import nam.tran.home.assignment.jetpack.compose.ui.common.PullToRefresh
import nam.tran.home.assignment.jetpack.compose.ui.feature.home.product_list.components.CategorySurface
import nam.tran.home.assignment.jetpack.compose.ui.feature.home.product_list.components.IndicatorProduct
import nam.tran.home.assignment.jetpack.compose.ui.feature.home.product_list.components.ProductCard


@Composable
fun ProductListTabScreen(
    viewModel: ProductListViewModel = hiltViewModel(),
    openSearch: () -> Unit,
) {
    val categories by viewModel.categoriesDataState.collectAsState()
    val selectedCategory by viewModel.selectedCategoryState.collectAsState()
    val statusStateCategory by viewModel.statusStateCategory.collectAsState()
    val products = viewModel.productsState.collectAsLazyPagingItems()
    val scrollState by viewModel.scrollState.collectAsState()
    val currentIndicator by viewModel.currentIndicator.collectAsState()

    ProductListTabScreenContent(
        statusStateCategory = statusStateCategory,
        categories = categories,
        selectedCategory = selectedCategory,
        products = products,
        scrollState = scrollState,
        currentIndicator = currentIndicator,
        onLoadCategories = {
            viewModel.loadCategories()
        },
        onOpenSearch = {
            openSearch()
        },
        onSelectCategory = {
            viewModel.selectCategory(it)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListTabScreenContent(
    statusStateCategory: StatusState,
    categories: List<CategoryEntity>,
    selectedCategory: CategoryEntity?,
    products: LazyPagingItems<ProductEntity>,
    scrollState: LazyListState,
    currentIndicator: Int,
    onLoadCategories: () -> Unit = {},
    onOpenSearch: () -> Unit = {},
    onSelectCategory: (CategoryEntity) -> Unit = {},
    isPreview: Boolean = false,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        when (statusStateCategory) {
            is StatusState.Loading -> {
                LoadingDisplay(modifier = Modifier.fillMaxSize())
            }

            is StatusState.Error -> {
                ErrorDisplay(
                    modifier = Modifier.testTag("load_category_error"),
                    message = statusStateCategory.error.message
                ) {
                    onLoadCategories()
                }
            }

            is StatusState.Success -> {
                Column(modifier = Modifier.fillMaxSize()) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 15.dp, start = 20.dp, end = 10.dp)
                    ) {
                        Text(
                            "Products".uppercase(),
                            modifier = Modifier.weight(1f),
                            style = MaterialTheme.typography.headlineLarge.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )
                        IconButton(modifier = Modifier.testTag("search_icon"),onClick = {
                            onOpenSearch()
                        }) {
                            Icon(
                                painter = painterResource(R.drawable.ic_search),
                                contentDescription = null,
                                modifier = Modifier.size(30.dp)
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                    ) {
                        val refreshing = products.loadState.refresh is LoadState.Loading
                        PullToRefresh(refreshing = refreshing, onRefresh = {
                            products.refresh()
                        }) {
                            Column(
                                Modifier.fillMaxSize()
                            ) {
//                                Logger.debug(products.loadState)
                                when (products.loadState.refresh) {
                                    //pull to refresh loading
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
                                            LazyRow(
                                                modifier = Modifier
                                                    .fillMaxSize()
                                                    .weight(1f)
                                                    .testTag("product_list"),
                                                state = scrollState,
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                items(total) { index ->
                                                    val product = products[index]
                                                    ProductCard(
                                                        modifier = Modifier.padding(start = if (index == 0) 20.dp else 0.dp),
                                                        product = product,
                                                        isHorizontal = true,
                                                        isPreview = isPreview
                                                    )
                                                }

                                                products.apply {
//                                                Logger.debug(loadState.append)
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

                                            Spacer(Modifier.height(15.dp))

                                            IndicatorProduct(
                                                modifier = Modifier.padding(start = 15.dp),
                                                current = currentIndicator,
                                                total = total
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }

                    CategorySurface(categories, selectedCategory) { category ->
                        onSelectCategory(category)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ProductListTabScreenContentPreview() {
    val fakeProducts = listOf(
        ProductEntity(id = 1, title = "Product 1", price = 100.0),
        ProductEntity(id = 2, title = "Product 2", price = 150.0),
        ProductEntity(id = 3, title = "Product 3", price = 200.0)
    )
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

    ProductListTabScreenContent(
        statusStateCategory = StatusState.Success,
        categories = listOf(
            CategoryEntity(slug = "1", name = "Category 1"),
            CategoryEntity(slug = "2", name = "Category 2")
        ),
        selectedCategory = CategoryEntity(slug = "1", name = "Category 1"),
        products = flowPaging.collectAsLazyPagingItems(),
        scrollState = rememberLazyListState(),
        currentIndicator = 0,
        isPreview = true
    )
}