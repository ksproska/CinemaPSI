package com.psi.ticket.collector.common.network

import com.psi.ticket.collector.common.network.data.CollectTicketData
import com.psi.ticket.collector.common.network.data.CollectTicketResponse
import com.psi.ticket.collector.common.network.data.TicketData
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface TicketCollectorService {

    @GET("/get-ticket/{id}")
    suspend fun fetchTicket(@Path("id") ticketId: Long): TicketData

    @POST("/collect")
    suspend fun collectTicket(@Body ticketData: CollectTicketData): CollectTicketResponse
}