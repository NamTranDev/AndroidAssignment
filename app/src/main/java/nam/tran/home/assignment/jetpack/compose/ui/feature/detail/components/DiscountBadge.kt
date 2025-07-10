package nam.tran.home.assignment.jetpack.compose.ui.feature.detail.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nam.tran.home.assignment.jetpack.compose.ui.theme.JetpackComposeHomeAssignmentTheme

@Composable
fun DiscountBadge(discount: String?, modifier: Modifier = Modifier) {
    BoxWithConstraints(modifier = modifier) {
        val badgeSize = maxWidth * 0.25f
        Box(
            modifier = Modifier
                .size(badgeSize)
                .background(Color.Red, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "${discount ?: ""}% off".uppercase(),
                style = MaterialTheme.typography.labelMedium.copy(color = Color.White, fontSize = 10.sp),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DiscountBadgePreview() {
    JetpackComposeHomeAssignmentTheme {
        DiscountBadge("50", modifier = Modifier.size(200.dp))
    }
}