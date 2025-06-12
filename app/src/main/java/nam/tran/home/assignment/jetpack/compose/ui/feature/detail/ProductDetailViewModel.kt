package nam.tran.home.assignment.jetpack.compose.ui.feature.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import nam.tran.home.assignment.jetpack.compose.model.ui.StatusState
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val useCase: ProductDetailUseCase,
) : ViewModel() {

    private val _detailDataState = MutableStateFlow<ProductDetailResponse?>(null)
    val detailDataState: StateFlow<ProductDetailResponse?> = _detailDataState

    private val _statusState = MutableStateFlow<StatusState>(StatusState.Loading)
    val statusState: StateFlow<StatusState> = _statusState

    init {
        loadProductDetail()
    }

    fun loadProductDetail(){
        viewModelScope.launch {
            try {
                _statusState.value = StatusState.Loading
                _detailDataState.value = useCase.execute(savedStateHandle.get("productId"))
                _statusState.value = StatusState.Success
            }catch (e : Exception){
                _statusState.value = StatusState.Error(error = e)
            }
        }
    }
}