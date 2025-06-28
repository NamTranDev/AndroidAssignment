package nam.tran.product_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import nam.tran.domain.model.entity.ProductDetailEntity
import nam.tran.domain.usecase.ProductDetailUseCase
import nam.tran.ui_state.StatusState
import nam.tran.utils.Logger
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val useCase: ProductDetailUseCase,
) : ViewModel() {

    private val _detailDataState = MutableStateFlow<ProductDetailEntity?>(null)
    val detailDataState: StateFlow<ProductDetailEntity?> = _detailDataState

    private val _statusState = MutableStateFlow<StatusState>(StatusState.Loading)
    val statusState: StateFlow<StatusState> = _statusState

    fun loadProductDetail(productId : String){
        Logger.debug(productId)
        viewModelScope.launch {
            try {
                _statusState.value = StatusState.Loading
                _detailDataState.value = useCase.execute(productId)
                _statusState.value = StatusState.Success
            }catch (e : Exception){
                _statusState.value = StatusState.Error(error = e)
            }
        }
    }
}