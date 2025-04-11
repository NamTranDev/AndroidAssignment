package nam.tran.home.assignment.jetpack.compose.ui.feature.home.product_list.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nam.tran.home.assignment.jetpack.compose.model.response.CategoryResponse
import nam.tran.home.assignment.jetpack.compose.ui.theme.JetpackComposeHomeAssignmentTheme

@Composable
fun CategorySurface(
    categories: List<CategoryResponse>,
    selectedCategory: CategoryResponse?,
    onCategoryClick: ((CategoryResponse) -> Unit)? = null
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(5.dp)
                .background(
                    brush = Brush.verticalGradient(
                        0.0f to Color.Transparent,
                        0.5f to Color.Black.copy(alpha = 0.025f),
                        0.75f to Color.Black.copy(alpha = 0.05f),
                        1.0f to Color.Black.copy(alpha = 0.2f)
                    ),
                )
        )
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(categories) { category ->
                CategoryText(category, category == selectedCategory) {
                   onCategoryClick?.invoke(category)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun CategorySurfacePreview() {
    JetpackComposeHomeAssignmentTheme {
        CategorySurface(
            categories = listOf(
                CategoryResponse(name = "Hello"),
                CategoryResponse(name = "World"),
                CategoryResponse(name = "Android")
            ), selectedCategory = CategoryResponse(name = "Android")
        )
    }
}