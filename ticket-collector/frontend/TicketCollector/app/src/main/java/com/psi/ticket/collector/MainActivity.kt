package com.psi.ticket.collector

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import com.psi.ticket.collector.codeCheck.presentation.CodeCheckViewModel
import com.psi.ticket.collector.codeCheck.presentation.CodeCheckViewModelFactory
import com.psi.ticket.collector.destinations.CodeCheckScreenDestination
import com.psi.ticket.collector.destinations.TicketDetailsScreenDestination
import com.psi.ticket.collector.ticketDetails.presentation.TicketDetailsViewModel
import com.psi.ticket.collector.ticketDetails.presentation.TicketDetailsViewModelFactory
import com.psi.ticket.collector.ui.theme.TicketCollectorTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.dependency
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var codeCheckViewModelFactory: CodeCheckViewModelFactory

    private val codeCheckViewModel: CodeCheckViewModel by viewModels {
        codeCheckViewModelFactory
    }

    @Inject
    lateinit var ticketDetailsViewModelFactory: TicketDetailsViewModelFactory

    private val ticketDetailsViewModel: TicketDetailsViewModel by viewModels {
        ticketDetailsViewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }

    @Composable
    fun MainScreen() {
        TicketCollectorTheme {
            DestinationsNavHost(
                navGraph = NavGraphs.root,
                dependenciesContainerBuilder = {
                    dependency(CodeCheckScreenDestination) { codeCheckViewModel }
                    dependency(TicketDetailsScreenDestination) { ticketDetailsViewModel }
                }
            )
        }
    }
}
