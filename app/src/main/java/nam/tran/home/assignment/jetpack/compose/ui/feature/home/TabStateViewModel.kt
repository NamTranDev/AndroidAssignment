package nam.tran.home.assignment.jetpack.compose.ui.feature.home

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import dagger.hilt.android.lifecycle.HiltViewModel
import nam.tran.home.assignment.jetpack.compose.ui.navigation.Tab
import javax.inject.Inject

@HiltViewModel
class TabStateViewModel @Inject constructor() : ViewModel() {

    private val _currentTab = mutableStateOf(Tab.ProductList.tab)
    val currentTab: State<String> = _currentTab

    fun selectTab(tab: String) {
        _currentTab.value = tab
    }
}