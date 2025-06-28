package nam.tran.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import nam.tran.home.navigation.Tab
import javax.inject.Inject

@HiltViewModel
class TabStateViewModel @Inject constructor() : ViewModel() {

    private val _currentTab = mutableStateOf(Tab.ProductList.tab)
    val currentTab: State<String> = _currentTab

    fun selectTab(tab: String) {
        _currentTab.value = tab
    }
}