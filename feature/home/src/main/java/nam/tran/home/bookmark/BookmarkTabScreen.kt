package nam.tran.home.bookmark

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import nam.tran.domain.model.entity.ProductEntity
import nam.tran.components.EmptyDisplay
import nam.tran.home.product_list.components.ProductCard
import nam.tran.resource.theme.AppTheme

@Composable
fun BookmarkTabScreen(
    viewModel: BookmarkViewModel = hiltViewModel(),
) {
    val products by viewModel.bookmarkedProducts.collectAsState()
    BookmarkTabScreenContent(products = products)
}

@Composable
fun BookmarkTabScreenContent(products : List<ProductEntity>, isPreview : Boolean = false){
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(start = 20.dp, end = 20.dp, top = 15.dp)) {
        Text(
            "Bookmark".uppercase(),
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.headlineLarge.copy(
                fontWeight = FontWeight.Bold
            )
        )
        if (products.isEmpty()){
            EmptyDisplay(modifier = Modifier
                .fillMaxWidth()
                .weight(1f))
        }else{
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 20.dp)
                    .weight(1f),
            ) {
                items(products.size) { index ->
                    val product = products[index]
                    ProductCard(
                        product = product,
                        isPreview = isPreview
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun BookmarkTabScreenComponentPreview() {
    AppTheme {
        val products = remember {
            listOf(
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
        }
        BookmarkTabScreenContent(products = products, isPreview = true)
    }
}