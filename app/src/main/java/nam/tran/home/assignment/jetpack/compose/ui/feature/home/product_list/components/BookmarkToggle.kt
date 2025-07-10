package nam.tran.home.assignment.jetpack.compose.ui.feature.home.product_list.components

import android.content.res.Configuration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import nam.tran.domain.model.entity.ProductEntity
import nam.tran.home.assignment.jetpack.compose.R
import nam.tran.home.assignment.jetpack.compose.ui.feature.home.bookmark.BookmarkShareViewModel
import nam.tran.home.assignment.jetpack.compose.ui.theme.JetpackComposeHomeAssignmentTheme

@Composable
fun BookmarkToggle(
    modifier: Modifier = Modifier,
    product: ProductEntity?,
    isPreview : Boolean = false,
){
    val bookmarkViewModel: BookmarkShareViewModel? = if (!isPreview) hiltViewModel() else null
    val bookmarkedIds by bookmarkViewModel?.bookmarkedIds?.collectAsState() ?: remember { mutableStateOf(emptyList()) }
    val isBookmarked = product?.id in bookmarkedIds

    IconButton(modifier = modifier, onClick = {
        bookmarkViewModel?.toggleBookmark(product)
    }) {
        Icon(
            painterResource(R.drawable.ic_bookmark),
            contentDescription = null,
            tint = if (isBookmarked) Color.Green else if (isSystemInDarkTheme()) Color.White else Color.Black
        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun BookmarkTogglePreview() {
    JetpackComposeHomeAssignmentTheme {
        BookmarkToggle(product = ProductEntity(
            id = 1,
            title = "Horizontal Product",
            description = "This is shown in horizontal layout.",
            brand = "Brand A",
            category = "Category A",
            price = 12.34,
            thumbnail = "https://cdn.dummyjson.com/product-image.jpg"
        ), isPreview = true)
    }
}