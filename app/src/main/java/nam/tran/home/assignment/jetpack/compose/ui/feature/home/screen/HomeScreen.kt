package nam.tran.home.assignment.jetpack.compose.ui.feature.home.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import nam.tran.home.assignment.jetpack.compose.model.response.CategoryResponse
import nam.tran.home.assignment.jetpack.compose.model.response.ProductResponse
import nam.tran.home.assignment.jetpack.compose.ui.feature.home.HomeViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val categories by viewModel.categoriesState.collectAsState()
    val isLoadingCategories by viewModel.isLoadingCategoryState.collectAsState()
    val isLoadingProducts by viewModel.isLoadingProductState.collectAsState()
    val products by viewModel.productsState.collectAsState()
    val selectedCategory by viewModel.selectedCategoryState.collectAsState()

    Scaffold { paddingValue ->
        Box(modifier = Modifier.fillMaxSize().padding(paddingValue)) {
            if (isLoadingCategories) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                Column(modifier = Modifier.fillMaxSize()) {
                    if (isLoadingProducts) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    } else {
                        LazyColumn(
                            modifier = Modifier.weight(1f)
                        ) {
                            items(products) { product ->
                                ProductItem(product)
                            }
                        }
                    }
                }

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .background(Color.LightGray)
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp)
                ) {
                    items(categories) { category ->
                        CategoryItem(category, category == selectedCategory) {
                            viewModel.selectCategory(category)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CategoryItem(category: CategoryResponse, isSelected: Boolean, onClick: () -> Unit) {
    Text(
        text = category.name ?: "",
        modifier = Modifier
            .background(
                if (isSelected) Color.Blue else Color.Gray,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 8.dp),
        color = Color.White
    )
}

@Composable
fun ProductItem(product: ProductResponse) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = product.title ?: "", fontWeight = FontWeight.Bold)
            Text(text = product.price.toString(), color = Color.Green)
        }
    }
}