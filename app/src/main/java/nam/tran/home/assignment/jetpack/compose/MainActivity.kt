package nam.tran.home.assignment.jetpack.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.ActivityScoped
import io.lifestyle.plus.utils.Logger
import kotlinx.coroutines.launch
import nam.tran.home.assignment.jetpack.compose.domain.usecase.OnBoardingUseCase
import nam.tran.home.assignment.jetpack.compose.ui.feature.onboarding.OnBoardingViewModel
import nam.tran.home.assignment.jetpack.compose.ui.feature.onboarding.screen.OnBoardingScreen
import nam.tran.home.assignment.jetpack.compose.ui.theme.JetpackComposeHomeAssignmentTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var onBoardingUseCase : OnBoardingUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBar?.hide()
        enableEdgeToEdge()
        installSplashScreen()
        lifecycleScope.launch {
            onBoardingUseCase.readAppOnboarding().collect{
                Logger.debug(it)
            }
        }
        setContent {
            JetpackComposeHomeAssignmentTheme {
                Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)){
                    val viewModel : OnBoardingViewModel = hiltViewModel()
                    OnBoardingScreen(viewModel::onEvent)
                }
            }
        }
    }
}