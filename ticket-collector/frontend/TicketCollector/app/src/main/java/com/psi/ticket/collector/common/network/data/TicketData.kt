package com.psi.ticket.collector.common.network.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TicketData(
    @Json(name = "id") val ticketId: Long?,
    @Json(name = "isValidated") val isValidated: Boolean?,
    @Json(name = "isStudent") val isStudent: Boolean?,
    @Json(name = "room") val room: Int?,
    @Json(name = "seat") val seat: Int?,
    @Json(name = "row") val row: Int?
)