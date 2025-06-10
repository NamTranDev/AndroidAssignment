package nam.tran.home.assignment.jetpack.compose.ui.feature.home.search

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollToIndex
import androidx.compose.ui.test.performTextInput
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import nam.tran.home.assignment.jetpack.compose.MainActivity
import nam.tran.home.assignment.jetpack.compose.fakes.CaseTest
import nam.tran.home.assignment.jetpack.compose.ui.feature.home.product_list.ProductListTabScreen
import nam.tran.home.assignment.jetpack.compose.ui.theme.JetpackComposeHomeAssignmentTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class SearchProductScreenTest {

    @get:Rule(order = 1)
    var hiltTestRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    var composeTestRule = createAndroidComposeRule<MainActivity>()

    @Inject
    lateinit var caseTest: CaseTest

    @Before
    fun setup() {
        hiltTestRule.inject()
        composeTestRule.activity.setContent {
            JetpackComposeHomeAssignmentTheme {
                SearchProductTabScreen { }
            }
        }
    }

    @Test
    fun emptySearch() = runTest {
        caseTest.productType = CaseTest.ProductType.Success

        composeTestRule.onNodeWithTag("pull_to_refresh_indicator").assertIsDisplayed()

        composeTestRule.waitUntil(
            condition = {
                composeTestRule
                    .onAllNodesWithText("Product 1")
                    .fetchSemanticsNodes().isNotEmpty()
            },
            timeoutMillis = 5_000
        )

        composeTestRule
            .onNodeWithText("Product 1")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag("product_list")
            .performScrollToIndex(9)

        composeTestRule.waitUntil(
            timeoutMillis = 5_000,
            condition = {
                composeTestRule
                    .onAllNodesWithTag("loading", useUnmergedTree = true)
                    .fetchSemanticsNodes()
                    .isEmpty()
            }
        )

        composeTestRule
            .onNodeWithTag("loading", useUnmergedTree = true)
            .assertIsNotDisplayed()

        composeTestRule
            .onNodeWithText("Product 11")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag("product_list")
            .performScrollToIndex(19)

        composeTestRule.waitUntil(
            timeoutMillis = 5_000,
            condition = {
                composeTestRule
                    .onAllNodesWithTag("loading", useUnmergedTree = true)
                    .fetchSemanticsNodes()
                    .isEmpty()
            }
        )

        composeTestRule
            .onNodeWithTag("loading", useUnmergedTree = true)
            .assertIsNotDisplayed()

        composeTestRule
            .onNodeWithText("Product 21")
            .assertIsDisplayed()
    }

    @Test
    fun hasSearchInput() = runTest {
        caseTest.productType = CaseTest.ProductType.Success

        val search = "a"

        composeTestRule.onNodeWithTag("search_input").performTextInput(search)

        composeTestRule.onNodeWithTag("pull_to_refresh_indicator").assertIsDisplayed()

        composeTestRule.waitUntil(
            condition = {
                composeTestRule
                    .onAllNodesWithText("Product 1$search")
                    .fetchSemanticsNodes().isNotEmpty()
            },
            timeoutMillis = 5_000
        )

        composeTestRule
            .onNodeWithText("Product 1$search")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag("product_list")
            .performScrollToIndex(9)

        composeTestRule.waitUntil(
            timeoutMillis = 5_000,
            condition = {
                composeTestRule
                    .onAllNodesWithTag("loading", useUnmergedTree = true)
                    .fetchSemanticsNodes()
                    .isEmpty()
            }
        )

        composeTestRule
            .onNodeWithTag("loading", useUnmergedTree = true)
            .assertIsNotDisplayed()

        composeTestRule
            .onNodeWithText("Product 1$search")
            .assertIsDisplayed()
    }

    @Test
    fun searchWithEmptyResult() = runTest {
        caseTest.productType = CaseTest.ProductType.Success

        val search = "empty"

        composeTestRule.onNodeWithTag("search_input").performTextInput(search)

        composeTestRule.onNodeWithTag("pull_to_refresh_indicator").assertIsDisplayed()

        composeTestRule.waitUntil(
            condition = {
                composeTestRule
                    .onAllNodesWithTag("empty_display")
                    .fetchSemanticsNodes().isNotEmpty()
            },
            timeoutMillis = 5_000
        )

        composeTestRule
            .onNodeWithTag("empty_display")
            .assertIsDisplayed()
    }

    @Test
    fun searchWithError() = runTest {
        caseTest.productType = CaseTest.ProductType.Error

        val search = "a"

        composeTestRule.onNodeWithTag("search_input").performTextInput(search)

        composeTestRule.onNodeWithTag("pull_to_refresh_indicator").assertIsDisplayed()

        composeTestRule.waitUntil(
            condition = {
                composeTestRule
                    .onAllNodesWithTag("load_product_error")
                    .fetchSemanticsNodes().isNotEmpty()
            },
            timeoutMillis = 5_000
        )

        composeTestRule.onNodeWithTag("load_product_error").assertIsDisplayed()
    }

    @Test
    fun searchWithLoadMoreFail() = runTest {
        caseTest.productType = CaseTest.ProductType.LoadMoreError

        val search = "a"

        composeTestRule.onNodeWithTag("search_input").performTextInput(search)

        composeTestRule.onNodeWithTag("pull_to_refresh_indicator").assertIsDisplayed()

        composeTestRule.waitUntil(
            condition = {
                composeTestRule
                    .onAllNodesWithText("Product 1$search")
                    .fetchSemanticsNodes().isNotEmpty()
            },
            timeoutMillis = 5_000
        )

        composeTestRule
            .onNodeWithText("Product 1$search")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag("product_list")
            .performScrollToIndex(9)

        composeTestRule.waitUntil(
            timeoutMillis = 5_000,
            condition = {
                composeTestRule
                    .onAllNodesWithTag("loading", useUnmergedTree = true)
                    .fetchSemanticsNodes()
                    .isEmpty()
            }
        )

        composeTestRule
            .onNodeWithTag("loading", useUnmergedTree = true)
            .assertIsNotDisplayed()

        composeTestRule
            .onNodeWithTag("load_product_more_error")
            .assertIsDisplayed()
    }
}