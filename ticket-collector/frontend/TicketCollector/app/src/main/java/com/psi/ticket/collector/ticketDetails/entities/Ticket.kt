package com.psi.ticket.collector.ticketDetails.entities

import com.psi.ticket.collector.common.network.data.TicketData

data class Ticket(
    val ticketId: Long,
    val isValidated: Boolean,
    val isStudent: Boolean,
    val room: Int,
    val seat: Int,
    val row: Int
)

fun TicketData.toTicket(): Ticket {
    return Ticket(
        ticketId = requireNotNull(ticketId),
        isValidated = requireNotNull(isValidated),
        isStudent = requireNotNull(isStudent),
        room = requireNotNull(room),
        seat = requireNotNull(seat),
        row = requireNotNull(row)
    )
}