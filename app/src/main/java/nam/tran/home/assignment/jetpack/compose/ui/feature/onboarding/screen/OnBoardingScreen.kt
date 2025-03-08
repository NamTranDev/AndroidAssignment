package nam.tran.home.assignment.jetpack.compose.ui.feature.onboarding.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch
import nam.tran.home.assignment.jetpack.compose.ui.Dimens.mediumPadding
import nam.tran.home.assignment.jetpack.compose.ui.Dimens.pageIndicatorWidth
import nam.tran.home.assignment.jetpack.compose.ui.Dimens.spaceHeight
import nam.tran.home.assignment.jetpack.compose.ui.common.ButtonApp
import nam.tran.home.assignment.jetpack.compose.ui.feature.onboarding.components.OnBoardingPage
import nam.tran.home.assignment.jetpack.compose.ui.feature.onboarding.components.PageIndicator
import nam.tran.home.assignment.jetpack.compose.ui.feature.onboarding.model.PageInfo.Companion.pageInfos

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen() {
    Column(modifier = Modifier.fillMaxSize()) {

        val pageState = rememberPagerState(initialPage = 0) {
            pageInfos.size
        }

        val buttonState = remember {
            derivedStateOf {
                if (pageState.currentPage == 0) {
                    listOf("", "Next")
                } else if (pageState.currentPage == pageState.pageCount - 1) {
                    listOf("Back", "Get Started")
                } else {
                    listOf("Back", "Next")
                }
            }
        }

        HorizontalPager(pageState) { index ->
            OnBoardingPage(pageInfos.get(index))
        }
        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = mediumPadding)
                .navigationBarsPadding(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            PageIndicator(
                modifier = Modifier.width(pageIndicatorWidth),
                pageSize = pageState.pageCount,
                selectedPage = pageState.currentPage
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                val scope = rememberCoroutineScope()
                if (buttonState.value[0].isNotEmpty()) {
                    ButtonApp(onClick = {
                        scope.launch {
                            pageState.animateScrollToPage(page = pageState.currentPage - 1)
                        }
                    }, text = buttonState.value[0], isTextButton = true)
                }
                ButtonApp(onClick = {
                    scope.launch {
                        if (pageState.currentPage == pageState.pageCount - 1) {
                            //go to home page
                        } else {
                            pageState.animateScrollToPage(page = pageState.currentPage + 1)
                        }
                    }
                }, text = buttonState.value[1])
            }
        }
        Spacer(modifier = Modifier.height(spaceHeight))
    }
}