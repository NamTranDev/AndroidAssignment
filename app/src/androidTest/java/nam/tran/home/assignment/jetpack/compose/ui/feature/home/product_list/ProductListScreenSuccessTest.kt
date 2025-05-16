package nam.tran.home.assignment.jetpack.compose.ui.feature.home.product_list

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToIndex
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import nam.tran.home.assignment.jetpack.compose.MainActivity
import nam.tran.home.assignment.jetpack.compose.fakes.CaseTest
import nam.tran.home.assignment.jetpack.compose.ui.theme.JetpackComposeHomeAssignmentTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class ProductListScreenSuccessTest {

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
                ProductListTabScreen { }
            }
        }
    }

    @Test
    fun loadCategoriesFail() = runTest {
        caseTest.isCategorySuccess = false

        val tag = "load_category_error"

        composeTestRule.onNodeWithTag("loading").assertIsDisplayed()

        composeTestRule.waitUntil(
            condition = {
                composeTestRule
                    .onAllNodesWithTag(tag)
                    .fetchSemanticsNodes().isNotEmpty()
            },
            timeoutMillis = 5_000
        )

        composeTestRule.onNodeWithTag(tag).assertIsDisplayed()
    }

    @Test
    fun loadCategoriesSuccess() = runTest {
        caseTest.isCategorySuccess = true

        composeTestRule.onNodeWithTag("loading").assertIsDisplayed()

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
            .assertIsDisplayed()
    }

    @Test
    fun performClickAndLoadProduct() {

        loadCategoriesSuccess()

        composeTestRule
            .onNodeWithText("Category 1")
            .performClick()

        composeTestRule.onNodeWithTag("pull_to_refresh_indicator").assertIsDisplayed()
    }

    @Test
    fun loadProductsAndLoadMoreSuccess() = runTest {
        caseTest.productType = 0

        performClickAndLoadProduct()

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

//        composeTestRule.waitUntil(
//            timeoutMillis = 1_000,
//            condition = {
//                composeTestRule
//                    .onAllNodesWithTag("loading", useUnmergedTree = true)
//                    .fetchSemanticsNodes()
//                    .isNotEmpty()
//            }
//        )
//
        composeTestRule
            .onNodeWithTag("loading", useUnmergedTree = true)
            .assertIsDisplayed()

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
            .onNodeWithTag("product_list")
            .performScrollToIndex(10)

        composeTestRule
            .onNodeWithText("Product 11")
            .assertIsDisplayed()
    }

    @Test
    fun loadProductsFail() = runTest {
        caseTest.productType = 1

        val tag = "load_product_error"

        performClickAndLoadProduct()

        composeTestRule.waitUntil(
            condition = {
                composeTestRule
                    .onAllNodesWithTag(tag)
                    .fetchSemanticsNodes().isNotEmpty()
            },
            timeoutMillis = 5_000
        )

        composeTestRule
            .onNodeWithTag(tag)
            .assertIsDisplayed()
    }

    @Test
    fun loadProductLoadMoreFail() = runTest {
        caseTest.productType = 2
        val tag = "load_product_more_error"

        performClickAndLoadProduct()

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

        composeTestRule.onNodeWithTag("product_list") // testTag của LazyRow
            .performScrollToIndex(10)

        composeTestRule.waitUntil(
            timeoutMillis = 5_000,
            condition = {
                composeTestRule
                    .onAllNodesWithTag(tag)
                    .fetchSemanticsNodes().isNotEmpty()
            }
        )

        composeTestRule
            .onNodeWithTag(tag)
            .assertIsDisplayed()
    }
}


