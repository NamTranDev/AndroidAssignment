package nam.tran.home.assignment.jetpack.compose.ui.feature.detail

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.rememberAsyncImagePainter
import nam.tran.home.assignment.jetpack.compose.R
import nam.tran.home.assignment.jetpack.compose.model.response.ProductDetailResponse
import nam.tran.home.assignment.jetpack.compose.model.ui.StatusState
import nam.tran.home.assignment.jetpack.compose.ui.common.ErrorDisplay
import nam.tran.home.assignment.jetpack.compose.ui.feature.detail.components.DiscountBadge
import nam.tran.home.assignment.jetpack.compose.ui.feature.home.product_list.components.BookmarkToggle
import nam.tran.home.assignment.jetpack.compose.ui.feature.home.product_list.components.ProductPriceAndCart
import nam.tran.home.assignment.jetpack.compose.ui.theme.JetpackComposeHomeAssignmentTheme

@Composable
fun ProductDetailScreen(
    viewModel: ProductDetailViewModel = hiltViewModel(),
    onBack: () -> Unit
) {
    val statusState by viewModel.statusState.collectAsState()
    val productDetailState by viewModel.detailDataState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        when (statusState) {
            is StatusState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is StatusState.Error -> {
                ErrorDisplay(message = (statusState as StatusState.Error).error.message) {
                    viewModel.loadProductDetail()
                }
            }

            is StatusState.Success -> {
                ProductDetailContent(productDetail = productDetailState, onBack = onBack)
            }
        }
    }
}

@Composable
fun ProductDetailContent(
    productDetail: ProductDetailResponse?,
    onBack: () -> Unit = {},
    isPreview: Boolean = false
) {
//    Logger.debug(productDetail)
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(screenHeight * 5.5f / 10)
        ) {
            Row(modifier = Modifier.padding(bottom = 10.dp)) {
                IconButton(modifier = Modifier.width(60.dp), onClick = {}) {
                    IconButton(onClick = {
                        onBack()
                    }) {
                        Icon(
                            modifier = Modifier
                                .size(24.dp)
                                .align(Alignment.CenterVertically),
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .background(
                            color = Color.Gray.copy(alpha = 0.2f),
                            shape = RoundedCornerShape(bottomStart = 32.dp)
                        ),
                    painter = rememberAsyncImagePainter(
                        model = productDetail?.thumbnail,
                        placeholder = painterResource(R.drawable.image_place_holder),
                        error = painterResource(R.drawable.image_place_holder)
                    ),
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                )
            }
            DiscountBadge(
                discount = productDetail?.discountPercentage?.toString(),
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 40.dp)
            )
            BookmarkToggle(
                modifier = Modifier.align(Alignment.TopEnd),
                product = productDetail?.toProduct(),
                isPreview = isPreview
            )
        }
        Spacer(modifier = Modifier.weight(0.8f))
        Text(
            productDetail?.title?.uppercase() ?: "",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            style = MaterialTheme.typography.titleLarge.copy(
                fontSize = 25.sp, color = MaterialTheme.colorScheme.primary
            )
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            productDetail?.brand?.uppercase() ?: productDetail?.category?.uppercase() ?: "",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            style = MaterialTheme.typography.titleMedium.copy(
                fontSize = 18.sp
            )
        )
        Spacer(modifier = Modifier.weight(0.5f))
        Text(
            productDetail?.description ?: "",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 15.sp
            )
        )
        Spacer(modifier = Modifier.weight(0.6f))
        ProductPriceAndCart(
            modifier = Modifier.padding(horizontal = 30.dp),
            price = productDetail?.price?.toString(),
            textStyle = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp
            ),
            iconSize = 30.dp,
            onProductCartClick = {})

        Spacer(modifier = Modifier.weight(0.8f))
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ProductDetailPreview() {
    JetpackComposeHomeAssignmentTheme {
        ProductDetailContent(
            productDetail = ProductDetailResponse(
                id = 1,
                title = "Essence Mascara Lash Princess",
                description = "The Essence Mascara Lash Princess is a popular mascara known for its volumizing and lengthening effects. Achieve dramatic lashes with this long-lasting and cruelty-free formula.",
                brand = "Essence",
                price = 8.0,
                discountPercentage = 7.7
            ),
            isPreview = true
        )
    }
}