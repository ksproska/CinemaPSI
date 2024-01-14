package com.psi.ticket.collector.common.network

import com.psi.ticket.collector.common.network.data.CollectTicketResponse
import com.psi.ticket.collector.common.network.data.TicketData

interface TicketCollectorRepository {

    suspend fun getTicket(ticketId: Long): TicketData
    suspend fun collectTicket(ticketId: Long): CollectTicketResponse
}