package nam.tran.home.assignment.jetpack.compose.ui.feature.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ProductDetailScreen(){
    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        Text("Product Detail Screen")
    }
}