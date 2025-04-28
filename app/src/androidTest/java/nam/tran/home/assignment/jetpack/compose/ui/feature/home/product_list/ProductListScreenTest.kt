package nam.tran.home.assignment.jetpack.compose.ui.feature.home.product_list

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class ProductListScreenTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun loadCategoriesAndDisplayProducts() = runTest {
        composeTestRule.setContent {
            ProductListTabScreen { }
        }

        composeTestRule.waitUntil(
            condition = {
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
