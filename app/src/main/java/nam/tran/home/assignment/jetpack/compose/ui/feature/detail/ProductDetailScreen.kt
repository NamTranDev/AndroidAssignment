package nam.tran.home.assignment.jetpack.compose.ui.feature.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import nam.tran.home.assignment.jetpack.compose.model.ui.StatusState
import nam.tran.home.assignment.jetpack.compose.ui.common.ErrorDisplay
import nam.tran.home.assignment.jetpack.compose.ui.feature.detail.components.ProductDetailComponent

@Composable
fun ProductDetailScreen(
    viewModel: ProductDetailViewModel = hiltViewModel()
){
    val statusState by viewModel.statusState.collectAsState()
    val productDetailState by viewModel.productDetailState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        when (statusState){
            is StatusState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is StatusState.Error -> {
                ErrorDisplay((statusState as StatusState.Error).error.message) {
                    viewModel.retry()
                }
            }

            is StatusState.Success -> {
                ProductDetailComponent(productDetailState)
            }
        }
    }
}