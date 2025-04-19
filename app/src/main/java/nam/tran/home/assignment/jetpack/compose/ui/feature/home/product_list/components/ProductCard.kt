package nam.tran.home.assignment.jetpack.compose.ui.feature.home.product_list.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import dagger.hilt.android.EntryPointAccessors
import nam.tran.home.assignment.jetpack.compose.R
import nam.tran.home.assignment.jetpack.compose.model.response.ProductResponse
import nam.tran.home.assignment.jetpack.compose.ui.navigation.NavigationDispatcherEntryPoint
import nam.tran.home.assignment.jetpack.compose.ui.navigation.NavigationEvent
import nam.tran.home.assignment.jetpack.compose.ui.theme.JetpackComposeHomeAssignmentTheme

@Composable
fun ProductCard(
    modifier: Modifier = Modifier,
    product: ProductResponse?,
    isHorizontal: Boolean = false,
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val widthCard = screenWidth * 4 / 6

    val dispatcher = EntryPointAccessors.fromApplication(
        LocalContext.current.applicationContext,
        NavigationDispatcherEntryPoint::class.java
    ).navigationDispatcher()

    Box(
        modifier = modifier
            .padding(top = 10.dp, bottom = 10.dp, end = if (isHorizontal) 20.dp else 0.dp)
    ) {
        Card(
            modifier = (if (isHorizontal) Modifier.width(widthCard) else Modifier.fillMaxWidth()).clickable {
                product?.id?.run {
                    dispatcher.tryEmit(NavigationEvent.NavigateToProductDetail(this))
                }
            },
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
        ) {
            Column {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(0.8f)
                        .background(color = Color.Gray.copy(alpha = 0.2f)),
                    painter = rememberAsyncImagePainter(
                        model = product?.thumbnail,
                        placeholder = painterResource(R.drawable.image_place_holder),
                        error = painterResource(R.drawable.image_place_holder)
                    ),
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(if (isHorizontal) 25.dp else 50.dp)
                        .padding(start = 10.dp, end = 10.dp)
                        .wrapContentHeight(Alignment.CenterVertically),
                    text = product?.title ?: "",
                    maxLines = if (isHorizontal) 1 else 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                )

                if (isHorizontal)
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .padding(top = 5.dp, start = 10.dp, end = 10.dp),
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                        text = product?.description ?: "",
                        style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Light)
                    )

                if (isHorizontal)
                    Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 10.dp)
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        text = ("$" + product?.price?.toString()),
                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
                    )
                    Icon(
                        modifier = Modifier.size(20.dp),
                        painter = painterResource(R.drawable.ic_add_cart),
                        contentDescription = null,
                    )
                }
            }
        }
        BookmarkToggle(modifier = Modifier.align(Alignment.TopEnd), product = product)
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ProductCardMultiPreview(
    @PreviewParameter(ProductCardPreviewProvider::class) data: Pair<Boolean, ProductResponse>,
) {
    val (isHorizontal, product) = data

    JetpackComposeHomeAssignmentTheme {
        ProductCard(
            product = product,
            isHorizontal = isHorizontal
        )
    }
}

class ProductCardPreviewProvider : PreviewParameterProvider<Pair<Boolean, ProductResponse>> {

    val product = ProductResponse(
        id = 1,
        title = "Horizontal Product",
        description = "This is shown in horizontal layout.",
        brand = "Brand A",
        category = "Category A",
        price = 12.34,
        thumbnail = "https://cdn.dummyjson.com/product-image.jpg"
    )

    override val values: Sequence<Pair<Boolean, ProductResponse>> =
        sequenceOf(true to product, false to product)
}