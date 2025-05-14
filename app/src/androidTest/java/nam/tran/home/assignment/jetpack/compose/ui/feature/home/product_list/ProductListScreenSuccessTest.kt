package nam.tran.home.assignment.jetpack.compose.ui.feature.home.product_list

import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToLog
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.advanceTimeBy
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

    @Inject lateinit var caseTest: CaseTest

    @Before
    fun setup() {
        hiltTestRule.inject()
    }

    @Test
    fun testLoadingState() {
        composeTestRule.activity.setContent {
            JetpackComposeHomeAssignmentTheme{
                ProductListTabScreen { }
            }
        }

        composeTestRule.onRoot().printToLog("ComposeTree")

        // Check loading
        composeTestRule.onNodeWithTag("loading").assertIsDisplayed()
    }

    @Test
    fun loadCategoriesSuccessAndDisplayProducts() = runTest {

        caseTest.isCategorySuccess = true

        composeTestRule.activity.setContent {
            JetpackComposeHomeAssignmentTheme{
                ProductListTabScreen { }
            }
        }

        composeTestRule.onNodeWithTag("loading").assertExists()

        advanceTimeBy(5000)

        composeTestRule
            .onAllNodesWithText("Category 1")
            .fetchSemanticsNodes().isNotEmpty()

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

    @Test
    fun loadCategoriesFail() = runTest {
        caseTest.isCategorySuccess = false

        composeTestRule.activity.setContent {
            JetpackComposeHomeAssignmentTheme{
                ProductListTabScreen { }
            }
        }

        composeTestRule.onNodeWithTag("loading") .assertIsDisplayed()

        advanceTimeBy(5000)

        composeTestRule.onNodeWithTag("error")
            .assertIsDisplayed()
    }

}


