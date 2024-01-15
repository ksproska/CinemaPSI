package com.psi.ticket.collector.ticketDetails.ui

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.psi.ticket.collector.R
import com.psi.ticket.collector.common.entities.ScreenState
import com.psi.ticket.collector.common.ui.components.Screen
import com.psi.ticket.collector.ticketDetails.presentation.TicketDetailsViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination
fun TicketDetailsScreen(
    code: Long,
    viewModel: TicketDetailsViewModel,
    navigator: DestinationsNavigator
) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(key1 = code) {
        viewModel.trigger(TicketDetailsViewModel.Command.OnCodeReceived(code = code))
    }

    LaunchedEffect(Unit) {
        viewModel.action.collect { action ->
            when (action) {
                is TicketDetailsViewModel.Action.ShowToast -> showToast(
                    context = context,
                    message = action.message
                )

                TicketDetailsViewModel.Action.GoBack -> navigator.navigateUp()
            }
        }
    }

    TicketDetailsScreen(
        viewState = state,
        trigger = viewModel::trigger
    )
}

@Composable
fun TicketDetailsScreen(
    viewState: TicketDetailsViewModel.ViewState,
    trigger: (TicketDetailsViewModel.Command) -> Unit
) {

    Screen(modifier = Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            when (viewState.screenState) {
                ScreenState.Success -> SuccessContent(viewState = viewState, trigger = trigger)
                ScreenState.Loading -> LoadingContent()
                ScreenState.Error -> ErrorContent(trigger = trigger)
            }
        }
    }
}

@Composable
private fun SuccessContent(
    viewState: TicketDetailsViewModel.ViewState,
    trigger: (TicketDetailsViewModel.Command) -> Unit
) {
    Text(
        text = stringResource(id = R.string.active_ticket).uppercase(),
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.primary,
        fontSize = 20.sp
    )
    Spacer(modifier = Modifier.height(10.dp))
    if (viewState.isStudent == true) {
        Text(
            text = stringResource(id = R.string.student_ticket),
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.tertiary
        )
        Text(
            text = stringResource(id = R.string.confirmation_required),
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.tertiary
        )
    } else {
        Text(
            text = stringResource(id = R.string.regular_ticket),
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.tertiary
        )
    }
    Spacer(modifier = Modifier.height(10.dp))
    Text(
        text = stringResource(id = R.string.id, viewState.code.toString()),
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold
    )
    Spacer(modifier = Modifier.height(10.dp))
    Text(text = stringResource(id = R.string.room, viewState.room.toString()))
    Spacer(modifier = Modifier.height(10.dp))
    Text(text = stringResource(id = R.string.seat, viewState.seat.toString()))
    Spacer(modifier = Modifier.height(10.dp))
    Text(text = stringResource(id = R.string.row, viewState.row.toString()))
    Spacer(modifier = Modifier.height(10.dp))
    Button(
        enabled = viewState.isValidated?.not() ?: true,
        onClick = { trigger(TicketDetailsViewModel.Command.OnCollectButtonClick) }
    ) {
        Text(text = stringResource(id = R.string.collect_ticket))
    }
}

@Composable
private fun LoadingContent() {
    CircularProgressIndicator()
}

@Composable
private fun ErrorContent(
    trigger: (TicketDetailsViewModel.Command) -> Unit
) {
    Text(
        text = stringResource(id = R.string.error),
        fontSize = 18.sp
    )
    Spacer(modifier = Modifier.height(20.dp))
    Button(onClick = { trigger(TicketDetailsViewModel.Command.OnTryAgainButtonClick) }) {
        Text(text = stringResource(id = R.string.try_again))
    }
}

private fun showToast(context: Context, message: String) =
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()

@Preview
@Composable
fun TicketDetailsScreenPreview() {
    TicketDetailsScreen(
        viewState = TicketDetailsViewModel.ViewState(
            code = 163725L,
            screenState = ScreenState.Success,
            isValidated = false,
            isStudent = true,
            room = 10,
            seat = 15,
            row = 7,
            message = ""
        ),
        trigger = {}
    )
}

@Preview
@Composable
fun TicketDetailsScreenPreviewLoading() {
    TicketDetailsScreen(
        viewState = TicketDetailsViewModel.ViewState(
            code = 163725L,
            screenState = ScreenState.Loading,
            isValidated = false,
            isStudent = true,
            room = 10,
            seat = 15,
            row = 7,
            message = ""
        ),
        trigger = {}
    )
}

@Preview
@Composable
fun TicketDetailsScreenPreviewError() {
    TicketDetailsScreen(
        viewState = TicketDetailsViewModel.ViewState(
            code = 163725L,
            screenState = ScreenState.Error,
            isValidated = false,
            isStudent = true,
            room = 10,
            seat = 15,
            row = 7,
            message = ""
        ),
        trigger = {}
    )
}