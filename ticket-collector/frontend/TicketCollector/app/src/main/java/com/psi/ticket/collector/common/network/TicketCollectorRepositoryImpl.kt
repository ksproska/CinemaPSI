package com.psi.ticket.collector.common.network

import com.psi.ticket.collector.common.network.data.CollectTicketData
import com.psi.ticket.collector.common.network.data.CollectTicketResponse
import com.psi.ticket.collector.common.network.data.TicketData
import javax.inject.Inject

class TicketCollectorRepositoryImpl @Inject constructor(
    private val ticketCollectorService: TicketCollectorService
) : TicketCollectorRepository {

    override suspend fun getTicket(ticketId: Long): TicketData {
        return ticketCollectorService.fetchTicket(ticketId)
    }

    override suspend fun collectTicket(ticketId: Long): CollectTicketResponse {
        return ticketCollectorService.collectTicket(
            ticketData = CollectTicketData(ticketId = ticketId)
        )
    }
}