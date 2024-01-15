package com.psi.ticket.collector.ticketDetails.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.psi.ticket.collector.common.entities.ScreenState
import com.psi.ticket.collector.ticketDetails.domain.CollectTicketUseCase
import com.psi.ticket.collector.ticketDetails.domain.GetTicketUseCase
import com.psi.ticket.collector.ticketDetails.entities.Ticket
import kotlinx.coroutines.Job
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
import timber.log.Timber
import javax.inject.Inject


class TicketDetailsViewModel @Inject constructor(
    private val getTicket: GetTicketUseCase,
    private val collectTicket: CollectTicketUseCase
) : ViewModel() {

    private var getTicketJob: Job? = null
    private var collectTicketJob: Job? = null

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
            is Command.OnCodeReceived -> onCodeReceived(command.code)
            Command.OnCollectButtonClick -> onCollectButtonClick()
            Command.OnTryAgainButtonClick -> onTryAgainButtonClick()
        }
    }

    private fun onCodeReceived(code: Long) {
        _state.value = _state.value.copy(code = code)
        loadTicketData()
    }

    private fun onCollectButtonClick() {
        collectTicketJob?.cancel()
        collectTicketJob = viewModelScope.launch {
            runCatching {
                val message: String? = _state.value.code?.let { code ->
                    collectTicket.execute(code = code)?.message
                }
                message?.let {
                    onSuccessCollectTicket(message = message)
                } ?: onCollectTicketError()
            }.onFailure { error ->
                Timber.e(error, "Collect ticket failed")
                onErrorCollectTicket()
            }
        }
    }

    private fun onTryAgainButtonClick() {
        loadTicketData()
    }

    private fun loadTicketData() {
        _state.value = _state.value.copy(
            screenState = ScreenState.Loading
        )

        getTicketJob?.cancel()
        getTicketJob = viewModelScope.launch {
            runCatching {
                val ticket: Ticket? = _state.value.code?.let { code ->
                    getTicket.execute(code = code)
                }
                ticket?.let {
                    onSuccessTicketLoad(ticket)
                } ?: onCollectTicketError()
            }.onFailure { error ->
                Timber.e(error, "Get ticket failed")
                onCollectTicketError()
            }
        }
    }

    private fun onSuccessTicketLoad(ticket: Ticket) {
        _state.value = _state.value.copy(
            ticket = ticket,
            screenState = ScreenState.Success
        )
    }

    private fun onCollectTicketError() {
        viewModelScope.launch {
            _action.emit(Action.ShowToast(GET_TICKET_ERROR))
            _action.emit(Action.GoBack)
        }
    }

    private fun onErrorCollectTicket() {
        viewModelScope.launch {
            _action.emit(Action.ShowToast(COLLECT_ERROR))
        }
    }

    private fun onSuccessCollectTicket(message: String) {
        _state.value = _state.value.copy(
            ticket = _state.value.ticket?.copy(isValidated = true),
            message = message,
            screenState = ScreenState.Success
        )
        viewModelScope.launch {
            _action.emit(Action.ShowToast(message))
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
            screenState = screenState,
            isValidated = ticket?.isValidated,
            isStudent = ticket?.isStudent,
            room = ticket?.room,
            seat = ticket?.seat,
            row = ticket?.row,
            message = message
        )
    }

    sealed class Action {
        data class ShowToast(val message: String) : Action()
        object GoBack : Action()
    }

    sealed class Command {
        data class OnCodeReceived(val code: Long) : Command()
        object OnCollectButtonClick : Command()
        object OnTryAgainButtonClick : Command()
    }

    data class State(
        val code: Long? = null,
        val screenState: ScreenState = ScreenState.Loading,
        val ticket: Ticket? = null,
        val message: String = ""
    )

    data class ViewState(
        val code: Long?,
        val screenState: ScreenState,
        val isValidated: Boolean?,
        val isStudent: Boolean?,
        val room: Int?,
        val seat: Int?,
        val row: Int?,
        val message: String
    )

    companion object {
        private const val COLLECT_ERROR = "Skasowanie nie powiodło się"
        private const val GET_TICKET_ERROR = "Pobranie biletu nie powiodło się"
    }
}