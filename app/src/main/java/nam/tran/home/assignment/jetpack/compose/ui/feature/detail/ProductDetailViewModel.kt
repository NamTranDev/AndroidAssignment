package nam.tran.home.assignment.jetpack.compose.ui.feature.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import nam.tran.home.assignment.jetpack.compose.domain.usecase.ProductDetailUseCase
import nam.tran.home.assignment.jetpack.compose.model.response.ProductDetailResponse
import nam.tran.home.assignment.jetpack.compose.model.ui.StatusState
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    useCase: ProductDetailUseCase,
) : ViewModel() {

    private val _statusState = MutableStateFlow<StatusState>(StatusState.Loading)
    val statusState: StateFlow<StatusState> = _statusState

    private val retryTrigger = MutableStateFlow(Unit)

    val productDetailState: StateFlow<ProductDetailResponse?> =
        retryTrigger
            .flatMapLatest {
               useCase.produceDetail(savedStateHandle.get("productId"))
            }.onStart {
                _statusState.value = StatusState.Loading
            }
            .onEach {
                _statusState.value = StatusState.Success
            }
            .catch {
                _statusState.value = StatusState.Error(error = it)
            }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    fun retry() {
        retryTrigger.value = Unit
    }
}