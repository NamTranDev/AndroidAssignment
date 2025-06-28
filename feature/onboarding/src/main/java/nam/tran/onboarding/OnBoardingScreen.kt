package nam.tran.onboarding

import android.content.res.Configuration
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch
import nam.tran.components.ButtonApp
import nam.tran.resource.Dimens.mediumPadding
import nam.tran.resource.Dimens.pageIndicatorWidth
import nam.tran.resource.Dimens.spaceHeight
import nam.tran.onboarding.components.OnBoardingPage
import nam.tran.onboarding.components.PageIndicator
import nam.tran.onboarding.model.PageInfo
import nam.tran.resource.theme.AppTheme

@Composable
fun OnBoardingScreen(viewModel: OnBoardingViewModel = hiltViewModel()) {
    OnBoardingScreenContent{
        viewModel.saveOnBoaringEntry()
    }
}

@Composable
fun OnBoardingScreenContent(
    onSaveBoardingEntry : () -> Unit = {}
){
    Column(modifier = Modifier.fillMaxSize()) {

        val pageState = rememberPagerState(initialPage = 0) {
            PageInfo.Companion.pageInfos.size
        }

        val buttonState = remember {
            derivedStateOf {
                when (pageState.currentPage) {
                    0 -> {
                        listOf("", "Next")
                    }
                    pageState.pageCount - 1 -> {
                        listOf("Back", "Get Started")
                    }
                    else -> {
                        listOf("Back", "Next")
                    }
                }
            }
        }

        HorizontalPager(pageState) { index ->
            OnBoardingPage(PageInfo.Companion.pageInfos.get(index))
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
                            onSaveBoardingEntry.invoke()
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

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun OnBoardingScreenComponentPreview() {
    AppTheme {
        OnBoardingScreenContent()
    }
}