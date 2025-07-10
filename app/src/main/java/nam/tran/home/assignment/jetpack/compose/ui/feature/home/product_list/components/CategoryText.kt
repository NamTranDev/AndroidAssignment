package nam.tran.home.assignment.jetpack.compose.ui.feature.home.product_list.components

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nam.tran.home.assignment.jetpack.compose.model.response.CategoryResponse
import nam.tran.home.assignment.jetpack.compose.ui.theme.JetpackComposeHomeAssignmentTheme

@Composable
fun CategoryText(category: CategoryResponse, isSelected: Boolean, onClick: () -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }
    Text(
        text = category.name ?: "",
        modifier = Modifier
            .clickable(
                interactionSource = interactionSource, indication = null
            ) { onClick() }
            .padding(horizontal = 16.dp, vertical = 8.dp),
        style = MaterialTheme.typography.titleMedium.copy(
            fontWeight = FontWeight.SemiBold
        ),
        color = if (isSelected) Color.Blue else Color.Gray
    )
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun CategoryTextPreview() {
    JetpackComposeHomeAssignmentTheme {
        CategoryText(
            category = CategoryResponse(
                slug = "",
                name = "Recipes"
            ), isSelected = false
        ) { }
    }

}