package nam.tran.home.assignment.jetpack.compose.ui.feature.detail.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import io.lifestyle.plus.utils.Logger
import nam.tran.home.assignment.jetpack.compose.model.response.ProductDetailResponse

@Composable
fun ProductDetailCard(productDetail: ProductDetailResponse?) {
    Logger.debug(productDetail)
    Text(text = productDetail?.title ?: "")
}