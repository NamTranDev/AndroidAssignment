package nam.tran.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import nam.tran.resource.theme.AppTheme

@Composable
fun EmptyDisplay(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.testTag("empty_display").fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            "Oops! No item",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun LoadingDisplayPreview() {
    AppTheme {
        EmptyDisplay(modifier = Modifier.fillMaxSize())
    }
}