package nam.tran.home.assignment.jetpack.compose.ui.feature.home.bookmark

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import nam.tran.home.assignment.jetpack.compose.ui.feature.home.product_list.components.ProductCard

@Composable
fun BookmarkTabScreen(
    viewModel: BookmarkViewModel = hiltViewModel(),
) {
    val products by viewModel.bookmarkedProducts.collectAsState()
    Column(modifier = Modifier.fillMaxSize().padding(start = 20.dp, end = 20.dp, top = 15.dp)) {
        Text(
            "Bookmark".uppercase(),
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.headlineLarge.copy(
                fontWeight = FontWeight.Bold
            )
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp)
                .weight(1f),
        ) {
            items(products.size) { index ->
                val product = products[index]
                ProductCard(
                    product = product
                )
            }
        }
    }
}