package nam.tran.home.assignment.jetpack.compose.ui.feature.home.product_list

import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToLog
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import nam.tran.home.assignment.jetpack.compose.MainActivity
import nam.tran.home.assignment.jetpack.compose.ui.theme.JetpackComposeHomeAssignmentTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class ProductListScreenTest {

    @get:Rule(order = 1)
    var hiltTestRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    var composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setup() {
        hiltTestRule.inject()
        composeTestRule.activity.setContent {
            JetpackComposeHomeAssignmentTheme{
                ProductListTabScreen {

                }
            }
        }
    }

    @ExperimentalFoundationApi
    @ExperimentalComposeUiApi
    @Test
    fun loadCategoriesAndDisplayProducts() = runTest {
        composeTestRule.waitUntil(
            condition = {
                composeTestRule.onRoot().printToLog("ComposeTree")
                composeTestRule
                    .onAllNodesWithText("Category 1")
                    .fetchSemanticsNodes().isNotEmpty()
            },
            timeoutMillis = 5_000
        )

        composeTestRule
            .onNodeWithText("Category 1")
            .performClick()

        composeTestRule
            .onNodeWithText("Product 1")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Category 2")
            .performClick()

        composeTestRule
            .onNodeWithText("Product 2")
            .assertIsDisplayed()
    }
}
