package com.psi.ticket.collector.ticketDetails.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class TicketDetailsViewModelFactory @Inject constructor(
    ticketDetailsViewModelProvider: Provider<TicketDetailsViewModel>
) : ViewModelProvider.Factory {

    private val providers = mapOf<Class<*>, Provider<out ViewModel>>(
        TicketDetailsViewModel::class.java to ticketDetailsViewModelProvider
    )

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return providers[modelClass]!!.get() as T
    }
}