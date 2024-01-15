package com.psi.ticket.collector.codeCheck.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.psi.ticket.collector.R
import com.psi.ticket.collector.codeCheck.presentation.CodeCheckViewModel
import com.psi.ticket.collector.common.ui.components.Screen
import com.psi.ticket.collector.destinations.TicketDetailsScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator

@RootNavGraph(start = true)
@Destination
@Composable
fun CodeCheckScreen(
    viewModel: CodeCheckViewModel,
    navigator: DestinationsNavigator
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.action.collect { action ->
            when (action) {
                is CodeCheckViewModel.Action.GoToTicketDetailsScreen -> navigator.navigate(
                    TicketDetailsScreenDestination(code = action.code)
                )
            }
        }
    }

    CodeCheckScreen(
        viewState = state,
        trigger = viewModel::trigger
    )
}

@Composable
fun CodeCheckScreen(
    viewState: CodeCheckViewModel.ViewState,
    trigger: (CodeCheckViewModel.Command) -> Unit
) {
    var text by remember { mutableStateOf("") }

    Screen(modifier = Modifier.fillMaxSize()) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                fontSize = 20.sp,
                text = stringResource(id = R.string.enter_code)
            )
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                value = text,
                onValueChange = { code ->
                    text = code
                    trigger(CodeCheckViewModel.Command.OnCodeChange(code.toLong()))
                },
                supportingText = {
                    if (viewState.isError) {
                        Text(text = stringResource(id = R.string.no_code_error))
                    }
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = { trigger(CodeCheckViewModel.Command.OnConfirmButtonClick) }) {
                Text(text = stringResource(id = R.string.confirm_code))
            }
        }
    }
}

@Preview
@Composable
fun CodeCheckScreenPreview() {
    CodeCheckScreen(
        viewState = CodeCheckViewModel.ViewState(code = null, isError = false),
        trigger = {}
    )
}