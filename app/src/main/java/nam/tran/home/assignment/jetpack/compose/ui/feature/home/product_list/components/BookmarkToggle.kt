package nam.tran.home.assignment.jetpack.compose.ui.feature.home.product_list.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import io.lifestyle.plus.utils.Logger
import nam.tran.home.assignment.jetpack.compose.R
import nam.tran.home.assignment.jetpack.compose.model.response.ProductResponse
import nam.tran.home.assignment.jetpack.compose.ui.feature.home.bookmark.BookmarkShareViewModel

@Composable
fun BookmarkToggle(
    modifier: Modifier = Modifier,
    product: ProductResponse?
){
    val bookmarkViewModel: BookmarkShareViewModel = hiltViewModel()
    val bookmarkedIds by bookmarkViewModel.bookmarkedIds.collectAsState()
    val isBookmarked = product?.id in bookmarkedIds
    IconButton(modifier = modifier, onClick = {
        Logger.debug("onToggleBookmark")
        bookmarkViewModel.toggleBookmark(product)
    }) {
        Icon(
            painterResource(R.drawable.ic_bookmark),
            contentDescription = null,
            tint = if (isBookmarked) Color.Green else if (isSystemInDarkTheme()) Color.White else Color.Black
        )
    }
}