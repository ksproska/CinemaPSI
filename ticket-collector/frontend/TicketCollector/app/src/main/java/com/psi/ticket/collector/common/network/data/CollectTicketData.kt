package com.psi.ticket.collector.common.network.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CollectTicketData(
    @Json(name = "ticketId") val ticketId: Long
)