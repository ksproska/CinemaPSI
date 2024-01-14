package com.psi.ticket.collector.ticketDetails.entities

import com.psi.ticket.collector.common.network.data.CollectTicketResponse

data class CollectResponse(
    val message: String
)

fun CollectTicketResponse.toCollectResponse(): CollectResponse =
    CollectResponse(message = message)