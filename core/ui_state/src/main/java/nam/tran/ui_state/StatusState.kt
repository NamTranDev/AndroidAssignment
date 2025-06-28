package nam.tran.ui_state

sealed interface StatusState{
    data object Loading : StatusState
    data class Error(val error : Throwable) : StatusState
    data object Success : StatusState
}