package nam.tran.home.assignment.jetpack.compose.model.ui

sealed interface StatusState{
    data object Loading : StatusState
    data class Error(val error : Throwable) : StatusState
    data object Success : StatusState
}