package com.psi.ticket.collector.ticketDetails.domain

import com.psi.ticket.collector.common.network.TicketCollectorRepository
import com.psi.ticket.collector.ticketDetails.entities.CollectResponse
import com.psi.ticket.collector.ticketDetails.entities.toCollectResponse
import javax.inject.Inject

class CollectTicketUseCase @Inject constructor(
    private val ticketCollectorRepository: TicketCollectorRepository
) {

    suspend fun execute(code: Long): CollectResponse? {
        return try {
            ticketCollectorRepository.collectTicket(code).toCollectResponse()
        } catch (e: IllegalArgumentException) {
            null
        }
    }
}