package nam.tran.home.assignment.jetpack.compose.ui.feature.home.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(){
    Column(modifier = Modifier.fillMaxSize()){
        Spacer(Modifier.height(100.dp))
        Text("This is Home Screen")
    }
}