package com.psi.ticket.collector.codeCheck.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class CodeCheckViewModel @Inject constructor(

) : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state: StateFlow<ViewState> = _state.toViewState().stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = _state.value.toViewState()
    )

    private val _action = MutableSharedFlow<Action>()
    val action: SharedFlow<Action> = _action.asSharedFlow()

    fun trigger(command: Command) {
        when (command) {
            is Command.OnCodeChange -> onCodeChange(command.code)
            Command.OnConfirmButtonClick -> onConfirmButtonClick()
        }
    }

    private fun onCodeChange(newCode: Long) {
        _state.value = _state.value.copy(code = newCode)
    }

    private fun onConfirmButtonClick() {
        _state.value.code?.let { code ->
            _state.value = _state.value.copy(isError = false)
            viewModelScope.launch {
                _action.emit(Action.GoToTicketDetailsScreen(code = code))
            }
        } ?: run {
            onError()
        }
    }

    private fun onError() {
        _state.value = _state.value.copy(isError = true)
    }

    private fun Action.trigger() {
        viewModelScope.launch {
            _action.emit(this@trigger)
        }
    }

    private fun MutableStateFlow<State>.toViewState(): Flow<ViewState> {
        return map { state ->
            state.toViewState()
        }
    }

    private fun State.toViewState(): ViewState {
        return ViewState(
            code = code,
            isError = isError
        )
    }

    sealed class Action {
        data class GoToTicketDetailsScreen(val code: Long) : Action()
    }

    sealed class Command {
        class OnCodeChange(val code: Long) : Command()
        object OnConfirmButtonClick : Command()
    }

    data class State(
        val code: Long? = null,
        val isError: Boolean = false
    )

    data class ViewState(
        val code: Long?,
        val isError: Boolean
    )
}