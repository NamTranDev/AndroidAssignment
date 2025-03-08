package nam.tran.home.assignment.jetpack.compose.ui.common

import android.content.res.Configuration
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import nam.tran.home.assignment.jetpack.compose.R
import nam.tran.home.assignment.jetpack.compose.ui.theme.JetpackComposeHomeAssignmentTheme

@Composable
fun ButtonApp(
    onClick: () -> Unit,
    text: String,
    textColor: Color? = null,
    isTextButton: Boolean = false
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isTextButton) Color.Transparent else MaterialTheme.colorScheme.primary,
            contentColor = if (isTextButton) Color.Transparent else Color.White
        ),
        shape = RoundedCornerShape(size = if (isTextButton) 0.dp else 6.dp)
    ) {
        Text(
            text = text, style = MaterialTheme.typography.labelMedium.copy(
                fontWeight = FontWeight.SemiBold
            ), color = textColor ?: if (isTextButton) MaterialTheme.colorScheme.primary else colorResource(id = R.color.text_button)
        )
    }
}

class IsTextButtonProvider : PreviewParameterProvider<Boolean> {
    override val values = sequenceOf(false, true)
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewButtonApp(
    @PreviewParameter(IsTextButtonProvider::class) isTextButton: Boolean
) {
    JetpackComposeHomeAssignmentTheme {
        ButtonApp(
            onClick = {},
            text = "Button",
            isTextButton = isTextButton
        )
    }
}