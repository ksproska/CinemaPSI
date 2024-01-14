package com.psi.ticket.collector.ticketDetails.domain

import com.psi.ticket.collector.common.network.TicketCollectorRepository
import com.psi.ticket.collector.ticketDetails.entities.Ticket
import com.psi.ticket.collector.ticketDetails.entities.toTicket
import java.lang.IllegalArgumentException
import javax.inject.Inject

class GetTicketUseCase @Inject constructor(
    private val ticketCollectorRepository: TicketCollectorRepository
) {

    suspend fun execute(code: Long): Ticket? {
        return try {
            ticketCollectorRepository.getTicket(code).toTicket()
        } catch (e: IllegalArgumentException) {
            null
        }
    }
}