package nam.tran.home.assignment.jetpack.compose.ui.feature.onboarding

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
import nam.tran.home.assignment.jetpack.compose.ui.Dimens.mediumPadding
import nam.tran.home.assignment.jetpack.compose.ui.Dimens.pageIndicatorWidth
import nam.tran.home.assignment.jetpack.compose.ui.Dimens.spaceHeight
import nam.tran.home.assignment.jetpack.compose.ui.common.ButtonApp
import nam.tran.home.assignment.jetpack.compose.ui.feature.onboarding.components.OnBoardingPage
import nam.tran.home.assignment.jetpack.compose.ui.feature.onboarding.components.OnBoardingScreenComponent
import nam.tran.home.assignment.jetpack.compose.ui.feature.onboarding.components.PageIndicator
import nam.tran.home.assignment.jetpack.compose.ui.feature.onboarding.model.PageInfo.Companion.pageInfos
import nam.tran.home.assignment.jetpack.compose.ui.theme.JetpackComposeHomeAssignmentTheme

@Composable
fun OnBoardingScreen(viewModel: OnBoardingViewModel = hiltViewModel()) {
    OnBoardingScreenComponent{
        viewModel.saveOnBoaringEntry()
    }
}